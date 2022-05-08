package com.github.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.dto.Result;
import com.github.dto.UserDTO;
import com.github.pojo.Blog;
import com.github.mapper.BlogMapper;
import com.github.pojo.User;
import com.github.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.service.IUserService;
import com.github.utils.RedisConstants;
import com.github.utils.SystemConstants;
import com.github.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Resource
    private IUserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result queryBlogById(Long id) {
        // 查询blog
        Blog blog = getById(id);
        if (blog == null) {
            return Result.fail("笔记不存在！");
        }
        // 查询blog有关的用户
        queryBlogUser(blog);
        // 查询blog是否被点赞
        isBLogLiked(blog);
        return null;
    }

    private void isBLogLiked(Blog blog) {
        // 1 获取登陆用户
        UserDTO user = UserHolder.getUser();
        if (user == null) {
            // 用户未登陆，无需查询是否点赞
            return;
        }
        Long userId = UserHolder.getUser().getId();
        // 2 判断当前登陆用户是否已经点赞
        String key = "blog:liked:" + blog.getId();
        Double score = stringRedisTemplate.opsForZSet().score(key, userId.toString());
        blog.setIsLike(score != null);
    }

    @Override
    public Result queryHotBlog(Integer current) {
        // 根据用户查询
        Page<Blog> page = query()
                .orderByDesc("liked")
                .page(new Page<>(current, SystemConstants.MAX_PAGE_SIZE));
        // 获取当前页数据
        List<Blog> records = page.getRecords();
        // 查询用户
        // records.forEach(this::queryBlogUser);
        records.forEach(blog -> {
            this.queryBlogUser(blog);
            this.isBLogLiked(blog);
        });
        return Result.ok(records);
    }

    @Override
    public Result likeBlog(Long id) {
        // 1 获取登陆用户
        Long userId = UserHolder.getUser().getId();
        // 2 判断当前登陆用户是否已经点赞
        String key = "blog:liked:" + id;
        Double score = stringRedisTemplate.opsForZSet().score(key, userId.toString());
        if (score == null) {
            // 3 如果未点赞，可以点赞
            // 3.1 数据库点赞数+1
            boolean isSuccess = update().setSql("liked=liked+1").eq("id", id).update();
            // 3.2 保存用户到redis set集合
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().add(key, userId.toString(), System.currentTimeMillis());
            }
        } else {
            // 4 如果已点赞，取消点赞
            // 4.1 数据库点赞数-1
            boolean isSuccess = update().setSql("liked=liked-1").eq("id", id).update();
            // 4.2 把用户从redis set集合移除
            if (isSuccess) {
                stringRedisTemplate.opsForZSet().remove(key, userId.toString());
            }
        }
        return Result.ok();
    }

    @Override
    public Result queryBlogLikes(Long id) {
        String key = RedisConstants.BLOG_LIKED_KEY + id;
        // 1 查询top5的点赞用户 zrange key 0 4
        Set<String> topFive = stringRedisTemplate.opsForZSet().range(key, 0, 4);
        if (topFive == null || topFive.isEmpty()) {
            return Result.ok(Collections.emptyList());
        }
        // 2 解析出其中的用户ID
        List<Long> ids = topFive.stream().map(Long::valueOf).collect(Collectors.toList());
        // 3 根据用户ID查询用户
        // select * from tb_user where id in (5, 1) order by field(id, 5, 1)
        String idStr = StrUtil.join(",", ids);
        List<UserDTO> userDTOS = userService.query()
                .in("id", ids)
                .last("order by field(id, " + idStr + ")").list()
                .stream()
                .map(user -> BeanUtil.copyProperties(user, UserDTO.class))
                .collect(Collectors.toList());
        // 4 返回
        return Result.ok(userDTOS);
    }

    private void queryBlogUser(Blog blog) {
        Long userId = blog.getUserId();
        User user = userService.getById(userId);
        blog.setName(user.getNickName());
        blog.setIcon(user.getIcon());
    }
}

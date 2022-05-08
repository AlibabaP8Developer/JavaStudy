package com.github.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.dto.Result;
import com.github.pojo.Follow;
import com.github.mapper.FollowMapper;
import com.github.service.IFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.utils.UserHolder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {

    @Override
    public Result follow(Long followUserId, Boolean isFollow) {
        Long userId = UserHolder.getUser().getId();
        // 1.判断到底是关注还是取关
        if (isFollow) {
            // 2.关注，新增
            Follow follow = new Follow();
            follow.setUserId(userId);
            follow.setFollowUserId(followUserId);
            this.save(follow);
        } else {
            // 3.取关，删除
            QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("follow_user_id", followUserId);
            this.remove(queryWrapper);
        }
        return Result.ok();
    }

    @Override
    public Result isFollow(Long followUserId) {
        // 1.获取登陆用户
        Long userId = UserHolder.getUser().getId();
        // 2.查询是否关注
        Integer count = this.query().eq("user_id", userId).eq("follow_user_id", followUserId).count();
        // 3.判断
        return Result.ok(count > 0);
    }
}

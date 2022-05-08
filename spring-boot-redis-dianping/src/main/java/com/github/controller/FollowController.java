package com.github.controller;


import com.github.dto.Result;
import com.github.service.IFollowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/follow")
public class FollowController {

    @Resource
    private IFollowService followService;

    /**
     * 关注&取消关注
     * @param followUserId 被关注用户ID
     * @return
     */
    @PutMapping("/{id}{isFollow}")
    public Result follow(@PathVariable("id") Long followUserId, @PathVariable("isFollow") Boolean isFollow ){
        return followService.follow(followUserId, isFollow);
    }

    /**
     * 是否关注
     * @param followUserId 被关注用户ID
     * @return
     */
    @GetMapping("/or/not/{id}")
    public Result isFollow(@PathVariable("id") Long followUserId){
        return followService.isFollow(followUserId);
    }
}

package com.tedbilgar.feather.rest_controller.user_group_controller;

import com.tedbilgar.feather.service.userAndGroupService.UserAndGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/user_group")
public class UserAndGroupController {
    @Autowired
    private UserAndGroupService userAndGroupService;

    @RequestMapping("/set")
    public void setRelation(@RequestParam("userId") Long userId,@RequestParam("groupId") Long groupId){
        userAndGroupService.setRelation(userId,groupId);
    }

    @RequestMapping("/test")
    public void test(){

    }
}

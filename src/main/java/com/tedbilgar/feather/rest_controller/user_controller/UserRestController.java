package com.tedbilgar.feather.rest_controller.user_controller;

import com.tedbilgar.feather.domain.units.User;
import com.tedbilgar.feather.repository.group_repo.GroupRepo;
import com.tedbilgar.feather.repository.user_repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/user")
public class UserRestController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GroupRepo groupRepo;

    @GetMapping
    public List<User> findAll(){
        return userRepo.findAll();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable Long id){
        return userRepo.findAllById(id);
    }

}

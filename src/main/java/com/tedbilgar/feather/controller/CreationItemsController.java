package com.tedbilgar.feather.controller;

import com.tedbilgar.feather.domain.task_units.Desk;
import com.tedbilgar.feather.domain.units.User;
import com.tedbilgar.feather.repository.task_unit_controller.desk_repo.DeskRepo;
import com.tedbilgar.feather.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CreationItemsController {

    @Autowired
    private UserService userService;
    @Autowired
    private DeskRepo deskRepo;

    private User getAuth(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userService.loadUserByUsername(auth.getName());
        return user;
    }

    @GetMapping("/newdesk")
    public ModelAndView createDesk(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",getAuth());
        modelAndView.setViewName("create/newDesk");
        return modelAndView;
    }

    @GetMapping("/setdeskreq/{deskId}")
    public String createDesk(@PathVariable Long deskId){
        User user = getAuth();
        System.out.println("U" + user.getId() + "D" + deskId);
        final String uri = "http://localhost:9000/rest/user-desk/set?userId="+user.getId() + "&deskId=" + deskId+"&role=REQ";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);

        return "redirect:/deskcommunity";
    }


}

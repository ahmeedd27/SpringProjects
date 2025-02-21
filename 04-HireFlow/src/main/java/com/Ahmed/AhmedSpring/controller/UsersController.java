package com.Ahmed.AhmedSpring.controller;

import com.Ahmed.AhmedSpring.entity.Users;
import com.Ahmed.AhmedSpring.entity.UsersType;
import com.Ahmed.AhmedSpring.services.UsersService;
import com.Ahmed.AhmedSpring.services.UsersTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsersController {
    private final UsersTypeService utr;
    private final UsersService us;
    @Autowired
    public UsersController(UsersTypeService utr , UsersService us ){
        this.utr=utr;
        this.us=us;
    }

    @GetMapping("/register")
    public String register(Model m){
       List<UsersType> usersTypes= utr.getAll();
       m.addAttribute("getAllTypes" , usersTypes);
       m.addAttribute("user" , new Users());
       return "register";
    }
    @PostMapping("/register/new")
    public String newRegUser(@Valid Users user, Model m){
        if(us.findByEmail(user.getEmail()).isPresent()){
            m.addAttribute("error" , "this email is exist");
            List<UsersType> usersTypes= utr.getAll();
            m.addAttribute("getAllTypes" , usersTypes);
            m.addAttribute("user" , new Users());
            return "register";
        }
        us.addNew(user);
        return "redirect:/dashboard/";
    }
    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request , HttpServletResponse response){

        Authentication principle= SecurityContextHolder.getContext().getAuthentication();
        if(principle != null){
            new SecurityContextLogoutHandler().logout(request , response , principle);
        }
        return "redirect:/";
    }


}

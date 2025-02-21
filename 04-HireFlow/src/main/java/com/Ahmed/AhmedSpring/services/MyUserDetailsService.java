package com.Ahmed.AhmedSpring.services;

import com.Ahmed.AhmedSpring.entity.Users;
import com.Ahmed.AhmedSpring.util.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UsersService us;

    @Autowired
    public MyUserDetailsService(UsersService us) {  // Setter Injection
        this.us = us;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Users user= us.findByEmail(username)
               .orElseThrow(() -> new UsernameNotFoundException("NotFound"));

       return new MyUserDetails(user);
    }
}

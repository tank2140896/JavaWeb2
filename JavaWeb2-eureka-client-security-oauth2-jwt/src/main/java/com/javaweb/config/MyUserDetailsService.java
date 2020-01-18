package com.javaweb.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    public UserDetails loadUserByUsername(String userame) throws UsernameNotFoundException {
        //TODO db ...
         return null;
    }
    
    

}

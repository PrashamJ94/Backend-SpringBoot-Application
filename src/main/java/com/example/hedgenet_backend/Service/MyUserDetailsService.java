package com.example.hedgenet_backend.Service;

import com.example.hedgenet_backend.Entity.User;
import com.example.hedgenet_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
        Optional<User> userRes = userRepo.findByUsername(user_name);
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with email = " + user_name);
        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                user_name,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
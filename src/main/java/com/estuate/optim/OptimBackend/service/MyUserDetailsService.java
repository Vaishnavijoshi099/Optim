package com.estuate.optim.OptimBackend.service;

import com.estuate.optim.OptimBackend.Repository.UserRepository;
import com.estuate.optim.OptimBackend.model.UserPrinciple;
import com.estuate.optim.OptimBackend.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username);
        if(users==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found!!");
        }
        return new UserPrinciple(users);
    }
}

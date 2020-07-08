package com.benz.web.services;

import com.benz.web.dao.UserDAO;
import com.benz.web.entity.User;
import com.benz.web.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

      @Autowired
      UserDAO user_dao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=user_dao.findByUserName(username);

        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getRoles());
         if(user!=null)
         {
                return new com.benz.web.services.impl.UserDetails(user);
         }else
             throw new DataNotFoundException("Data Not Available");
    }


}

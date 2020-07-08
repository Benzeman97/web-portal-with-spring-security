package com.benz.web.controller;

import com.benz.web.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController{



    @RequestMapping("/admin")
    public String getAdmin()
    {
        return ("<h1>Welcome Admin</h1>");
    }

    @RequestMapping("/user")
    public String getUser()
    {
        return ("<h1>Welcome User</h1>");
    }

    @RequestMapping("/")
    public String getAll()
    {
        return ("<h1>Welcome All</h1>");
    }



}

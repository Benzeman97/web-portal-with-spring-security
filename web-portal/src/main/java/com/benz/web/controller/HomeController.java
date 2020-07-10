package com.benz.web.controller;

import com.benz.web.model.AuthenticationRequest;
import com.benz.web.model.AuthenticationResponse;
import com.benz.web.services.UserDetailsService;
import com.benz.web.services.impl.UserDetails;
import com.benz.web.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController{

     @Autowired
     private AuthenticationManager authManager;

     @Autowired
     private UserDetailsService userDetailsService;

     @Autowired
     private JwtUtil jwtUtil;

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


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthentication(@RequestBody AuthenticationRequest authRequest) throws Exception
    {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));


            final UserDetails userDetails = (UserDetails)userDetailsService.loadUserByUsername(authRequest.getUserName());

            final String jwt=jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));



        }catch (BadCredentialsException bx)
        {
            throw new Exception("Username or Password is invalid",bx);
        }
    }

}

package com.benz.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.benz.web.services.UserDetailsService;
import com.benz.web.services.impl.UserDetails;
import com.benz.web.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader=request.getHeader("Authorization");
		
		String userName=null;
		String jwt=null;
		
		if(authorizationHeader!=null && authorizationHeader.startsWith("Benz "))
		{
			jwt=authorizationHeader.substring(5);
			userName=jwtUtil.extractUserName(jwt);
			
			if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails userDetails=(UserDetails) this.userDetailsService.loadUserByUsername(userName);
				
				if(jwtUtil.validateToken(jwt, userDetails))
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
					=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
				}
			}
			
		}
		filterChain.doFilter(request, response);
		
	}

}

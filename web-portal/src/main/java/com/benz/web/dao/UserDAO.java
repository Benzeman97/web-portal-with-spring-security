package com.benz.web.dao;

import com.benz.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,String> {

    User findByUserName(String userName);
}

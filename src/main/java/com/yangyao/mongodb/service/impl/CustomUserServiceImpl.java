package com.yangyao.mongodb.service.impl;

import com.yangyao.mongodb.model.SysUser;
import com.yangyao.mongodb.service.CustomUserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomUserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户

        SysUser user = userDao.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(SysRole role:user.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            logger.info("loadUserByUsername: " + user);
        }
        user.setGrantedAuthorities(authorities); //用于登录时 @AuthenticationPrincipal 标签取值
        return user;
    }
}

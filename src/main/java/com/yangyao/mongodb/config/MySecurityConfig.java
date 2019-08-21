package com.yangyao.mongodb.config;

import com.yangyao.mongodb.utils.MD5Util;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/static/**"); //过滤静态资源
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");
        //开启自动配置的登录功能。效果：如果没有登录就来到登录页面
        http.formLogin().loginPage("/userlogin");
        /**
         * 1./login请求来到登录页
         * 2.重定向到/login?error表示登录失败
         * 3.更多详细规定
         *
         * 4.默认post形式的/login代表处理登陆
         * 5.一旦定制loginPage，那么loginPage的post请求就是登陆.loginPage("/userlogin")
         */

        //开启自动配置注销功能
        http.logout().logoutSuccessUrl("/");
        /**
         * 1.访问/logout表示用户注销，清空session
         * 2.注销成功会返回/login?loginout界面，可以通过.logoutSuccessUrl("/")指定退出后返回界面
         */

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");//保存在cookie中，时间限制14天免登录。点击注销就删除了Cookie


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("wangwu").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1","VIP3")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1","VIP2")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP3","VIP2");
        auth.userDetailsService(customUserService().passwordEncoder(new PasswordEncoder(){
            @Override
            public boolean upgradeEncoding(String encodedPassword) {
                return false;
            }

            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String)rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String)rawPassword));
            }
        })); //3
    }
}

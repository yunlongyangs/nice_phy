package cn.nice.nice_phy.config;

import cn.nice.nice_phy.utils.IdWorker;
import cn.nice.nice_phy.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtUtil jwtUtil(){

        return new JwtUtil();
    }

}

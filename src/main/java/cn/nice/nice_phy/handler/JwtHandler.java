package cn.nice.nice_phy.handler;

import cn.nice.nice_phy.codesenum.NiceCode;
import cn.nice.nice_phy.exception.NiceException;
import cn.nice.nice_phy.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtHandler extends HandlerInterceptorAdapter{
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头
        final String author = request.getHeader("Cookie");
        //验证
        if(author!=null && author.startsWith("Nice ")){
            try {
                //获取新的
                final String newAuthor = author.substring(5);
                try {
                    Claims claims = jwtUtil.parseJWT(newAuthor);
                    if(claims!=null){
                        if("user".equals(claims.get("roles"))){
                            log.info("通过:"+claims);
                            request.setAttribute("user",claims);
                            return true;
                        }

                    }
                }catch (NiceException e){
                    response.sendRedirect("http://localhost:8888/login.html");
                }


            }catch (NiceException e){
                response.sendRedirect("http://localhost:8888/login.html");
            }
        }
        response.sendRedirect("http://localhost:8888/login.html");
        return false;
    }

}

package cn.nice.nice_phy.controller.user;

import cn.nice.nice_phy.pojo.user.NiceUser;
import cn.nice.nice_phy.service.user.NiceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class NiceUserController {
    @Autowired
    private NiceUserService niceUserService;

    /**
     * 验证用户
     * @return emial
     */
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkUserData(@RequestParam("email") String email){
        return ResponseEntity.ok(niceUserService.checkUserData(email));
    }

    /**
     * 注册
     * @param niceUser
     * @return
     */
    @PostMapping("/registry")
    public ResponseEntity<Boolean> registry(NiceUser niceUser){
        return ResponseEntity.ok(niceUserService.registry(niceUser));
    }

    /**
     * 登录
     * @param niceUser
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(NiceUser niceUser){
        return ResponseEntity.ok(niceUserService.login(niceUser));
    }


}



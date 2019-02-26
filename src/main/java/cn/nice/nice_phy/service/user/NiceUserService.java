package cn.nice.nice_phy.service.user;

import cn.nice.nice_phy.codesenum.NiceCode;
import cn.nice.nice_phy.exception.NiceException;
import cn.nice.nice_phy.mapper.user.NiceUserMapper;
import cn.nice.nice_phy.pojo.user.NiceUser;
import cn.nice.nice_phy.utils.IdWorker;
import cn.nice.nice_phy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class NiceUserService {
    @Autowired
    private NiceUserMapper niceUserMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 验证用户
     *
     * @param emial
     */
    public Boolean checkUserData(String emial) {
        try {
            NiceUser niceUser = new NiceUser();
            niceUser.setEmail(emial);
            return this.niceUserMapper.selectCount(niceUser) == 0;

        } catch (NiceException e) {
            throw new NiceException(NiceCode.ERROR);
        }

    }

    /**
     * 注册
     *
     * @param niceUser
     */
    public Boolean registry(NiceUser niceUser) {
        try {
            niceUser.setId(idWorker.nextId() + "");
            niceUser.setLogin_count(1);
            niceUser.setPassword(bCryptPasswordEncoder.encode(niceUser.getPassword()));
            return niceUserMapper.insert(niceUser) == 1;
        } catch (NiceException e) {
            throw new NiceException(NiceCode.REQUEST_BAD);
        }


    }

    /**
     * 登录
     *
     * @param niceUser
     * @return
     */
    public Map<String, String> login(NiceUser niceUser) {
        Map<String, String> map = new HashMap<>();
        NiceUser nu = new NiceUser();
        nu.setEmail(niceUser.getEmail());
        NiceUser selectOne = niceUserMapper.selectOne(nu);
        if (selectOne == null) {
               map.put("code",NiceCode.UNAME_ERROR.getCode() + "");
                map.put("error", NiceCode.UNAME_ERROR.getMessage());
        } else {
            if (!bCryptPasswordEncoder.matches(niceUser.getPassword(), selectOne.getPassword())) {
                map.put("code",NiceCode.PASSWORD_ERROR.getCode() + "");
                map.put("error", NiceCode.PASSWORD_ERROR.getMessage());
            }

            String jwt = jwtUtil.createJWT(selectOne.getId(), selectOne.getEmail(), "user");

            map.put("token", jwt);
            map.put("email", selectOne.getEmail());

        }
        return map;

    }

}

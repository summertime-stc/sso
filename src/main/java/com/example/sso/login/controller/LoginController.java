package com.example.sso.login.controller;

import com.example.sso.login.Dao.UserDao;
import com.example.sso.login.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@Api(tags = "sso")
public class LoginController {

    @Autowired
    private UserDao userDao;

    @ApiOperation(value = "登录页面")
    @RequestMapping("/loginhtml")
    public String loginhtml(){
        return "login";
    }


    @ApiOperation(value = "登录")
    @RequestMapping("/login")
    public String login(@Param("username") String username,@Param("password") String password,HttpServletResponse response){

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 计算md5函数
        md.update(password.getBytes());
        // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
        password=new BigInteger(1, md.digest()).toString(16);

        User user=userDao.existuser(username,password);
        if(user==null){
            System.out.println("用户不存在");
            return "error";
        }
        else {
            System.out.println("登录成功");

            //生成token
            Map<String, Object> map = new HashMap<>(1);
            map.put(username, user); //后面可以换成用户实体
            String jwt =  Jwts.builder()
                    .setClaims(map)
                    .setSubject(user.getName()) //用户名
                    .setExpiration(new Date(System.currentTimeMillis() + 3600*1000)) //token保留的时间
                    .signWith(SignatureAlgorithm.HS512, "123456") //
                    .compact();
            System.out.println(jwt);

            //设置cookie
            Cookie cookie_token = new Cookie("token",jwt);
            cookie_token.setMaxAge(60*60*60);
            cookie_token.setPath("/");
            cookie_token.setDomain("localhost");
            response.addCookie(cookie_token);
        }
        return "test";
    }
}

package cn.mtttt.controller;

import cn.mtttt.bean.ResultRes;
import cn.mtttt.bean.SchoolRoll;
import cn.mtttt.service.LatterService;
import cn.mtttt.service.OriginService;
import cn.mtttt.utils.MyJasyptUtils;
import cn.mtttt.utils.VHelper;
import com.alibaba.fastjson2.JSON;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class F101 {
    @Autowired
    LatterService latterService;
    @Autowired
    OriginService originService;
    static MyJasyptUtils myJasyptUtils;
    static{
        myJasyptUtils = new MyJasyptUtils();
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String loginPage(){
        VHelper vHelper = new VHelper("F101/index.html");
        return vHelper.render();
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletResponse response, @RequestBody Map<String,Object> params){
        String Sno = params.get("sno").toString();
        String password_pre = params.get("password").toString();
        SchoolRoll schoolRoll = originService.getSchoolRoll(Sno);
        if(schoolRoll != null){
            StringEncryptor encryptor = myJasyptUtils.getEncryptor();
            if(schoolRoll.getPassword().equals(password_pre.trim())){
                Cookie sno = new Cookie("Sno", encryptor.encrypt(Sno));
                sno.setPath("/");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.addCookie(sno);
                return JSON.toJSONString(new ResultRes(200,true));
            }else{
                return JSON.toJSONString(new ResultRes(404,false));
            }
        }else{
            return JSON.toJSONString(new ResultRes(404,false));
        }
    }
}

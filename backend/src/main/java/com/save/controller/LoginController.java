package com.save.controller;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.save.controller.login.UtilsToken;
import com.save.entity.Volunteer;
import com.save.service.VolunteerService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/app")
public class LoginController {
    /**
     * 服务对象
     */
    @Resource
    private VolunteerService volunteerService;


    @RequestMapping("selectByToken")
    public JSONObject test(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String token = request.getParameter("token");
        Volunteer volunteer = volunteerService.queryById(UtilsToken.tokenToId(token));
        json.put("isok", true);
        json.put("data", volunteer);
        return json;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject userLogin(HttpServletRequest request) throws JSONException {
        JSONObject json = new JSONObject();
        String userName = request.getParameter("userName");
        String password = request.getParameter("pwd");
        if (userName == null || password == null)
        {
            json.put("isok",false);
            json.put("token", null);
            return json;
        }

        System.out.println(userName+ password);
        try {
            Volunteer volunteer = new Volunteer();
            volunteer.setUsername(userName);
            volunteer.setPwd(password);
            List<Volunteer> volunteers = volunteerService.queryAll(volunteer);
            System.out.println(volunteers.get(0).getPwd());

            if (password.equals(volunteers.get(0).getPwd())) {
                //生成token返回
                                String token = UtilsToken.getToken(volunteers.get(0).getId());
                                json.put("isok", true);
                                json.put("token", token);
            }
        } catch (Exception exception) {
            json.put("isok", false);
            json.put("token", null);
            System.out.println(exception);
        }

        return json;
    }


}
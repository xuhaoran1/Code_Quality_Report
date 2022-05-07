package com.save.controller;

import com.alibaba.fastjson.JSONObject;

import com.save.entity.TaskGroup;
import com.save.entity.Volunteer;
import com.save.service.TaskGroupService;
import com.save.service.VolunteerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * (TaskGroup)表控制层
 *
 * @author makejava
 * @since 2021-01-05 15:39:05
 */
@RestController
@RequestMapping("/app")
public class TaskGroupController {
    /**
     * 服务对象
     */
    @Resource
    private TaskGroupService taskGroupService;

    @Resource
    private VolunteerService volunteerService;

    public JSONObject noTeammate(){
        JSONObject json = new JSONObject();
        json.put("msg", "当前还没有其他队员接受任务");
        json.put("isok", "false");
        return json;
    }
    public JSONObject haveTeammate(Integer userId){
        JSONObject json = new JSONObject();
        Volunteer teamMate = volunteerService.queryById(userId);
        json.put("msg", "返回队员信息");
        json.put("isok", "true");
        json.put("address",teamMate.getAddress());
        json.put("name",teamMate.getName());
        json.put("telephone",teamMate.getTelephone());
        return json;
    }
    @GetMapping("/getTm")
    public JSONObject getTm(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Integer userId = (Integer)request.getAttribute("userId");
            Volunteer volunteer = volunteerService.queryById(userId);
            Integer status = volunteer.getStatus();
            if(status==0){//当前不在任务中
                json.put("msg", "当前没有接收任务，没有任务小组");
                json.put("isok", "false");
                return json;
            }
            TaskGroup taskGroup = taskGroupService.queryById(status);
            if(taskGroup.getMem1Id().equals(userId)){
                if(taskGroup.getMem2Id()==null){
                    return noTeammate();
                }
                return haveTeammate(taskGroup.getMem2Id());
            }
            return haveTeammate(taskGroup.getMem1Id());

        }catch (Exception exception) {
            json.put("msg", "查询失败！");
            json.put("isok", "false");
        }
        return json;
    }

}
package com.save.controller;

import com.alibaba.fastjson.JSONObject;
import com.save.entity.Route;
import com.save.entity.TaskGroup;
import com.save.entity.Volunteer;
import com.save.service.RouteService;
import com.save.service.TaskGroupService;
import com.save.service.VolunteerService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * (Volunteer)表控制层
 *
 * @author makejava
 * @since 2021-01-04 12:32:40
 */
@RestController
@RequestMapping("/app")
public class VolunteerController {
    /**
     * 服务对象
     */
    @Resource
    private VolunteerService volunteerService;

    @Resource
    private TaskGroupService taskGroupService;

    @Resource
    private RouteService routeService;


    @GetMapping("/getOtherHisXy")
    public JSONObject getOtherHisXy(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            //获得同任务其他人的id
            Integer userId = (Integer) request.getAttribute("userId");
            Volunteer requester = volunteerService.queryById(userId);
            Integer grouperId = requester.getStatus();
            TaskGroup taskGroup = taskGroupService.queryById(grouperId);
            Integer taskId = taskGroup.getTaskId();
            TaskGroup AlltaskGroup = new TaskGroup();
            AlltaskGroup.setTaskId(taskId);
            List<TaskGroup> taskGroups = taskGroupService.queryAll(AlltaskGroup);
            HashSet<Integer> volIdSet = new HashSet<>();
            for (TaskGroup group: taskGroups) {
                volIdSet.add(group.getMem1Id());
                volIdSet.add(group.getMem2Id());
            }
//            volIdSet.remove(userId);//自己的经纬度不用返回
            List<Integer> volIdList = new ArrayList<>(volIdSet);
            List<Route> otherHisXy = routeService.getOtherHisXy(volIdList,taskId);


            HashMap<Integer, List<String>> hashMap = new HashMap<>();
            for(Route route:otherHisXy){
                Integer volId = route.getVolId();
                String content = route.getContent();
                if(!hashMap.containsKey(volId)){
                    ArrayList<String> list = new ArrayList<>();
                    list.add(content);
                    hashMap.put(volId, list);
                }else{
                    List<String> list = hashMap.get(volId);
                    list.add(content);
                    hashMap.put(volId, list);
                }
            }

            json.put("isok", true);
            json.put("msg", "获取成功");
            json.put("data", hashMap);
        }catch (Exception e){
            e.printStackTrace();
            json.put("isok", false);
            json.put("msg", "获取失败");
        }

        return json;
    }

    /**
     * 前端控制有任务才会请求该接口，所以没有做判空
     * @param request
     * @return
     */
    @GetMapping("/getOtherXy")
    public JSONObject getOtherXy(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{
            Integer userId = (Integer) request.getAttribute("userId");
            Volunteer requester = volunteerService.queryById(userId);
            Integer grouperId = requester.getStatus();
            TaskGroup taskGroup = taskGroupService.queryById(grouperId);
            Integer taskId = taskGroup.getTaskId();
            TaskGroup AlltaskGroup = new TaskGroup();
            AlltaskGroup.setTaskId(taskId);
            List<TaskGroup> taskGroups = taskGroupService.queryAll(AlltaskGroup);
            HashSet<Integer> volIdSet = new HashSet<>();
            for (TaskGroup group: taskGroups) {
                volIdSet.add(group.getMem1Id());
                volIdSet.add(group.getMem2Id());
            }
            volIdSet.remove(userId);//自己的经纬度不用返回
            List<Integer> volIdList = new ArrayList<>(volIdSet);
            List<Volunteer> volunteers = volunteerService.getOtherXy(volIdList);
            List<String> addressList = new ArrayList<>();
            for(Volunteer vol:volunteers){
                addressList.add(vol.getAddress());
            }
            json.put("data", addressList);
            json.put("isok", true);
            json.put("msg","获取成功");
        }catch (Exception e){
            e.printStackTrace();
            json.put("isok", false);
            json.put("msg","获取失败");
        }
        return json;
    }

    @PostMapping("/uploadXy")
    @Transactional
    public JSONObject uploadXy(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{
            //更新vol表的最新位置
            Volunteer volunteer = new Volunteer();
            String address = request.getParameter("address");
            volunteer.setAddress(address);
            Integer userId = (Integer) request.getAttribute("userId");
            volunteer.setId(userId);
            volunteerService.update(volunteer);
            //更新route表的历史路径
            Volunteer volunteerToUpload = volunteerService.queryById(userId);
            Integer groupId = volunteerToUpload.getStatus();
            TaskGroup taskGroup = taskGroupService.queryById(groupId);
            Integer taskId = taskGroup.getTaskId();
            Route route = new Route();
            route.setTaskId(taskId);
            route.setContent(address);
            route.setVolId(userId);
            route.setCreateTime(new Date());
            routeService.insert(route);
            json.put("isok", true);
            json.put("msg","上传成功");
        }catch (Exception e){
            e.printStackTrace();
            json.put("isok", false);
            json.put("msg","上传失败");
        }

        return json;
    }



}
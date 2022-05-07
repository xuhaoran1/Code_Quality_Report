package com.save.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.save.entity.OutStandard;
import com.save.entity.Task;
import com.save.entity.TaskGroup;
import com.save.entity.Volunteer;
import com.save.service.OutStandardService;
import com.save.service.TaskGroupService;
import com.save.service.TaskService;
import com.save.service.VolunteerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 1.该表的字段可以通过后端自由管理
 * 2.一条记录是一个走失的老人
 * (Task)表控制层
 *
 * @author makejava
 * @since 2021-01-05 15:39:02
 */
@RestController
@RequestMapping("/app")
public class TaskController {
    /**
     * 服务对象
     */
    @Resource
    private TaskService taskService;
    @Resource
    private VolunteerService volunteerService;
    @Resource
    private TaskGroupService taskGroupService;
    @Resource
    private OutStandardService outStandardService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Task selectOne(Integer id) {
        return this.taskService.queryById(id);
    }


    public boolean judgeLevel(Task task, Integer hour) {
        final int hour2min=60;
        final int min2sec=60;
        final int sec2ms=1000;
        Date currentDate = new Date();
        Date lostDate = task.getLostTime();
        return currentDate.getTime() - lostDate.getTime() >= hour * hour2min * min2sec * sec2ms;
    }

    public boolean judgeDis(double n1, double e1, double n2, double e2) {

        final double jl_jd = 102834.74258026089786013677476285;//每经度单位米
        final double jl_wd = 111712.69150641055729984301412873;//每纬度单位米
        final double km2m = 1000;
        double b = Math.abs((e1 - e2) * jl_jd);
        double a = Math.abs((n1 - n2) * jl_wd);
        double distance = Math.sqrt((a * a + b * b));
        return distance <= 10 * km2m; //可以实现，是否需要实现？
    }

    public JSONObject getJson(Task task) {
        String gender;
        if (task.getTargetGender() == 1) gender = "女";
        else gender = "男";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("target_name", task.getTargetName());
        jsonObject.put("target_health", task.getTargetHealth());
        jsonObject.put("target_appearance", task.getTargetAppearance());
        jsonObject.put("target_cloth", task.getTargetCloth());
        jsonObject.put("lost_position", task.getLostPosition());
        jsonObject.put("target_gender", gender);
        jsonObject.put("lost_time", task.getLostTime());
        jsonObject.put("task_id", task.getId());
        jsonObject.put("target_age",task.getTargetAge());
//        jsonObject.put("task_id", task.getId());
        jsonObject.put("level", task.getLevel());

        return jsonObject;
    }


    @RequestMapping(value = "/getJob", method = RequestMethod.POST)
    public JSONObject SelectJob(HttpServletRequest request) throws JSONException {
        List<JSONObject> jsonObjectList = new ArrayList<>();
        JSONObject json = new JSONObject();
//        Integer userId = (Integer) request.getAttribute("userId");
        double userLongitude = Double.parseDouble(request.getParameter("longitude"));
        double userLatitude = Double.parseDouble(request.getParameter("latitude"));
        OutStandard outStandard = outStandardService.queryLast();
        try {//最好还是分开来查，遍历感觉不太好
            Task testTask = new Task();
            List<Task> tasks = taskService.queryAll(testTask);

            for (Task task : tasks) {
                String location = task.getLostPosition();
                String[] longlatitude = location.split(",");
                double elderLongitude = Double.parseDouble(longlatitude[0]);
                double elderLatitude = Double.parseDouble(longlatitude[1]);

                if (!judgeDis(userLatitude, userLongitude, elderLatitude, elderLongitude)) continue;//不在范围内任务不再判断

                if (task.getLevel() != 1) {
                    if (judgeLevel(task, outStandard.getLostHour())) {//state缺省为0，升级时不改变

                        Task updatetask = new Task();
                        updatetask.setId(task.getId());
                        updatetask.setLevel(1);//升级信息：任务级别，接收人数，起始时间
                        updatetask.setTaskAcceptNum(0);
                        updatetask.setTaskStartTime(new Date());
                        taskService.update(updatetask);
                        //当前任务就应该要改变
                        task.setLevel(1);//升级信息：任务级别，接收人数，起始时间
                        task.setTaskAcceptNum(0);
                        task.setTaskStartTime(new Date());
                        System.out.println(1);
                    }
                    JSONObject j = getJson(task);
                    jsonObjectList.add(j);
                    continue;
                }
                if (task.getState() != 1) {//一级任务的信息判断状态是否结束，尚未结束的话
                    JSONObject j = getJson(task);//直接添加就可以了
//                    System.out.println(j);+
                    jsonObjectList.add(j);
                }
            }

            json.put("data", jsonObjectList);
            json.put("isok", true);
            json.put("msg", "任务信息已经返回");

        } catch (Exception exception) {
            json.put("isok", false);
            json.put("msg", "没有相关任务信息");
            exception.printStackTrace();
        }

        return json;
    }


    @RequestMapping(value = "/getReJob", method = RequestMethod.GET)
    public JSONObject SelectReJob(HttpServletRequest request) throws JSONException{
        JSONObject json = new JSONObject();
        Integer userId = (Integer) request.getAttribute("userId");
        System.out.println(userId);
        try {
            Volunteer volunteer = volunteerService.queryById(userId);
            Integer status = volunteer.getStatus();
            if (status == 0) {
                json.put("isok", false);
                json.put("msg", "没有相关任务信息，请先接受任务");
                return json;
            }
            TaskGroup taskGroup = taskGroupService.queryById(volunteer.getStatus());//我需要测试是否能够
            Task task = taskService.queryById(taskGroup.getTaskId());
            Integer gender = task.getTargetGender();
            if (gender == 0) json.put("gender", '男');
            else json.put("target_gender", '女');
            json.put("lost_position", task.getLostPosition());
//            json.put("task_id", task.getId());
            json.put("target_cloth", task.getTargetCloth());
            json.put("target_appearance", task.getTargetAppearance());
            json.put("lost_time", task.getLostTime());
            json.put("target_name", task.getTargetName());
            json.put("family_contact", task.getFamilyContact());
            json.put("image_url", task.getImageUrl());
            json.put("target_age",task.getTargetAge());
            json.put("isok", true);
            json.put("msg", "任务信息已返回");
        } catch (Exception exception) {
            json.put("isok", false);
            json.put("msg", "没有相关任务信息");
            exception.printStackTrace();
        }

        return json;
    }

    @RequestMapping(value = "/getTask", method = RequestMethod.POST)
    public JSONObject getTask(HttpServletRequest request) throws JSONException {
        JSONObject json = new JSONObject();
        Integer userId = (Integer) request.getAttribute("userId");
        Integer taskId = Integer.parseInt(request.getParameter("task_id"));
        Integer vGroupId;
        Volunteer testVolunteer = volunteerService.queryById(userId);
        if(testVolunteer.getStatus()!=0){
            json.put("isok", true);
            json.put("msg", "当前有任务还未完成，不能再接收任务");
            return json;
        }
        try {
            Task task = taskService.queryById(taskId);
//            task.setState(0);
            if (task.getTaskAcceptNum() % 2 == 1) {//奇数人数
                TaskGroup testTaskGroup = new TaskGroup();
                testTaskGroup.setTaskId(taskId);
                List<TaskGroup> taskGroups = taskGroupService.queryAll(testTaskGroup);
                TaskGroup taskGroup = taskGroups.get(taskGroups.size() - 1);
                taskGroup.setMem2Id(userId);
                taskGroup = taskGroupService.update(taskGroup);
                vGroupId = taskGroup.getId();
            } else {
                TaskGroup taskGroup = new TaskGroup();
                taskGroup.setTaskId(taskId);
                taskGroup.setMem1Id(userId);
                taskGroup.setCreateTime(new Date());
                taskGroup = taskGroupService.insert(taskGroup);
                vGroupId = taskGroup.getId();
            }
            Volunteer updateVolunteer = new Volunteer();
            updateVolunteer.setId(userId);
            updateVolunteer.setStatus(vGroupId);
            volunteerService.update(updateVolunteer);
            Task updateTask = new Task();
            if (task.getState() == -1) updateTask.setState(0);
            updateTask.setId(taskId);
            updateTask.setTaskAcceptNum(task.getTaskAcceptNum() + 1);
            taskService.update(updateTask);
            json.put("isok", true);
            json.put("msg", "成功接受任务");
        } catch (Exception exception) {
            json.put("isok", false);
            json.put("msg", "未成功接受任务");
            exception.printStackTrace();
        }

        return json;
    }

    @RequestMapping(value = "/commitJob", method = RequestMethod.GET)
    public JSONObject commitJob(HttpServletRequest request) throws JSONException {
        JSONObject json = new JSONObject();
        Integer userId = (Integer) request.getAttribute("userId");
        try {
            Volunteer volunteer = volunteerService.queryById(userId);
            if(volunteer.getStatus()==0){
                json.put("isok", false);
                json.put("msg", "您当前没有任务可以查询，先去接收任务");
            }
            else{
                TaskGroup taskGroup = taskGroupService.queryById(volunteer.getStatus());//我需要测试是否能够
                Task task = taskService.queryById(taskGroup.getTaskId());
                if(task.getState()==1){
                    json.put("isok",true);
                    json.put("msg","当前任务已经完成，可以前往确认");
                    return json;
                }
                if(task.getState()==2){
                    json.put("isok", true);
                    json.put("msg", "任务状态已变更为待确认");
                    return json;
                }
                Task updateTask = new Task();
                updateTask.setId(task.getId());
                if(task.getState()==0)updateTask.setState(2);
                taskService.update(updateTask);
                json.put("isok", true);
                json.put("msg", "任务状态已变更为待确认");
            }

        } catch (Exception exception) {
            json.put("isok", false);
            json.put("msg", "该用户不存在");
            exception.printStackTrace();
        }

        return json;
    }

    @RequestMapping(value = "/jobIsOk", method = RequestMethod.GET)
    public JSONObject jobIsOk(HttpServletRequest request) throws JSONException {
        JSONObject json = new JSONObject();
        Integer userId = (Integer) request.getAttribute("userId");
        try {
            Volunteer volunteer = volunteerService.queryById(userId);
            if(volunteer.getStatus()==0){
                json.put("isok", true);
                json.put("isFinish",false);
                json.put("msg", "您当前没有任务可以查询，先去接收任务");
            }
            else{
                TaskGroup taskGroup = taskGroupService.queryById(volunteer.getStatus());//我需要测试是否能够
                Task task = taskService.queryById(taskGroup.getTaskId());
                if(task.getState()==1){
                    Volunteer updateVolunteer = new Volunteer();
                    updateVolunteer.setId(userId);
                    updateVolunteer.setStatus(0);
                    volunteerService.update(updateVolunteer);
                    json.put("isok", true);
                    json.put("isFinish",true);
                    json.put("msg", "任务已结束");
                }else{
                    if(task.getState()==2){
                        json.put("isok", true);
                        json.put("isFinish",false);
                        json.put("msg", "任务状态等待管理员确认");
                    }else{
                        json.put("isok", true);
                        json.put("isFinish",false);
                        json.put("msg", "任务仍然未结束");
                    }
                }

            }

        } catch (Exception exception) {
            json.put("isok", false);
            json.put("msg", "该用户不存在");
            exception.printStackTrace();
        }

        return json;
    }

    @RequestMapping(value = "/getAcTaskid", method = RequestMethod.GET)
    public JSONObject getAcTaskid(HttpServletRequest request) throws JSONException {
        JSONObject json = new JSONObject();
        Integer userId = (Integer) request.getAttribute("userId");
        try {
            Volunteer volunteer = volunteerService.queryById(userId);
            if(volunteer.getStatus()==0){
                json.put("isok", true);
                json.put("msg", "您当前没有任务可以查询，先去接收任务");
            }
            else{
                TaskGroup taskGroup = taskGroupService.queryById(volunteer.getStatus());//我需要测试是否能够
                json.put("isok", true);
                json.put("msg", "taskId已经返回");
                json.put("taskid",taskGroup.getTaskId());
            }

        } catch (Exception exception) {
            json.put("isok", false);
            exception.printStackTrace();
        }

        return json;
    }




    @RequestMapping(value = "/getTimes", method = RequestMethod.GET)
    public JSONObject getTimes(HttpServletRequest request) throws JSONException {
        JSONObject json = new JSONObject();
        Integer userId = (Integer) request.getAttribute("userId");
        try {
            int num = 0;
            TaskGroup test = new TaskGroup();
            test.setMem1Id(userId);
            List<TaskGroup> list = taskGroupService.queryAll(test);
            num = num+list.size();
            TaskGroup test2 = new TaskGroup();
            test2.setMem2Id(userId);
            List<TaskGroup> list2 = taskGroupService.queryAll(test2);
            num = num + list2.size();
            json.put("times",num);
            json.put("isok",true);
            json.put("msg","出队次数已返回");
        } catch (Exception exception) {
            json.put("isok", false);
            json.put("msg", "该用户不存在");
            exception.printStackTrace();
        }

        return json;
    }

}
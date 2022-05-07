package com.save.controller;

import com.alibaba.fastjson.JSONObject;
import com.save.entity.Clue;
import com.save.entity.FamClue;
import com.save.entity.TaskGroup;
import com.save.service.ClueService;
import com.save.service.FamClueService;
import com.save.service.TaskGroupService;
import com.save.service.VolunteerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * (Clue)表控制层
 *
 * @author makejava
 * @since 2021-01-05 10:58:13
 */
@RestController
@RequestMapping("/app")
public class ClueController {
    /**
     * 服务对象
     */
    @Resource
    private ClueService clueService;
    @Resource
    private VolunteerService volunteerService;
    @Resource
    private TaskGroupService taskGroupService;
    @Resource
    private FamClueService famClueService;

    /**
     * 通过主键查询单条数据
     *
     *
     * @return 单条数据
     */
    @GetMapping("/getNewClue")
    public JSONObject getNewClue(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        System.out.println("123");
        try {

            int userid=Integer.parseInt(request.getAttribute("userId").toString());
            System.out.println(userid);
            int groupid=Integer.parseInt(this.volunteerService.queryById(userid).getStatus().toString());
            System.out.println(groupid);
            TaskGroup taskGroup=this.taskGroupService.queryById(groupid);
            int taskid=taskGroup.getTaskId();

            System.out.println(taskid);
            List<Clue> vclues = this.clueService.queryByGroupId(groupid);

            List<FamClue> fclues=this.famClueService.queryByTaskId(taskid);
            Collections.sort(vclues, new Comparator<Clue>() {
                @Override
                public int compare(Clue o1, Clue o2) {
                    int flag = o1.getCreateTime().compareTo(o2.getCreateTime());
                    return -flag;
                }
            });
            Collections.sort(fclues, new Comparator<FamClue>() {
                @Override
                public int compare(FamClue o1, FamClue o2) {
                    int flag = o1.getCreateTime().compareTo(o2.getCreateTime());
                    return -flag;
                }
            });

            json.put("msg", "查询成功！");
            json.put("isok", "true");
            json.put("fdata", fclues);
            json.put("vdata",vclues);
        }catch (Exception exception) {
            json.put("msg", "查询失败！");
            json.put("isok", "false");
        }
        return json;
    }

    @PostMapping("/setClue")
    public JSONObject setClue(HttpServletRequest request) {
        int userId=Integer.parseInt(request.getAttribute("userId").toString());
        String content=request.getParameter("clue");
        JSONObject json = new JSONObject();
        Clue clue=new Clue();
        try {
            clue.setGroupId(this.volunteerService.queryById(userId).getStatus());
            clue.setContent(content);
            clue.setCreateTime(new Date());
            this.clueService.insert(clue);
            json.put("isok","true");
            json.put("msg","上传成功！");
        }catch (Exception exception) {
            json.put("isok","false");
            json.put("msg","上传失败！");
        }
        return json;
    }

}
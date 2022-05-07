package com.save.controller;

import com.save.entity.FamClue;
import com.save.service.FamClueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (FamClue)表控制层
 *
 * @author makejava
 * @since 2021-01-06 08:42:33
 */
@RestController
@RequestMapping("famClue")
public class FamClueController {
    /**
     * 服务对象
     */
    @Resource
    private FamClueService famClueService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")

    public FamClue selectOne(Integer id) {
        return this.famClueService.queryById(id);
    }

}
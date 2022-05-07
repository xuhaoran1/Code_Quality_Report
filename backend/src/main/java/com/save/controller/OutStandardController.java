package com.save.controller;

import com.save.entity.OutStandard;
import com.save.service.OutStandardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (OutStandard)表控制层
 *
 * @author makejava
 * @since 2021-01-05 16:21:15
 */
@RestController
@RequestMapping("outStandard")
public class OutStandardController {
    /**
     * 服务对象
     */
    @Resource
    private OutStandardService outStandardService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public OutStandard selectOne(Integer id) {
        return this.outStandardService.queryById(id);
    }

}
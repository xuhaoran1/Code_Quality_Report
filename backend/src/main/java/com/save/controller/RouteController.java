package com.save.controller;

import com.save.entity.Route;
import com.save.service.RouteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Route)表控制层
 *
 * @author makejava
 * @since 2021-01-11 14:54:28
 */
@RestController
@RequestMapping("route")
public class RouteController {
    /**
     * 服务对象
     */
    @Resource
    private RouteService routeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Route selectOne(Integer id) {
        return this.routeService.queryById(id);
    }

}
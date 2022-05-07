package com.save.service;

import com.save.entity.Route;

import java.util.HashSet;
import java.util.List;

/**
 * (Route)表服务接口
 *
 * @author makejava
 * @since 2021-01-11 14:54:28
 */
public interface RouteService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Route queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Route> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param route 实例对象
     * @return 实例对象
     */
    Route insert(Route route);

    /**
     * 修改数据
     *
     * @param route 实例对象
     * @return 实例对象
     */
    Route update(Route route);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<Route> getOtherHisXy(List<Integer> volIdList,Integer taskId);
}
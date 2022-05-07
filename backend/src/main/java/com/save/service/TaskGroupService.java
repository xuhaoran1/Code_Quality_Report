package com.save.service;

import com.save.entity.Task;
import com.save.entity.TaskGroup;

import java.util.List;

/**
 * (TaskGroup)表服务接口
 *
 * @author makejava
 * @since 2021-01-05 15:39:05
 */
public interface TaskGroupService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TaskGroup queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TaskGroup> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param taskGroup 实例对象
     * @return 实例对象
     */
    TaskGroup insert(TaskGroup taskGroup);

    /**
     * 修改数据
     *
     * @param taskGroup 实例对象
     * @return 实例对象
     */
    TaskGroup update(TaskGroup taskGroup);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<TaskGroup> queryAll(TaskGroup taskGroup);

}
package com.save.service;

import com.save.entity.Task;
import com.save.entity.Volunteer;

import java.util.List;

/**
 * 1.该表的字段可以通过后端自由管理
 * 2.一条记录是一个走失的老人
 * (Task)表服务接口
 *
 * @author makejava
 * @since 2021-01-05 15:39:02
 */
public interface TaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Task queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Task> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    Task insert(Task task);

    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    Task update(Task task);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<Task> queryAll(Task task);

}
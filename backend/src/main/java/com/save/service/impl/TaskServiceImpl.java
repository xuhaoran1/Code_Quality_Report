package com.save.service.impl;

import com.save.dao.TaskDao;
import com.save.entity.Task;
import com.save.entity.Volunteer;
import com.save.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 1.该表的字段可以通过后端自由管理
 * 2.一条记录是一个走失的老人
 * (Task)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 15:39:02
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskDao taskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Task queryById(Integer id) {
        return this.taskDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Task> queryAllByLimit(int offset, int limit) {
        return this.taskDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public Task insert(Task task) {
        this.taskDao.insert(task);
        return task;
    }

    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public Task update(Task task) {
        this.taskDao.update(task);
        return this.queryById(task.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.taskDao.deleteById(id) > 0;
    }

    @Override
    public List<Task> queryAll(Task task) {
        List<Task> tasks = taskDao.queryAll(task);
        return tasks;
    }
}
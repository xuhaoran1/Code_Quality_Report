package com.save.service.impl;

import com.save.dao.TaskGroupDao;
import com.save.entity.Task;
import com.save.entity.TaskGroup;
import com.save.service.TaskGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TaskGroup)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 15:39:05
 */
@Service("taskGroupService")
public class TaskGroupServiceImpl implements TaskGroupService {
    @Resource
    private TaskGroupDao taskGroupDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TaskGroup queryById(Integer id) {
        return this.taskGroupDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TaskGroup> queryAllByLimit(int offset, int limit) {
        return this.taskGroupDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param taskGroup 实例对象
     * @return 实例对象
     */
    @Override
    public TaskGroup insert(TaskGroup taskGroup) {
        this.taskGroupDao.insert(taskGroup);
        return taskGroup;
    }

    /**
     * 修改数据
     *
     * @param taskGroup 实例对象
     * @return 实例对象
     */
    @Override
    public TaskGroup update(TaskGroup taskGroup) {
        this.taskGroupDao.update(taskGroup);
        return this.queryById(taskGroup.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.taskGroupDao.deleteById(id) > 0;
    }

    @Override
    public List<TaskGroup> queryAll(TaskGroup taskGroup) {
        return this.taskGroupDao.queryAll(taskGroup);
    }
}
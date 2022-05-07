package com.save.service;

import com.save.entity.Volunteer;

import java.util.List;

/**
 * (Volunteer)表服务接口
 *
 * @author makejava
 * @since 2021-01-05 15:56:47
 */
public interface VolunteerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Volunteer queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Volunteer> queryAllByLimit(int offset, int limit);

    List<Volunteer> queryAll(Volunteer volunteer);
    /**
     * 新增数据
     *
     * @param volunteer 实例对象
     * @return 实例对象
     */
    Volunteer insert(Volunteer volunteer);

    /**
     * 修改数据
     *
     * @param volunteer 实例对象
     * @return 实例对象
     */
    Volunteer update(Volunteer volunteer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<Volunteer> getOtherXy(List<Integer> volIdList);
}
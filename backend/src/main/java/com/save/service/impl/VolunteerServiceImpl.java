package com.save.service.impl;

import com.save.entity.Volunteer;
import com.save.dao.VolunteerDao;
import com.save.service.VolunteerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Volunteer)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 15:56:48
 */
@Service("volunteerService")
public class VolunteerServiceImpl implements VolunteerService {

    @Resource
    private VolunteerDao volunteerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Volunteer queryById(Integer id) {
        return this.volunteerDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Volunteer> queryAllByLimit(int offset, int limit) {
        return this.volunteerDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Volunteer> queryAll(Volunteer volunteer) {
        return this.volunteerDao.queryAll(volunteer);
    }

    /**
     * 新增数据
     *
     * @param volunteer 实例对象
     * @return 实例对象
     */
    @Override
    public Volunteer insert(Volunteer volunteer) {
        this.volunteerDao.insert(volunteer);
        return volunteer;
    }

    /**
     * 修改数据
     *
     * @param volunteer 实例对象
     * @return 实例对象
     */
    @Override
    public Volunteer update(Volunteer volunteer) {
        this.volunteerDao.update(volunteer);
        return this.queryById(volunteer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.volunteerDao.deleteById(id) > 0;
    }

    @Override
    public List<Volunteer> getOtherXy(List<Integer> volIdList) {
        return this.volunteerDao.getOtherXy(volIdList);
    }
}
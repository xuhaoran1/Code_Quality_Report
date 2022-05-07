package com.save.service.impl;

import com.save.dao.OutStandardDao;
import com.save.entity.OutStandard;
import com.save.service.OutStandardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (OutStandard)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 16:21:15
 */
@Service("outStandardService")
public class OutStandardServiceImpl implements OutStandardService {
    @Resource
    private OutStandardDao outStandardDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OutStandard queryById(Integer id) {
        return this.outStandardDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<OutStandard> queryAllByLimit(int offset, int limit) {
        return this.outStandardDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param outStandard 实例对象
     * @return 实例对象
     */
    @Override
    public OutStandard insert(OutStandard outStandard) {
        this.outStandardDao.insert(outStandard);
        return outStandard;
    }

    /**
     * 修改数据
     *
     * @param outStandard 实例对象
     * @return 实例对象
     */
    @Override
    public OutStandard update(OutStandard outStandard) {
        this.outStandardDao.update(outStandard);
        return this.queryById(outStandard.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.outStandardDao.deleteById(id) > 0;
    }

    @Override
    public OutStandard queryLast() {
        return this.outStandardDao.queryLast();
    }
}
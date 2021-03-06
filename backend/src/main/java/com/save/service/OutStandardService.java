package com.save.service;

import com.save.entity.OutStandard;

import java.util.List;

/**
 * (OutStandard)表服务接口
 *
 * @author makejava
 * @since 2021-01-05 16:21:15
 */
public interface OutStandardService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OutStandard queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OutStandard> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param outStandard 实例对象
     * @return 实例对象
     */
    OutStandard insert(OutStandard outStandard);

    /**
     * 修改数据
     *
     * @param outStandard 实例对象
     * @return 实例对象
     */
    OutStandard update(OutStandard outStandard);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过主键删除数据
     *
     * @return 最新的一条信息（由于id自增，所以只要找id最大的就可以了）
     */
    OutStandard queryLast();

}
package com.save.service;

import com.save.entity.FamClue;

import java.util.List;

/**
 * (FamClue)表服务接口
 *
 * @author makejava
 * @since 2021-01-06 08:42:33
 */
public interface FamClueService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FamClue queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<FamClue> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param famClue 实例对象
     * @return 实例对象
     */
    FamClue insert(FamClue famClue);

    /**
     * 修改数据
     *
     * @param famClue 实例对象
     * @return 实例对象
     */
    FamClue update(FamClue famClue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<FamClue> queryByTaskId(int TaskId);
}
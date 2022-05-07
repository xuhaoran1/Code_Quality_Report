package com.save.service;

import com.save.entity.Clue;

import java.util.List;

/**
 * (Clue)表服务接口
 *
 * @author makejava
 * @since 2021-01-05 10:58:12
 */
public interface ClueService {

    /**
     *查询所有
     */
    List<Clue> queryAll();

    /**
     * 新增数据
     *
     * @param clue 实例对象
     * @return 实例对象
     */
    Clue insert(Clue clue);

    /**
     * 修改数据
     *
     * @param clue 实例对象
     * @return 实例对象
     */
    Clue update(Clue clue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /*
    通过组id查询
     */
    List<Clue> queryByGroupId(Integer GroupId);

}
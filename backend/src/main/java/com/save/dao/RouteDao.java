package com.save.dao;

import com.save.entity.Route;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Route)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-11 14:54:28
 */
@Mapper
public interface RouteDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Route queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Route> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param route 实例对象
     * @return 对象列表
     */
    List<Route> queryAll(Route route);

    /**
     * 新增数据
     *
     * @param route 实例对象
     * @return 影响行数
     */
    int insert(Route route);

    /**
     * 修改数据
     *
     * @param route 实例对象
     * @return 影响行数
     */
    int update(Route route);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Route> getOtherHisXy(@Param("list") List<Integer> volIdList,@Param("taskId") Integer taskId);
}
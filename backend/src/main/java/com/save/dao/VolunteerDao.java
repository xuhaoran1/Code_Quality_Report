package com.save.dao;

import com.save.entity.Volunteer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Volunteer)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-05 15:56:45
 */
@Mapper
public interface VolunteerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Volunteer queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Volunteer> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param volunteer 实例对象
     * @return 对象列表
     */
    List<Volunteer> queryAll(Volunteer volunteer);

    /**
     * 新增数据
     *
     * @param volunteer 实例对象
     * @return 影响行数
     */
    int insert(Volunteer volunteer);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Volunteer> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Volunteer> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Volunteer> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Volunteer> entities);

    /**
     * 修改数据
     *
     * @param volunteer 实例对象
     * @return 影响行数
     */
    int update(Volunteer volunteer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Volunteer> getOtherXy(@Param("list") List<Integer> volIdList);
}
package com.save.dao;

import com.save.entity.OutStandard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (OutStandard)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-05 16:21:14
 */
@Mapper
public interface OutStandardDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OutStandard queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OutStandard> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param outStandard 实例对象
     * @return 对象列表
     */
    List<OutStandard> queryAll(OutStandard outStandard);

    /**
     * 新增数据
     *
     * @param outStandard 实例对象
     * @return 影响行数
     */
    int insert(OutStandard outStandard);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OutStandard> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OutStandard> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OutStandard> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<OutStandard> entities);

    /**
     * 修改数据
     *
     * @param outStandard 实例对象
     * @return 影响行数
     */
    int update(OutStandard outStandard);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过主键删除数据
     *
     * @return 最新的一条信息（由于id自增，所以只要找id最大的就可以了）
     */
    OutStandard queryLast();

}
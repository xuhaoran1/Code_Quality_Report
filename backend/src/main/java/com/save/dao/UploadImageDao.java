package com.save.dao;

import com.save.entity.UploadImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UploadImage)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-06 15:03:07
 */
@Mapper
public interface UploadImageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UploadImage queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UploadImage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param uploadImage 实例对象
     * @return 对象列表
     */
    List<UploadImage> queryAll(UploadImage uploadImage);

    /**
     * 新增数据
     *
     * @param uploadImage 实例对象
     * @return 影响行数
     */
    int insert(UploadImage uploadImage);

    /**
     * 修改数据
     *
     * @param uploadImage 实例对象
     * @return 影响行数
     */
    int update(UploadImage uploadImage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
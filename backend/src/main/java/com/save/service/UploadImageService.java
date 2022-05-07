package com.save.service;

import com.save.entity.UploadImage;
import java.util.List;

/**
 * (UploadImage)表服务接口
 *
 * @author makejava
 * @since 2021-01-06 15:03:07
 */
public interface UploadImageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UploadImage queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UploadImage> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param uploadImage 实例对象
     * @return 实例对象
     */
    UploadImage insert(UploadImage uploadImage);

    /**
     * 修改数据
     *
     * @param uploadImage 实例对象
     * @return 实例对象
     */
    UploadImage update(UploadImage uploadImage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
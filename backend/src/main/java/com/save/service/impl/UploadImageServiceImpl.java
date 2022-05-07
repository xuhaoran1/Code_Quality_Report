package com.save.service.impl;

import com.save.entity.UploadImage;
import com.save.dao.UploadImageDao;
import com.save.service.UploadImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UploadImage)表服务实现类
 *
 * @author makejava
 * @since 2021-01-06 15:03:07
 */
@Service("uploadImageService")
public class UploadImageServiceImpl implements UploadImageService {
    @Resource
    private UploadImageDao uploadImageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UploadImage queryById(Integer id) {
        return this.uploadImageDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UploadImage> queryAllByLimit(int offset, int limit) {
        return this.uploadImageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param uploadImage 实例对象
     * @return 实例对象
     */
    @Override
    public UploadImage insert(UploadImage uploadImage) {
        this.uploadImageDao.insert(uploadImage);
        return uploadImage;
    }

    /**
     * 修改数据
     *
     * @param uploadImage 实例对象
     * @return 实例对象
     */
    @Override
    public UploadImage update(UploadImage uploadImage) {
        this.uploadImageDao.update(uploadImage);
        return this.queryById(uploadImage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.uploadImageDao.deleteById(id) > 0;
    }
}
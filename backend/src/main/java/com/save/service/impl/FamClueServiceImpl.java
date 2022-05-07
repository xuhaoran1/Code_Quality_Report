package com.save.service.impl;

import com.save.dao.FamClueDao;
import com.save.entity.FamClue;
import com.save.service.FamClueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FamClue)表服务实现类
 *
 * @author makejava
 * @since 2021-01-06 08:42:33
 */
@Service("famClueService")
public class FamClueServiceImpl implements FamClueService {
    @Resource
    private FamClueDao famClueDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FamClue queryById(Integer id) {
        return this.famClueDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<FamClue> queryAllByLimit(int offset, int limit) {
        return this.famClueDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param famClue 实例对象
     * @return 实例对象
     */
    @Override
    public FamClue insert(FamClue famClue) {
        this.famClueDao.insert(famClue);
        return famClue;
    }

    /**
     * 修改数据
     *
     * @param famClue 实例对象
     * @return 实例对象
     */
    @Override
    public FamClue update(FamClue famClue) {
        this.famClueDao.update(famClue);
        return this.queryById(famClue.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.famClueDao.deleteById(id) > 0;
    }

    @Override
    public List<FamClue> queryByTaskId(int TaskId) {
        return this.famClueDao.queryByTaskId(TaskId);
    }
}
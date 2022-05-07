package com.save.service.impl;

import com.save.entity.Clue;
import com.save.dao.ClueDao;
import com.save.service.ClueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Clue)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 10:58:12
 */
@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Resource
    private ClueDao clueDao;

    /**
     * 查询所有
     * @return 对象列表
     */
    @Override
    public List<Clue> queryAll() {
        return this.clueDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param clue 实例对象
     * @return 实例对象
     */
    @Override
    public Clue insert(Clue clue) {
        this.clueDao.insert(clue);
        return clue;
    }

    @Override
    public Clue update(Clue clue) {
        return null;
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.clueDao.deleteById(id) > 0;
    }

    @Override
    public List<Clue> queryByGroupId(Integer GroupId) {
        return this.clueDao.queryByGroupId(GroupId);
    }
}
package com.save.service.impl;

import com.save.entity.Route;
import com.save.dao.RouteDao;
import com.save.service.RouteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Route)表服务实现类
 *
 * @author makejava
 * @since 2021-01-11 14:54:28
 */
@Service("routeService")
public class RouteServiceImpl implements RouteService {
    @Resource
    private RouteDao routeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Route queryById(Integer id) {
        return this.routeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Route> queryAllByLimit(int offset, int limit) {
        return this.routeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param route 实例对象
     * @return 实例对象
     */
    @Override
    public Route insert(Route route) {
        this.routeDao.insert(route);
        return route;
    }

    /**
     * 修改数据
     *
     * @param route 实例对象
     * @return 实例对象
     */
    @Override
    public Route update(Route route) {
        this.routeDao.update(route);
        return this.queryById(route.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.routeDao.deleteById(id) > 0;
    }

    @Override
    public List<Route> getOtherHisXy(List<Integer> volIdList,Integer taskId) {
        return routeDao.getOtherHisXy(volIdList,taskId);
    }
}
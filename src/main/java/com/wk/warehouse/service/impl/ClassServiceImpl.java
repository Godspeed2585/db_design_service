package com.wk.warehouse.service.impl;

import com.wk.warehouse.entity.Class;
import com.wk.warehouse.mapper.ClassMapper;
import com.wk.warehouse.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * service实现类
 */
@Service
public class ClassServiceImpl implements ClassService{

    @Autowired
    private ClassMapper classMapper;

    @Override
    public Class getClassByWagenId(String wagonId) {
        return classMapper.findByWagonId(wagonId);
    }

    @Override
    public int deleteClass(String wagonId) {
        return classMapper.delete(wagonId);
    }

    @Override
    public int updateClass(Class classObj) {
        return classMapper.update(classObj);
    }

    @Override
    public boolean isExist(String wagonId) {
        Class a=classMapper.findByWagonId(wagonId);
        if(null == a)return false;
        else return true;
    }

    @Override
    public int addClass(Class classObj) {
        return classMapper.insert(classObj);
    }
}
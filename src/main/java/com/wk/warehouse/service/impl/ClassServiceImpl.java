package com.wk.warehouse.service.impl;

import com.wk.warehouse.entity.Class;
import com.wk.warehouse.mapper.ClassMapper;
import com.wk.warehouse.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service实现类
 */
@Service
public class ClassServiceImpl implements ClassService{

    @Autowired
    private ClassMapper classMapper;

    @Override
    public Class findByWagenId(String wagonld){
        return classMapper.findByWagonId(wagonld);
    }
    @Override
    public int addClass(Class class1){
        return classMapper.addClass()
    }
    @Override
    public int deleteClass(int routeId){

    }
}
package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.Class;

public interface ClassMapper {
    // 根据jobId查询信息
    Class findByWagonId(String wagonId);

    // 新增信息
    void insert(Class classObj);

    // 更新信息
    void update(Class classObj);

    // 删除信息
    void delete(int jobId);
}
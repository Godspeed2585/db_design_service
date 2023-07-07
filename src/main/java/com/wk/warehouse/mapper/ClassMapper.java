package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.Class;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface ClassMapper {
    // 根据jobId查询信息
    Class findByWagonId(String wagonId);

    // 新增信息
    int insert(Class classObj);

    // 更新信息
    int update(Class classObj);

    // 删除信息
    int delete(String wagonId);

    List<Class> getClassByRouteIdDate(@Param("routeId") String routeId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
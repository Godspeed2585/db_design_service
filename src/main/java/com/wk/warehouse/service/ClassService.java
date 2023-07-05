package com.wk.warehouse.service;

import com.wk.warehouse.entity.Class;

public interface ClassService {

    /** 根据班次号查询班次*/
    public Class getClassByWagenId(String wagonld);
    /*
     * 删除班次
     */
    public int deleteClass(String wagonId);

    /**更新班次 */
    int updateClass(Class classobj);

    /** 判断班次是否存在*/
    public boolean isExist(String wagonName);

    /** 添加班次*/
    public int addClass(Class classobj);
}

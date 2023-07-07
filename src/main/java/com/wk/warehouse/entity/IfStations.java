package com.wk.warehouse.entity;

public class IfStations {
    private String initialStation;
    private String finalStation;
    private float totalLength;
    private String routeId;

    // get方法获取initialStation属性的值
    public String getInitialStation() {
        return initialStation;
    }

    // set方法设置initialStation属性的值
    public void setInitialStation(String initialStation) {
        this.initialStation = initialStation;
    }

    // get方法获取finalStation属性的值
    public String getFinalStation() {
        return finalStation;
    }

    // set方法设置finalStation属性的值
    public void setFinalStation(String finalStation) {
        this.finalStation = finalStation;
    }

    // get方法获取totalLength属性的值
    public float getTotalLength() {
        return totalLength;
    }

    // set方法设置totalLength属性的值
    public void setTotalLength(float totalLength) {
        this.totalLength = totalLength;
    }

    // get方法获取routeId属性的值
    public String getRouteId() {
        return routeId;
    }

    // set方法设置routeId属性的值
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
}

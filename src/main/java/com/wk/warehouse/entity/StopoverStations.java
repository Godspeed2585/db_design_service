package com.wk.warehouse.entity;

import java.time.LocalTime;

public class StopoverStations {
    private String stationName;
    private LocalTime arriveTime;
    private LocalTime departureTime;
    private float length;
    private String routeId;
    private int number;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public LocalTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
    public int getnumber() {
        return number;
    }

    public void setnumber(int number) {
        this.number = number;
    }
}

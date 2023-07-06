package com.wk.warehouse.entity;

import java.time.LocalDate;

public class StopoverStations {
    private String stationName;
    private LocalDate arriveTime;
    private LocalDate departureTime;
    private float length;
    private int routeId;
    private int number;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public LocalDate getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalDate arriveTime) {
        this.arriveTime = arriveTime;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }
    public int getnumber() {
        return number;
    }

    public void setnumber(int number) {
        this.number = number;
    }
}

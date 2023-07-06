package com.wk.warehouse.entity;

public class Bus {
    private String license;
    private int totalFirst;
    private int totalSecond;

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getTotalFirst() {
        return totalFirst;
    }

    public void setTotalFirst(int totalFirst) {
        this.totalFirst = totalFirst;
    }

    public int getTotalSecond() {
        return totalSecond;
    }

    public void setTotalSecond(int totalSecond) {
        this.totalSecond = totalSecond;
    }
}

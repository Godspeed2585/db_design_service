package com.example.db_design_service.bean;
import java.util.List;
public class GetAllPassengerReturnData {
    private int status;
    private List<Passenger> passengerList;

    public GetAllPassengerReturnData(int status, List<Passenger> passengerList){
        this.status = status;
        this.passengerList = passengerList;
    }

    public int getStatus() {return status;}
    public void setStatus(int status) {this.status = status;}

    public List<Passenger> getPassengerList(){return passengerList;}
    public void setPassengerList(List<Passenger> passengerList){this.passengerList = passengerList;}
}

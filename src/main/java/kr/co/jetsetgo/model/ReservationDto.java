package kr.co.jetsetgo.model;

import java.sql.Timestamp;

public class ReservationDto {

    private long member_Id;
    private long reservation_Id;
    private java.sql.Timestamp reservation_Date;
    private long reservation_Flights_Id;
    private String status;
    private long id;
    private String originlocationcode;
    private String destinationlocationcode;
    private String departure_City;
    private String arrival_City;
    private java.sql.Timestamp departure_Time;
    private java.sql.Timestamp arrival_Time;

    public long getMember_Id() {
        return member_Id;
    }

    public void setMember_Id(long member_Id) {
        this.member_Id = member_Id;
    }

    public long getReservation_Id() {
        return reservation_Id;
    }

    public void setReservation_Id(long reservation_Id) {
        this.reservation_Id = reservation_Id;
    }

    public Timestamp getReservation_Date() {
        return reservation_Date;
    }

    public void setReservation_Date(Timestamp reservation_Date) {
        this.reservation_Date = reservation_Date;
    }

    public long getReservation_Flights_Id() {
        return reservation_Flights_Id;
    }

    public void setReservation_Flights_Id(long reservation_Flights_Id) {
        this.reservation_Flights_Id = reservation_Flights_Id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginlocationcode() {
        return originlocationcode;
    }

    public void setOriginlocationcode(String originlocationcode) {
        this.originlocationcode = originlocationcode;
    }

    public String getDestinationlocationcode() {
        return destinationlocationcode;
    }

    public void setDestinationlocationcode(String destinationlocationcode) {
        this.destinationlocationcode = destinationlocationcode;
    }

    public String getDeparture_City() {
        return departure_City;
    }

    public void setDeparture_City(String departure_City) {
        this.departure_City = departure_City;
    }

    public String getArrival_City() {
        return arrival_City;
    }

    public void setArrival_City(String arrival_City) {
        this.arrival_City = arrival_City;
    }

    public Timestamp getDeparture_Time() {
        return departure_Time;
    }

    public void setDeparture_Time(Timestamp departure_Time) {
        this.departure_Time = departure_Time;
    }

    public Timestamp getArrival_Time() {
        return arrival_Time;
    }

    public void setArrival_Time(Timestamp arrival_Time) {
        this.arrival_Time = arrival_Time;
    }
}

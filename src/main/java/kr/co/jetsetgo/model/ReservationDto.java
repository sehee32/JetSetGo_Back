package kr.co.jetsetgo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationDto {

    private long member_Id;
    private long reservation_Id;
    private java.time.LocalDateTime reservation_Date;
    private long num;
    private String status;
    private long id;
    private String originlocationcode;
    private String destinationlocationcode;
    private String departure_City;
    private String arrival_City;
    private java.time.LocalDateTime departure_Time;
    private java.time.LocalDateTime arrival_Time;

    private java.time.LocalDate departureDate;
    private java.time.LocalDate arrivalDate;
    private java.time.LocalTime departureTime;
    private java.time.LocalTime arrivalTime;

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

    public LocalDateTime getReservation_Date() {
        return reservation_Date;
    }

    public void setReservation_Date(LocalDateTime reservation_Date) {
        this.reservation_Date = reservation_Date;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
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

    public LocalDateTime getDeparture_Time() {
        return departure_Time;
    }

    public void setDeparture_Time(LocalDateTime departure_Time) {
        this.departure_Time = departure_Time;
    }

    public LocalDateTime getArrival_Time() {
        return arrival_Time;
    }

    public void setArrival_Time(LocalDateTime arrival_Time) {
        this.arrival_Time = arrival_Time;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}

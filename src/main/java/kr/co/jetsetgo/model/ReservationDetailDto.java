package kr.co.jetsetgo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationDetailDto {

    private long reservation_Id;
    private long member_Id;
    private String name;
    private String phoneNumber;
    private String status;
    private String trip_Type;
    private java.time.LocalDateTime reservation_Date;
    private String flight_Num;
    private java.time.LocalDateTime departure_Time;
    private java.time.LocalDateTime arrival_Time;
    private String originlocationcode;
    private String destinationlocationcode;
    private String departure_Airport;
    private String arrival_Airport;
    private String departure_City;
    private String arrival_City;
    private String iatacode;
    private int durationInMinutes;

    private java.time.LocalDate departureDate;
    private java.time.LocalDate arrivalDate;
    private java.time.LocalTime departureTime;
    private java.time.LocalTime arrivalTime;
    private String duration;

    public long getReservation_Id() {
        return reservation_Id;
    }

    public void setReservation_Id(long reservation_Id) {
        this.reservation_Id = reservation_Id;
    }

    public long getMember_Id() {
        return member_Id;
    }

    public void setMember_Id(long member_Id) {
        this.member_Id = member_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrip_Type() {
        return trip_Type;
    }

    public void setTrip_Type(String trip_Type) {
        this.trip_Type = trip_Type;
    }

    public LocalDateTime getReservation_Date() {
        return reservation_Date;
    }

    public void setReservation_Date(LocalDateTime reservation_Date) {
        this.reservation_Date = reservation_Date;
    }

    public String getFlight_Num() {
        return flight_Num;
    }

    public void setFlight_Num(String flight_Num) {
        this.flight_Num = flight_Num;
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

    public String getDeparture_Airport() {
        return departure_Airport;
    }

    public void setDeparture_Airport(String departure_Airport) {
        this.departure_Airport = departure_Airport;
    }

    public String getArrival_Airport() {
        return arrival_Airport;
    }

    public void setArrival_Airport(String arrival_Airport) {
        this.arrival_Airport = arrival_Airport;
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

    public String getIatacode() {
        return iatacode;
    }

    public void setIatacode(String iatacode) {
        this.iatacode = iatacode;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

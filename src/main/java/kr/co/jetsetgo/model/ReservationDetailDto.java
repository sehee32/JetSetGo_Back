package kr.co.jetsetgo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationDetailDto {

    private long reservation_Id;
    private long member_Id;
    private long flight_Id;
    private String name;
    private String phoneNumber;
    private String passenger_Name;
    private String phone_Number;
    private String status;
    private String trip_Type;
    private java.time.LocalDateTime reservation_Date;
    private String passport_Number;
    private String passport_ExpiryDate;
    private String passport_IssuingCountry;
    private double payment_Amount; // 결제 금액
    private String payment_Method; // 결제 수단
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

    public long getFlight_Id() {
        return flight_Id;
    }

    public void setFlight_Id(long flight_Id) {
        this.flight_Id = flight_Id;
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

    public String getPassenger_Name() {
        return passenger_Name;
    }

    public void setPassenger_Name(String passenger_Name) {
        this.passenger_Name = passenger_Name;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
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

    public String getPassport_Number() {
        return passport_Number;
    }

    public void setPassport_Number(String passport_Number) {
        this.passport_Number = passport_Number;
    }

    public String getPassport_ExpiryDate() {
        return passport_ExpiryDate;
    }

    public void setPassport_ExpiryDate(String passport_ExpiryDate) {
        this.passport_ExpiryDate = passport_ExpiryDate;
    }

    public String getPassport_IssuingCountry() {
        return passport_IssuingCountry;
    }

    public void setPassport_IssuingCountry(String passport_IssuingCountry) {
        this.passport_IssuingCountry = passport_IssuingCountry;
    }

    public double getPayment_Amount() {
        return payment_Amount;
    }

    public void setPayment_Amount(double paymentAmount) {
        this.payment_Amount = paymentAmount;
    }

    public String getPayment_Method() {
        return payment_Method;
    }

    public void setPayment_Method(String paymentMethod) {
        this.payment_Method = paymentMethod;
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

package kr.co.jetsetgo.model;


import java.sql.Timestamp;

public class TbReservation {

  private long num;
  private long member_Id;
  private String reservation_Id;
  private long flight_Id;
  private String status;
  private String trip_Type;
  private java.sql.Timestamp reservation_Date;
  private String passenger_Name;
  private String phone_Number;
  private String passport_Number;
  private String passport_Expirydate;
  private String passport_Issuingcountry;
  private double payment_Amount;
  private String payment_Method;
  private long nonstop;
  private String travelclass;
  private long adults;
  private long children;


  public long getNum() {
    return num;
  }

  public void setNum(long num) {
    this.num = num;
  }

  public long getMember_Id() {
    return member_Id;
  }

  public void setMember_Id(long member_Id) {
    this.member_Id = member_Id;
  }

  public String getReservation_Id() {
    return reservation_Id;
  }

  public void setReservation_Id(String reservation_Id) {
    this.reservation_Id = reservation_Id;
  }

  public long getFlight_Id() {
    return flight_Id;
  }

  public void setFlight_Id(Long flight_Id) {
    this.flight_Id = flight_Id;
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

  public Timestamp getReservation_Date() {
    return reservation_Date;
  }

  public void setReservation_Date(Timestamp reservation_Date) {
    this.reservation_Date = reservation_Date;
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

  public String getPassport_Number() {
    return passport_Number;
  }

  public void setPassport_Number(String passport_Number) {
    this.passport_Number = passport_Number;
  }

  public String getPassport_Expirydate() {
    return passport_Expirydate;
  }

  public void setPassport_Expirydate(String passport_Expirydate) {
    this.passport_Expirydate = passport_Expirydate;
  }

  public String getPassport_Issuingcountry() {
    return passport_Issuingcountry;
  }

  public void setPassport_Issuingcountry(String passport_Issuingcountry) {
    this.passport_Issuingcountry = passport_Issuingcountry;
  }

  public double getPayment_Amount() {
    return payment_Amount;
  }

  public void setPayment_Amount(double payment_Amount) {
    this.payment_Amount = payment_Amount;
  }

  public String getPayment_Method() {
    return payment_Method;
  }

  public void setPayment_Method(String payment_Method) {
    this.payment_Method = payment_Method;
  }

  public long getNonstop() {
    return nonstop;
  }

  public void setNonstop(long nonstop) {
    this.nonstop = nonstop;
  }

  public String getTravelclass() {
    return travelclass;
  }

  public void setTravelclass(String travelclass) {
    this.travelclass = travelclass;
  }

  public long getAdults() {
    return adults;
  }

  public void setAdults(long adults) {
    this.adults = adults;
  }

  public long getChildren() {
    return children;
  }

  public void setChildren(long children) {
    this.children = children;
  }

}

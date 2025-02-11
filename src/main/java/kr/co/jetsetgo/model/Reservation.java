package kr.co.jetsetgo.model;


public class Reservation {

  private long num;
  private long memberId;
  private long reservationId;
  private long flightId;
  private String status;
  private String tripType;
  private java.sql.Timestamp reservationDate;
  private String passengerName;
  private String phoneNumber;
  private String passportNumber;
  private String passportExpirydate;
  private String passportIssuingcountry;
  private double paymentAmount;
  private String paymentMethod;
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


  public long getMemberId() {
    return memberId;
  }

  public void setMemberId(long memberId) {
    this.memberId = memberId;
  }


  public long getReservationId() {
    return reservationId;
  }

  public void setReservationId(long reservationId) {
    this.reservationId = reservationId;
  }


  public long getFlightId() {
    return flightId;
  }

  public void setFlightId(long flightId) {
    this.flightId = flightId;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getTripType() {
    return tripType;
  }

  public void setTripType(String tripType) {
    this.tripType = tripType;
  }


  public java.sql.Timestamp getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(java.sql.Timestamp reservationDate) {
    this.reservationDate = reservationDate;
  }


  public String getPassengerName() {
    return passengerName;
  }

  public void setPassengerName(String passengerName) {
    this.passengerName = passengerName;
  }


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  public String getPassportNumber() {
    return passportNumber;
  }

  public void setPassportNumber(String passportNumber) {
    this.passportNumber = passportNumber;
  }


  public String getPassportExpirydate() {
    return passportExpirydate;
  }

  public void setPassportExpirydate(String passportExpirydate) {
    this.passportExpirydate = passportExpirydate;
  }


  public String getPassportIssuingcountry() {
    return passportIssuingcountry;
  }

  public void setPassportIssuingcountry(String passportIssuingcountry) {
    this.passportIssuingcountry = passportIssuingcountry;
  }


  public double getPaymentAmount() {
    return paymentAmount;
  }

  public void setPaymentAmount(double paymentAmount) {
    this.paymentAmount = paymentAmount;
  }


  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
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

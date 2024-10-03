package kr.co.jetsetgo.model;


public class TbReservation {

  private long reservation_Id;
  private long member_Id;
  private String status;
  private String trip_Type;
  private java.sql.Timestamp reservation_Date;


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


  public java.sql.Timestamp getReservation_Date() {
    return reservation_Date;
  }

  public void setReservation_Date(java.sql.Timestamp reservation_Date) {
    this.reservation_Date = reservation_Date;
  }

}

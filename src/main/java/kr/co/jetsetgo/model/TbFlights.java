package kr.co.jetsetgo.model;


public class TbFlights {

  private long id;
  private java.sql.Timestamp departureTime;
  private java.sql.Timestamp arrivalTime;
  private String originlocationcode;
  private String destinationlocationcode;
  private String departureCity;
  private String arrivalCity;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Timestamp getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(java.sql.Timestamp departureTime) {
    this.departureTime = departureTime;
  }


  public java.sql.Timestamp getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(java.sql.Timestamp arrivalTime) {
    this.arrivalTime = arrivalTime;
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


  public String getDepartureCity() {
    return departureCity;
  }

  public void setDepartureCity(String departureCity) {
    this.departureCity = departureCity;
  }


  public String getArrivalCity() {
    return arrivalCity;
  }

  public void setArrivalCity(String arrivalCity) {
    this.arrivalCity = arrivalCity;
  }

}

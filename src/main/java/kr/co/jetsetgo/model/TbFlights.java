package kr.co.jetsetgo.model;


public class TbFlights {

  private long id;
  private String flight_Num;
  private java.sql.Timestamp departure_Time;
  private java.sql.Timestamp arrival_Time;
  private String originlocationcode;
  private String destinationlocationcode;
  private String departure_Airport;
  private String arrival_Airport;
  private String departure_City;
  private String arrival_City;
  private String iatacode;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getFlight_Num() {
    return flight_Num;
  }

  public void setFlight_Num(String flight_Num) {
    this.flight_Num = flight_Num;
  }


  public java.sql.Timestamp getDeparture_Time() {
    return departure_Time;
  }

  public void setDeparture_Time(java.sql.Timestamp departure_Time) {
    this.departure_Time = departure_Time;
  }


  public java.sql.Timestamp getArrival_Time() {
    return arrival_Time;
  }

  public void setArrival_Time(java.sql.Timestamp arrival_Time) {
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

}

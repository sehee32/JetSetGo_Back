package kr.co.jetsetgo.model;


public class TbReservationFlights {

  private long reservation_Flights_Id;
  private long reservation_Id;
  private long flight_Id;


  public long getReservation_Flights_Id() {
    return reservation_Flights_Id;
  }

  public void setReservation_Flights_Id(long reservation_Flights_Id) {
    this.reservation_Flights_Id = reservation_Flights_Id;
  }


  public long getReservation_Id() {
    return reservation_Id;
  }

  public void setReservation_Id(long reservation_Id) {
    this.reservation_Id = reservation_Id;
  }


  public long getFlight_Id() {
    return flight_Id;
  }

  public void setFlight_Id(long flight_Id) {
    this.flight_Id = flight_Id;
  }

}

package com.company.appointmentBase;

import com.company.clientBase.Client;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The appointment class, making an appointment.
 */
public class Appointment {

  private Date booking;
  private final Date bookingEnd;
  private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
  private Client client;
  private int duration;

  /**
   * Make an appointment.
   *
   * @param date The date for appointment.
   * @param duration The duration of the appointment.
   * @param client The client that have the appointment.
   */
  public Appointment (String date, int duration, Client client) {

    this.client = client;

    this.booking = new Date();
    this.bookingEnd = new Date();
    this.duration = duration;

    try {
      this.booking = this.format.parse(date);
    } catch (Exception e) {
      System.out.println("ERROR - Date incorrect");
      //e.printStackTrace();
    }

    bookingEnd(this.booking);
  }

  /**
   * @return Clients booking date.
   */
  // Getter
  public Date getBooking() {
    return booking;
  }

  /**
   * @return Clients booking date end.
   */
  public Date getBookingEnd() {
    return bookingEnd;
  }

  /**
   * @return Client whom have the appointment.
   */
  public Client getClient() {
    return client;
  }

  /**
   * @return The duration of the appointment.
   */
  public int getDuration() {
    return duration;
  }

  /**
   * @param client whom make the appointment.
   */
  // Setter
  public void setClient(Client client) {
    this.client = client;
  }

  /**
   * @param duration of the appointment
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Calculate the end time.
   *
   * @param date Appointment start time.
   * Duration calculate the end time.
   */
  // Methods
  private void bookingEnd(Date date) {
    // (getTime) Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
    long min = date.getTime();
    this.bookingEnd.setTime(min + ((long) this.duration * 60 * 1000));

  }

  /**
   * @return toString for saving the appointment.
   */
  @Override
  public String toString() {
    return this.format.format(this.booking) + ';'
        + this.duration + ';'
        + this.client.getEmail();
  }
}

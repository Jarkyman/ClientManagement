package com.company.menu;

import com.company.appointmentBase.Appointment;
import com.company.appointmentBase.AppointmentCalender;
import com.company.clientBase.Client;
import com.company.clientBase.ClientsList;
import com.company.validations.IntegerValidations;
import com.company.validations.StringValidations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * The menu with options for appointments.
 */
public class AppointmentMenu {

  private final Scanner scanner = new Scanner(System.in);
  private final AppointmentCalender appCalender = new AppointmentCalender();
  private ClientsList clientsList = new ClientsList();

  public AppointmentMenu() {
  }

  // Getter
  /**
   * @return appointments
   */
  public AppointmentCalender getAppCalender() {
    return appCalender;
  }


  // Setter
  private void setClientsList(ClientsList clientsList) {
    this.clientsList = clientsList;
  }

  // Methods
  /**
   * Print the menu with options.
   *
   * @param clientsList list of all clients.
   */
  public void appointmentMenu(ClientsList clientsList) {

    setClientsList(clientsList);

    boolean back = false;
    int option;

    while (!back) {

      printClientMenu();
      option = new IntegerValidations().inputOption();

      switch (option) {

        case 1:
          if (this.appCalender.getAppointments().isEmpty()) {
            System.out.println("No appointments");
          } else {
            this.appCalender.printAppointments(this.appCalender.getAppointments());
            System.out.println("Press enter to continue");
            this.scanner.nextLine();
          }
          break;
        case 2:
          createAppointment();
          getAppCalender().saveAppointmentsToFile();
          break;
        case 3:
          removeAppointment();
          getAppCalender().saveAppointmentsToFile();
          break;
        case 4:
          removeOldAppointments();
          getAppCalender().saveAppointmentsToFile();
          break;
        case 9:
          back = true;
          break;
        default:
          System.out.println("Not a valid option");
          break;
      }

    }

  }

  private void printClientMenu() {

    System.out.println("\n" +
        "\t1. List of appointments\n" +
        "\t2. Add appointment\n" +
        "\t3. Remove appointment\n" +
        "\t4. Remove all old appointments\n" +
        "\t9. Back\n"
    );
  }

  private void createAppointment() {

    if (clientsList.getClientList().isEmpty()) {
      System.out.println("No clients");
    } else {
      Client client = chooseClient();
      String ddMmYy = new StringValidations().makeDateOnly();
      String hhMm = new StringValidations().makeTime();
      String date = ddMmYy + " " + hhMm;
      int duration = new IntegerValidations().makeDuration();

      Appointment appoint = new Appointment(date, duration, client);

      this.appCalender.createAppointment(appoint);

      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
      String appDate = format.format(appoint.getBooking());
      this.appCalender.bookAppointment(appDate, appoint.getDuration(), client);
    }
  }

  private Client chooseClient() {

    String print = "Select client for appointment";
    return this.clientsList.getClientList().get(new IntegerValidations().inputClientNumber(this.clientsList, print));
  }

  private void removeAppointment() {

    if (appCalender.getAppointments().isEmpty()) {
      System.out.println("No appointments");
    } else {
      this.appCalender.printAppointments(this.appCalender.getAppointments());
      int input = new IntegerValidations().inputAppointmentNumber(this.appCalender);

      if (input == -1) {
        System.out.println("Back");
      } else {
        Appointment appointment = this.appCalender.getAppointments().get(input);
        this.appCalender.removeAppointment(appointment);
      }
    }
  }

  private void removeOldAppointments() {
    if (appCalender.getAppointments().isEmpty()) {
      System.out.println("No appointments");
    } else {
      Date today = new Date();

      for (int i = appCalender.getAppointments().size() - 1; i > -1; i--) {
        if (appCalender.getAppointments().get(i).getBookingEnd().before(today)) {
          this.appCalender.removeAppointment(appCalender.getAppointments().get(i));
        }
      }
      System.out.println("Removed all old appointments");
    }
  }

}

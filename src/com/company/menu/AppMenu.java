package com.company.menu;

import com.company.validations.IntegerValidations;

import java.io.IOException;

/**
 * The startup menu of the application.
 */
public class AppMenu {

  private final ClientMenu clientMenu = new ClientMenu();
  private final AppointmentMenu appointmentMenu = new AppointmentMenu();

  public void menu() throws IOException {

    boolean quit = false;
    int option;

    while (!quit) {

      printMenu();
      option = new IntegerValidations().inputOption();

      switch (option) {

        case 1:
          this.clientMenu.clientMenu();
          break;
        case 2:
          this.appointmentMenu.appointmentMenu(this.clientMenu.getClientsList());
          break;
        case 9:
          quit = true;
          this.clientMenu.getClientsList().saveClientsToFile();
          this.appointmentMenu.getAppCalender().saveAppointmentsToFile();
          break;
        default:
          System.out.println("Not a valid option");
          break;
      }


    }

  }

  private void printMenu() {

    System.out.println("\n" +
        "\t1. Clients menu\n" +
        "\t2. Appointment menu\n" +
        "\t9. Quit\n"
    );
  }

  /**
   * Call to load files and run the application.
   *
   * @throws IOException loading files.
   */
  public AppMenu() throws IOException {
    this.clientMenu.getClientsList().loadClientsFromFile();
    this.appointmentMenu.getAppCalender().loadAppointmentsFromFile();
    menu();
  }
}

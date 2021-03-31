package com.company.appointmentBase;

import com.company.clientBase.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Make an appointment calender, to keep track of appointments.
 */
public class AppointmentCalender {

  private final ArrayList<Appointment> appointments = new ArrayList<>();
  private int firstAppointment;
  private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
  private final File fileName = new File("Appointments.txt");
  private final ClientsList clientList = new ClientsList();

  /**
   * Default constructor, make the fully calender.
   */
  public AppointmentCalender() {
    this.clientList.loadClientsFromFile();
    this.firstAppointment = 0;
  }

  /**
   * @return Arraylist of appointments.
   */
  public ArrayList<Appointment> getAppointments() {
    return appointments;
  }

  /**
   * Creating the appointment.
   * Checking for available appointments.
   *
   * @param appointment input appointment you want to create.
   */
  public void createAppointment(Appointment appointment) {
    int temp = 0;

    if (this.firstAppointment == 0) {
      this.appointments.add(appointment);
      System.out.println("Appointment successfully created");
      this.firstAppointment++;
    } else { // check if appointment overlap or are made
      for (Appointment value : this.appointments) {
        if ((value.getBookingEnd()).compareTo(appointment.getBooking()) < 0) {
          temp = 1;
          break;
        }
      }
      if (temp == 1) {
        this.appointments.add(appointment);
        System.out.println("Appointment successfully created");
      } else {
        System.out.println("Appointment not created");
      }
    }
  }

  /**
   * Book the appointment.
   * Sets the parameters of client, date and duration.
   *
   * @param date Date for appointment.
   * @param duration duration of the appointment in minutes.
   * @param client The client whom book the appointment.
   */
  public void bookAppointment(String date, int duration, Client client) {
    int temp = 0;
    int flag = 0;
    Date booking = new Date();


    try {
      booking = this.format.parse(date);
    } catch (Exception e) {
      System.out.println("ERROR - Date incorrect");
      //e.printStackTrace();
    }

    for (int i = 0; i < this.appointments.size(); i++) {
      if (booking.equals(this.appointments.get(i).getBooking())) {
        temp = i;
        flag = 1;
      }
    }

    if (flag == 1) {
      this.appointments.get(temp).setDuration(duration);
      this.appointments.get(temp).setClient(client);

    }

    sortAppointments();

  }

  /**
   * Remove an appointment.
   *
   * @param appointment The appointment to remove.
   */
  public void removeAppointment(Appointment appointment) {
    if (this.appointments.isEmpty()) {
      System.out.println("No appointments to remove");
    } else {
      this.appointments.remove(appointment);
      //saveAppointmentsToFile();
    }
  }

  /**
   * Print the appointments.
   *
   * @param appointments The list of appointment to print.
   */
  public void printAppointments(ArrayList<Appointment> appointments) {

    int count = 1;
    System.out.println("------------------------------------------------------------------------------------------------------");
    System.out.printf("%3s %15s %30s %25s %25s", "nr.", "Name", "Lastname", "Date", "End");
    System.out.println();
    System.out.println("------------------------------------------------------------------------------------------------------");

    for (Appointment a : appointments) {
      Date now = new Date();

      String name = a.getClient().getName();
      String lastName = a.getClient().getLastName();
      String date = this.format.format(a.getBooking());
      String end = this.format.format(a.getBookingEnd());

      if (a.getBookingEnd().before(now) || !emailMatch(a.getClient().getEmail())) {
        System.out.print("\033[9m");
        System.out.format("%3s %15s %30s %25s %25s", count++, name, lastName, date, end);
        System.out.println("\033[0m");
      } else {
        System.out.format("%3s %15s %30s %25s %25s", count++, name, lastName, date, end);
        System.out.println();
      }

    }
    System.out.println("------------------------------------------------------------------------------------------------------\n");

  }

  /**
   * Sorts the appointments by date.
   */
  public void sortAppointments() {
    this.appointments.sort(Comparator.comparingLong(a -> a.getBooking().getTime()));

    saveAppointmentsToFile();
  }


  // File

  /**
   * Saves all appointments to a file.
   */
  public void saveAppointmentsToFile() {

    try {
      FileWriter fileWriter = new FileWriter(this.fileName);
      Writer output = new BufferedWriter(fileWriter);

      for (Appointment appointment : this.appointments) {
        output.write(appointment.toString() + "\n");
      }

      output.close();
      //System.out.println("Successful saved file");
    } catch (Exception e) {
      System.out.println("ERROR - while saving file");
      //e.printStackTrace();
    }
  }

  /**
   * Load the appointments on file.
   * If file do not exist, the file will be created.
   */
  public void loadAppointmentsFromFile() {

    try {
      boolean newFileCreate = this.fileName.createNewFile(); // Create file if file not exist

      if (newFileCreate) {
        System.out.println("couldn't find file, file: " + fileName.toString() + " is created");
      }

      Scanner fileScanner = new Scanner(this.fileName);
      String clientInfo;

      if (fileScanner.hasNextLine()) {
        BufferedReader input = new BufferedReader(new FileReader(this.fileName));
        if (!input.ready()) {
          throw new IOException();
        }

        while (fileScanner.hasNextLine()) {
          clientInfo = fileScanner.nextLine();
          String[] appoint = clientInfo.split(";");

          if (emailMatch(appoint[2])) {
            Client client = loadClient(appoint[2]);
            Appointment appointment = new Appointment(appoint[0], Integer.parseInt(appoint[1]), client);
            this.appointments.add(appointment);
          } else {
            System.out.println("Wrong email in the list");
          }

        }
        input.close();
        //System.out.println("Loaded Appointments successfully");

      }

    } catch (IOException e) {
      System.out.println("ERROR - while loading file");
      //e.printStackTrace();
    }

  }

  /**
   * Find the client by email.
   *
   * @param email The email used to find client.
   * @return The client whom match the email.
   */
  private Client loadClient(String email) {

    for (Client client : this.clientList.getClientList()) {
      if (client.getEmail().equals(email)) {
        return client;
      }
    }
    return null;
  }

  /**
   * Find client whom match the email.
   *
   * @param email The mail to search for.
   * @return If a client got the mail.
   */
  private boolean emailMatch(String email) {
    this.clientList.loadClientsFromFile(); // load file to get the update
    for (Client client : clientList.getClientList()) {
      if (client.getEmail().equals(email)) {
        return true;
      }
    }
    return false;
  }

}

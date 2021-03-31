package com.company.validations;

import com.company.appointmentBase.AppointmentCalender;
import com.company.clientBase.ClientsList;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Validation of scanner input.
 * Only integers input.
 */
public class IntegerValidations {

  private final Scanner scanner = new Scanner(System.in);

  /**
   * Makes a option input.
   *
   * @return option
   */
  public int inputOption() {
    int option;

    do {
      System.out.println("Enter options");
      option = getNumber();
    }while (option < 0);
    this.scanner.nextLine();

    return option;
  }

  /**
   * Make a duration input.
   *
   * @return duration
   */
  public int makeDuration() {

    int duration;

    do {
      System.out.println("Enter duration");
      duration = getNumber();
    }while (duration < 0);
    this.scanner.nextLine();

    return duration;
  }

  private int getNumber() {
    int number;
    System.out.print(">: ");

    while (!this.scanner.hasNextInt()) {
      String input = this.scanner.next();
      System.out.println(input + ", isn't a valid input");
      System.out.println("Enter options");
      System.out.print(">: ");
    }

    number = this.scanner.nextInt();
    if (number < 0) {
      System.out.println("need to be a positive number");
    }
    return number;
  }

  /**
   * @return year.
   */
  public int makeBirthYear() {
    int birthYear;
    int currentYear = Calendar.getInstance().get(Calendar.YEAR) - 3;
    do {
      System.out.println("Enter clients birth year");
      System.out.print(">: ");
      while (!this.scanner.hasNextInt()) {
        String input = this.scanner.next();
        System.out.println(input + ", isn't a valid input");
        System.out.println("Enter clients birth year");
        System.out.print(">: ");
      }
      birthYear = this.scanner.nextInt();

      if (birthYear > currentYear) {
        System.out.println(birthYear + ", isn't valid, you need to be 3 years old");
      }
      if (birthYear < currentYear - 147) {
        System.out.println("Something went wrong, ERROR to old");
      }

    } while (birthYear < currentYear - 147 || birthYear > currentYear);

    this.scanner.nextLine();

    return birthYear;
  }

  /**
   * @return month.
   */
  public int makeBirthMonth() {
    int birthMonth;
    do {
      System.out.println("Enter clients birth month");
      System.out.print(">: ");
      while (!this.scanner.hasNextInt()) {
        String input = this.scanner.next();
        System.out.println(input + ", isn't a valid input");
        System.out.println("Enter clients birth month");
        System.out.print(">: ");
      }
      birthMonth = this.scanner.nextInt();

      if (birthMonth < 0 || birthMonth > 12) {
        System.out.println(birthMonth + ", isn't a valid month");
      }
    } while (birthMonth < 0 || birthMonth > 12);

    this.scanner.nextLine();
    return birthMonth;
  }

  /**
   * @param birthMonth to validate days.
   * @param birthYear to validate leap year.
   * @return day
   */
  public int makeBirthDay(int birthMonth, int birthYear) {
    int birthDay;
    int clientsBirthMonth = months(birthMonth, birthYear);

    do {
      System.out.println("Enter clients birth day");
      System.out.print(">: ");
      while (!this.scanner.hasNextInt()) {
        String input = this.scanner.next();
        System.out.println(input + ", isn't a valid input");
        System.out.println("Enter clients birth date");
        System.out.print(">: ");
      }
      birthDay = this.scanner.nextInt();

      if (birthDay < 0 || birthDay > clientsBirthMonth) {
        System.out.println(birthDay + ", isn't a valid date, need to be between 1 and " + clientsBirthMonth);
      }

    } while (birthDay < 0 || birthDay > clientsBirthMonth);

    this.scanner.nextLine();
    return birthDay;
  }

  private int months(int month, int birthYear) {

    int daysInAMonth = 31;

    switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        daysInAMonth = 31;
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        daysInAMonth = 30;
        break;
      case 2:
        if (isLeapYear(birthYear)) {
          daysInAMonth = 29;
          break;
        } else {
          daysInAMonth = 28;
        }
        break;
      default:
        System.out.println("Invalid month");
        break;
    }

    return daysInAMonth;
  }

  private boolean isLeapYear(int yeah) {
    boolean leapYeah = false;

    if (yeah % 4 == 0 && !(yeah % 100 == 0) || yeah % 400 == 0) {
      leapYeah = true;
    }

    return leapYeah;
  }


  /**
   * @param clientsList to get clients available.
   * @param text to print.
   * @return client index.
   */
  public int inputClientNumber(ClientsList clientsList, String text) {
    clientsList.printClientList(clientsList.getClientList());
    if (text.length() > 0) {
      System.out.println(text);
    }
    return clientNumber(clientsList);
  }

  /**
   * @param clientsList to get clients available.
   * @return client index.
   */
  public int inputClientNumber(ClientsList clientsList) {
    clientsList.printClientList(clientsList.getClientList());
    return clientNumber(clientsList);
  }

  private int clientNumber(ClientsList clientsList) {
    int option;

    do {
      System.out.println("Enter client number");
      System.out.print(">: ");

      while (!this.scanner.hasNextInt()) {
        String input = this.scanner.next();
        System.out.println(input + ", isn't a valid input");
        System.out.println("Enter client number");
        System.out.print(">: ");
      }

      option = this.scanner.nextInt();
      if (option < 1 || option > clientsList.getClientList().size()) {
        System.out.println(option + " isn't a valid client number");
      }

    } while (option < 1 || option > clientsList.getClientList().size());
    this.scanner.nextLine();

    return option - 1;
  }

  /**
   * @param appCalender to get the calender
   * @return appointment index.
   */
  public int inputAppointmentNumber(AppointmentCalender appCalender) {
    int option;

    do {
      System.out.println("Choose appointment (0 for cancel)");
      System.out.print(">: ");

      while (!this.scanner.hasNextInt()) {
        String input = this.scanner.next();
        System.out.println(input + ", isn't a valid input");
        System.out.println("Choose appointment (0 for cancel)");
        System.out.print(">: ");
      }

      option = this.scanner.nextInt();
      if (option < 0 || option > appCalender.getAppointments().size()) {
        System.out.println(option + " isn't a valid appointment number");
      }

    } while (option < 0 || option > appCalender.getAppointments().size());
    this.scanner.nextLine();

    return option - 1;
  }

}

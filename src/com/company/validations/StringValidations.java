package com.company.validations;

import com.company.clientBase.Client;
import com.company.clientBase.ClientsList;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation of scanner input.
 * Only string input.
 */
public class StringValidations {

  private final Scanner scanner = new Scanner(System.in);

  /**
   * @return name.
   */
  public String makeName() {
    String name;
    String matching = "[a-zA-Z æøåÆØÅ]+";
    do {
      System.out.println("Enter clients name");
      System.out.print(">: ");
      name = this.scanner.nextLine();

      if (name.length() < 1) {
        System.out.println("Name is too short");
      } else if (!name.matches(matching)) {
        System.out.println("Use only letters");
      }

    } while (name.length() < 1 || !name.matches(matching));

    name = stringToUpperCase(name);

    return name;

  }

  /**
   * @return lastname.
   */
  public String makeLastName() {
    String lastName;
    String matching = "[a-zA-Z æøåÆØÅ]+";

    do {
      System.out.println("Enter clients lastname");
      System.out.print(">: ");
      lastName = this.scanner.nextLine();

      if (lastName.length() < 1) {
        System.out.println("Name is too short");
      } else if (!lastName.matches(matching)) {
        System.out.println("Use only letters");
      }

    } while (lastName.length() < 1 || !lastName.matches(matching));

    lastName = stringToUpperCase(lastName);

    return lastName;
  }

  private String stringToUpperCase(String name) {
    String[] nameSplitter = name.split(" ");

    for (int i = 0; i < nameSplitter.length; i++) {
      nameSplitter[i] = nameSplitter[i].substring(0,1).toUpperCase() + nameSplitter[i].substring(1).toLowerCase();
    }
    name = String.join(" ", nameSplitter);

    return name;
  }

  /**
   * @return industry.
   */
  public String makeIndustry() {
    String industry;
    System.out.println("Enter clients industry");
    System.out.print(">: ");
    industry = this.scanner.nextLine();
    industry = industry.replace(';', ':');

    return industry;
  }

  /**
   * @return phone number.
   */
  public String makePhoneNumber() {
    String phoneNumber;
    do {
      System.out.println("Enter clients phone number (remember country code)");
      System.out.print(">: ");

      phoneNumber = this.scanner.nextLine();

      if (isValidPhoneNumber(phoneNumber)) {
        System.out.println(phoneNumber + ", isn't a valid phone number");
      }

    } while (isValidPhoneNumber(phoneNumber));

    return phoneNumber;
  }

  private boolean isValidPhoneNumber(String phoneNumber) {
    Pattern pattern = Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$");

    Matcher matcher = pattern.matcher(phoneNumber);
    return (!matcher.find() || !matcher.group().equals(phoneNumber));
  }

  /**
   * Only emails that are not already to a client.
   *
   * @return email.
   */
  public String makeEmail() {
    String email;
    String email_regex = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    do {
      System.out.println("Enter clients email");
      System.out.print(">: ");

      email = this.scanner.nextLine();

      if (email.length() < 1) {
        System.out.println("Mail too short");
      } else if (!email.matches(email_regex)) {
        System.out.println(email + ", isn't a valid email");
      } else if (emailExist(email)) {
        System.out.println("Email already exist");
      }

    } while (email.length() < 1 || !email.matches(email_regex) || emailExist(email));

    return email;
  }

  private boolean emailExist(String email) {
    ClientsList clientList = new ClientsList();
    clientList.loadClientsFromFile();
    for (Client client : clientList.getClientList()) {
      if (client.getEmail().equals(email)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return presenting issue text.
   */
  public String makePresentingIssue() {
    String presentingIssue;
    System.out.println("Enter clients presentingIssue");
    System.out.print(">: ");

    presentingIssue = this.scanner.nextLine();

    presentingIssue = presentingIssue.replace(';', ':');

    return presentingIssue;
  }

  /**
   * @return date (dd/mm/yy hh:mm).
   */
  public String makeDate() {
    String date;
    do {

      System.out.println("Enter date (DD/MM/YY HH:MM)");
      System.out.print(">: ");
      date = this.scanner.nextLine();

    }while (!dateChecker(date));


    return date;
  }

  private boolean dateChecker(String date) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
    try {
      format.parse(date);
      return true;

    } catch (Exception e) {
      System.out.println("Wrong date");
      //e.printStackTrace();
    }
    return false;
  }

  /**
   * @return date (dd/mm/yy).
   */
  public String makeDateOnly() {
    String date;
    do {

      System.out.println("Enter date (DD/MM/YY)");
      System.out.print(">: ");
      date = this.scanner.nextLine();

    }while (!dateOnlyChecker(date));


    return date;
  }

  private boolean dateOnlyChecker(String date) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
    try {
      format.parse(date);
      return true;

    } catch (Exception e) {
      System.out.println("Wrong date");
      //e.printStackTrace();
    }
    return false;
  }

  /**
   * @return time (hh:mm).
   */
  public String makeTime() {
    String time;
    do {

      System.out.println("Enter time (HH:MM)");
      System.out.print(">: ");
      time = this.scanner.nextLine();

    }while (!timeChecker(time));


    return time;
  }

  private boolean timeChecker(String time) {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    try {
      format.parse(time);
      return true;

    } catch (Exception e) {
      System.out.println("Wrong date");
      //e.printStackTrace();
    }

    return false;
  }


  /**
   * @return record text.
   */
  public String makeRecord() {
    String record;
    System.out.println("Enter record");
    System.out.print(">: ");
    record = this.scanner.nextLine();

    record = record.replace(';', ':');

    return record;
  }

}

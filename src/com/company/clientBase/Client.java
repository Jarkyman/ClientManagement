package com.company.clientBase;

import com.company.validations.IntegerValidations;
import com.company.validations.StringValidations;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

/**
 * The client class, to make a client.
 */
public class Client {

  private final Scanner scanner = new Scanner(System.in);

  private String name;
  private String lastName;
  private int age;
  private int birthDay;
  private int birthMonth;
  private int birthYear;
  private String industry;
  private String phoneNumber;
  private String email;
  private String presentingIssue;

  /**
   * Default constructor, makes a client and ask of input to all information.
   *
   * @throws IOException Saving file.
   */
  public Client() throws IOException {
    setName(new StringValidations().makeName());
    setLastName(new StringValidations().makeLastName());
    setBirthYear(new IntegerValidations().makeBirthYear());
    setBirthMonth(new IntegerValidations().makeBirthMonth());
    setBirthDay(new IntegerValidations().makeBirthDay(this.birthMonth, this.birthYear));
    setIndustry(new StringValidations().makeIndustry());
    setPhoneNumber(new StringValidations().makePhoneNumber());
    setEmail(new StringValidations().makeEmail());
    setPresentingIssue(new StringValidations().makePresentingIssue());
  }

  /**
   * Create a client with parameters input.
   *
   * @param name Clients name.
   * @param lastName Clients lastname.
   * @param birthDay Clients birth day.
   * @param birthMonth Clients birth month.
   * @param birthYear Clients birth year.
   * @param industry Clients industry.
   * @param phoneNumber Clients phone number.
   * @param email Clients email.
   * @param presentingIssue Clients presenting issue.
   */
  public Client(String name, String lastName, int birthDay, int birthMonth,  int birthYear, String industry, String phoneNumber, String email, String presentingIssue) {
    setName(name);
    setLastName(lastName);
    setBirthYear(birthYear);
    setBirthMonth(birthMonth);
    setBirthDay(birthDay);
    setIndustry(industry);
    setPhoneNumber(phoneNumber);
    setEmail(email);
    setPresentingIssue(presentingIssue);
  }

  /**
   * @return Clients name.
   */
  // Getter
  public String getName() {
    return name;
  }

  /**
   * @return Clients lastname.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Calculate the age of client by birthday date and today date.
   *
   * @return Clients age.
   */
  public int getAge() {

    LocalDate birthDate = LocalDate.of(getBirthYear(), getBirthMonth(), getBirthDay());
    LocalDate todayDate = LocalDate.now();

    this.age = Period.between(birthDate, todayDate).getYears();

    return this.age;
  }

  /**
   * @return Clients birth day.
   */
  public int getBirthDay() {
    return birthDay;
  }

  /**
   * @return Clients birth month.
   */
  public int getBirthMonth() {
    return birthMonth;
  }

  /**
   * @return Clients birth year.
   */
  public int getBirthYear() {
    return birthYear;
  }

  /**
   * @return Clients industry.
   */
  public String getIndustry() {
    return industry;
  }

  /**
   * @return Clients phone number.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * @return Clients email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * @return Clients presenting issue.
   */
  public String getPresentingIssue() {
    return presentingIssue;
  }


  /**
   * @param name to client.
   */
  // Setter
  public void setName(String name) { this.name = name; }

  /**
   * @param lastName to client.
   */
  public void setLastName(String lastName) { this.lastName = lastName; }

  /**
   * @param birthDay to client.
   */
  public void setBirthDay(int birthDay) {
    this.birthDay = birthDay;
  }

  /**
   * @param birthMonth to client.
   */
  public void setBirthMonth(int birthMonth) {
    this.birthMonth = birthMonth;
  }

  /**
   * @param birthYear to client.
   */
  public void setBirthYear(int birthYear) {
    this.birthYear = birthYear;
  }

  /**
   * @param industry to client.
   */
  public void setIndustry(String industry) { this.industry = industry; }

  /**
   * @param phoneNumber to client.
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * @param email to client.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @param presentingIssue to client.
   */
  public void setPresentingIssue(String presentingIssue) { this.presentingIssue = presentingIssue; }

  /**
   * Print the client information
   */
  // Methods
  public void printClient() {
    String BOLT = "\033[1m";
    String END = "\033[0m";

    String pI = this.presentingIssue;
    StringBuilder pITxt = new StringBuilder(pI);
    int i = 0;
    while ((i = pITxt.indexOf(" ", i + 35)) != -1) {
      pITxt.replace(i, i + 1, "\n");
    }


    System.out.println(
        "\n========================================" +
            "\n" + BOLT + "Client name:\t\t" + END + this.name +
            "\n" + BOLT + "Client lastName:\t" + END + this.lastName +
            "\n" + BOLT + "Age:\t\t\t\t" + END + this.age +
            "\n" + BOLT + "Date:\t\t\t\t" + END + this.birthDay + '/' + this.birthMonth + '/' + this.birthYear +
            "\n" + BOLT + "Industry:\t\t\t" + END + this.industry +
            "\n" + BOLT + "PhoneNumber:\t\t" + END + this.phoneNumber +
            "\n" + BOLT + "Email:\t\t\t\t" + END + this.email +
            "\n\n" + BOLT + "PresentingIssue\n" + END + pITxt.toString() +
            "\n========================================\n");

    System.out.println("Press enter to continue");
    this.scanner.nextLine();
  }

  /**
   * @return toSting for saving the client.
   */
  @Override
  public String toString() {
    return this.name + ';' +
        this.lastName + ';' +
        this.birthDay + ';' +
        this.birthMonth + ';' +
        this.birthYear + ';' +
        this.industry + ';' +
        this.phoneNumber + ';' +
        this.email + ';' +
        this.presentingIssue;
  }

}

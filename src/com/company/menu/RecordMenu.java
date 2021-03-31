package com.company.menu;

import com.company.clientBase.ClientsList;
import com.company.recordBase.Record;
import com.company.recordBase.RecordWrapper;
import com.company.validations.IntegerValidations;
import com.company.validations.StringValidations;

import java.util.Scanner;

/**
 * The menu with options for records.
 */
public class RecordMenu {

  private ClientsList clientsList = new ClientsList();
  private RecordWrapper records;
  private final Scanner scanner = new Scanner(System.in);

  public RecordMenu() {
  }

  /**
   * Menu over record options.
   *
   * @param clientsList list of clients.
   * @param records records.
   */
  // Methods
  public void recordMenu(ClientsList clientsList ,RecordWrapper records) {

    this.clientsList = clientsList;
    this.records = records;
    records.createReadRecords();

    boolean back = false;
    int option;

    while (!back) {

      printClientMenu();
      option = new IntegerValidations().inputOption();

      switch (option) {

        case 1:
          if (records.getReadRecords().isEmpty()) {
            System.out.println("No records");
          } else {
            printRecords();
          }
          break;
        case 2:
          createRecord();
          break;
        case 3:
          removeRecord();
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
        "\t1. Print record\n" +
        "\t2. Add record\n" +
        "\t3. Remove record\n" +
        "\t9. Back\n"
    );
  }

  private void printRecords() {
    int clientNumber = new IntegerValidations().inputClientNumber(this.clientsList);

    if (records.getReadRecords().get(clientNumber).getRecords().isEmpty()) {
      System.out.println("No records");
    } else {
      records.getReadRecords().get(clientNumber).printRecords();
      System.out.println("Press enter to continue");
      this.scanner.nextLine();
    }


  }

  private void createRecord() {
    if (clientsList.getClientList().isEmpty()) {
      System.out.println("No clients");
    } else {
      int clientNumber = new IntegerValidations().inputClientNumber(this.clientsList);
      records.getReadRecords().get(clientNumber).createRecord();
    }

  }

  private void removeRecord() {

    if (!clientsList.getClientList().isEmpty()) {

      int client = new IntegerValidations().inputClientNumber(this.clientsList);
      if (!records.getReadRecords().get(client).getRecords().isEmpty()) {

        records.getReadRecords().get(client).printDates();

        String input = new StringValidations().makeDateOnly();

        Record record = records.getReadRecords().get(client).findRecordByDate(input);
        records.getReadRecords().get(client).removeRecord(record);
      } else {
        System.out.println("No records");
      }
    } else {
      System.out.println("No clients");
    }
  }

}

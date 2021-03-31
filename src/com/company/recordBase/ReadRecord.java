package com.company.recordBase;

import com.company.clientBase.Client;
import com.company.clientBase.ClientsList;
import com.company.validations.StringValidations;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

/**
 * Create the records of a client.
 */
public class ReadRecord {

  private final ArrayList<Record> records = new ArrayList<>();
  private final Client client;
  private final File finalFile;
  private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
  private final ClientsList clientList = new ClientsList();

  /**
   * Create a list of records.
   *
   * @param client to get record.
   */
  public ReadRecord(Client client) {
    this.client = client;
    String fileName = client.getEmail() + "Record.txt";
    String dirName = "Records";
    File dir = new File(dirName);
    finalFile = new File(dir, fileName);
    removeFileIfEmpty();
    loadRecordFromFile();
  }

  /**
   * @return the client of the records.
   */
  // Getter
  public Client getClient() {
    return client;
  }

  /**
   * @return list of records.
   */
  public ArrayList<Record> getRecords() {
    return records;
  }

  /**
   * Create a record.
   */
  public void createRecord() {
    String date = new StringValidations().makeDateOnly();
    if (records.isEmpty()) {
      String recordTxt = new StringValidations().makeRecord();
      Record record1 = new Record(date, this.client, recordTxt);
      records.add(record1);
      sortRecords();
      saveRecordToFile();
    } else {
      for (Record record : records) {
        if (format.format(record.getDate()).equals(date)) {
          System.out.println("Date already exist");
        } else {
          String recordTxt = new StringValidations().makeRecord();
          Record record1 = new Record(date, this.client, recordTxt);
          records.add(record1);
          sortRecords();
          saveRecordToFile();
        }
        return;
      }
    }

  }

  /**
   * remove a record.
   *
   * @param record to remove.
   */
  public void removeRecord(Record record) {
    if (this.records.isEmpty()) {
      System.out.println("No record to remove");
    } else {
      this.records.remove(record);
      saveRecordToFile();
      removeFileIfEmpty();
    }
  }

  /**
   * Find a record by searching the date.
   *
   * @param date of the record
   * @return record found by date.
   */
  public Record findRecordByDate(String date) {
    Date date1 = new Date();

    try {
      date1 = format.parse(date);
    } catch (Exception e) {
      System.out.println("ERROR - Date incorrect");
      //e.printStackTrace();
    }

    for (Record record : records) {
      if (date1.equals(record.getDate())) {
        return record;
      }
    }

    System.out.println("Date do not exist");
    return null;
  }

  /**
   * Print dates of all records.
   */
  public void printDates() {
    System.out.println("\t--------");
    for (Record record : records) {
      System.out.println("\t" + format.format(record.getDate()));
      System.out.println("\t--------");
    }
  }

  /**
   * Print records sorted by date.
   */
  public void printRecords() {
    if (records.isEmpty()) {
      System.out.println("No records yet");
    } else {
      for (Record record : records) {

        String record1 = record.getRecord();
        StringBuilder recordTxt = new StringBuilder(record1);
        int i = 0;
        while ((i = recordTxt.indexOf(" ", i + 90)) != -1) {
          recordTxt.replace(i, i + 1, "\n");
        }

        String date = "\033[4m" + format.format(record.getDate()) + "\033[0m";

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("%57s", date);
        System.out.println();
        System.out.println(recordTxt.toString());
      }
      System.out.println("----------------------------------------------------------------------------------------------------");
    }


  }

  /**
   * Sort records.
   */
  private void sortRecords() {
    this.records.sort(Comparator.comparingLong(r -> r.getDate().getTime()));
  }

  /**
   * Save records to file, named by client email.
   */
  // File
  public void saveRecordToFile() {

    try {
      FileWriter fileWriter = new FileWriter(finalFile);
      Writer output = new BufferedWriter(fileWriter);

      for (Record record : this.records) {
        output.write(record.toString() + "\n");
      }

      output.close();
      //System.out.println("Successful saved file");
    } catch (Exception e) {
      System.out.println("ERROR - while saving file");
      //e.printStackTrace();
    }
  }

  private void removeFileIfEmpty() {
    if (finalFile.length() < 1) {
      boolean fileDeleted = finalFile.delete();
      if (!fileDeleted) {
        System.out.println(finalFile + " not found.");
      }

    }
  }

  /**
   * Load records from file, by client email.
   */
  public void loadRecordFromFile() {

    try {
      if (!finalFile.exists()) {
        //if file don't exist, don't load it
        return;
      }

      Scanner fileScanner = new Scanner(this.finalFile);
      String recordInfo;

      if (!fileScanner.hasNextLine()) {
        System.out.println("List empty");
      } else {
        BufferedReader input = new BufferedReader(new FileReader(this.finalFile));
        if (!input.ready()) {
          throw new IOException();
        }

        while (fileScanner.hasNextLine()) {
          recordInfo = fileScanner.nextLine();
          String[] records = recordInfo.split(";");

          if (emailMatch(records[1])) {
            Client client = loadClient(records[1]);
            Record record = new Record(records[0], client, records[2]);
            this.records.add(record);
          } else {
            System.out.println("Wrong email in the file");
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

  private Client loadClient(String email) {

    for (Client client : this.clientList.getClientList()) {
      if (client.getEmail().equals(email)) {
        return client;
      }
    }
    return null;
  }

  private boolean emailMatch(String email) throws IOException {
    clientList.loadClientsFromFile();
    for (Client client : this.clientList.getClientList()) {
      if (client.getEmail().equals(email)) {
        return true;
      }
    }
    return false;
  }

}

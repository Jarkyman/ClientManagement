package com.company.clientBase;

import com.company.recordBase.RecordWrapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The list of clients.
 */
public class ClientsList {

  private final List<Client> clients = new ArrayList<>();
  private final File fileName = new File("ClientList.txt");

  public ClientsList() {
  }

  /**
   * @return the list of clients.
   */
  // Getter
  public List<Client> getClientList() {
    return clients;
  }


  /**
   * Add client to the list
   *
   * @throws IOException Saving file.
   */
  // Methods
  public void addClient() throws IOException {
    this.clients.add(new Client());
    saveClientsToFile();
  }

  /**
   * Delete the client from file
   * (This will not delete the appointments the client have)
   *
   * @param client to remove.
   * @param records to remove.
   */
  public void removeClient(Client client, RecordWrapper records) {
    this.clients.remove(client);
    saveClientsToFile();

    String fileName = client.getEmail() + "Record.txt";
    File dir = new File("Records");
    File clientRecordFile = new File(dir, fileName);

    if (clientRecordFile.delete()) {
      records.removeRecord(client.getEmail());
      //System.out.println(fileName + " Deleted");

    }

  }

  /**
   * Print the list of clients.
   *
   * @param clients list of clients.
   */
  public void printClientList(List<Client> clients) {
    int count = 1;
    System.out.println("-------------------------------------------------------------------------------------------------------");
    System.out.printf("%3s %15s %20s %10s %30s %20s", "nr.", "Name", "Lastname", "Age", "Email", "Presenting issue");
    System.out.println();
    System.out.println("-------------------------------------------------------------------------------------------------------");

    for (Client c : clients) {
      String name = c.getName();
      String lastName = c.getLastName();
      int age = c.getAge();
      String email = c.getEmail();
      String issue = c.getPresentingIssue();

      if (name.length() > 15) {
        name = name.substring(0, 12) + "..";
      }
      if (lastName.length() > 20) {
        lastName = lastName.substring(0, 17) + "..";
      }
      if (email.length() > 30) {
        email = email.substring(0, 27) + "..";
      }
      if (issue.length() > 20) {
        issue = issue.substring(0, 17) + "..";
      }
      System.out.format("%3s %15s %20s %10d %30s %20s", count++, name, lastName, age, email, issue);
      System.out.println();
    }
    System.out.println("-------------------------------------------------------------------------------------------------------\n");


  }

  /**
   * Save list of clients to file.
   */
  // File
  public void saveClientsToFile() {

    try {
      FileWriter fileWriter = new FileWriter(this.fileName);
      Writer output = new BufferedWriter(fileWriter);

      for (Client client : this.clients) {
        output.write(client.toString() + "\n");
      }

      output.close();
      //System.out.println("Successful saved file");
    } catch (Exception e) {
      System.out.println("ERROR - while saving file");
      //e.printStackTrace();
    }
  }

  /**
   * Load list of clients from file.
   */
  public void loadClientsFromFile() {

    try {
      boolean newFileCreated = this.fileName.createNewFile(); // Create file if file not exist

      if (newFileCreated) {
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
          String[] info = clientInfo.split(";");
          Client client = new Client(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]), info[5], info[6], info[7], info[8]);
          this.clients.add(client);
        }
        input.close();
        //System.out.println("Loaded Clients successfully");
      }

    } catch (IOException e) {
      System.out.println("ERROR - while loading file");
      //e.printStackTrace();
    }

  }

}

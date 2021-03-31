package com.company.menu;

import com.company.clientBase.ClientsList;
import com.company.recordBase.RecordWrapper;
import com.company.validations.IntegerValidations;

import java.io.IOException;
import java.util.Scanner;

/**
 * The menu with options for clients.
 */
public class ClientMenu {

  private final Scanner scanner = new Scanner(System.in);
  private final ClientsList clientsList = new ClientsList();
  private final RecordWrapper records = new RecordWrapper(clientsList);
  private final ConfigurationMenu cfgMenu = new ConfigurationMenu();
  private final RecordMenu recordMenu = new RecordMenu();

  public ClientMenu() {
  }

  /**
   * @return list of clients.
   */
  // Getter
  public ClientsList getClientsList() {
    return this.clientsList;
  }

  // Methods
  /**
   * Print the menu with options.
   *
   * @throws IOException Saving file.
   */
  public void clientMenu() throws IOException {

    boolean back = false;
    int option;

    while (!back) {

      printClientMenu();
      option = new IntegerValidations().inputOption();

      switch (option) {
        case 1:
          if (this.clientsList.getClientList().isEmpty()) {
            System.out.println("No clients");
          } else {
            this.clientsList.printClientList(this.clientsList.getClientList());
            System.out.println("Press enter to continue");
            this.scanner.nextLine();
          }
          break;
        case 2:
          this.clientsList.addClient();
          break;
        case 3:
          if (this.clientsList.getClientList().isEmpty()) {
            System.out.println("No clients");
          } else {
            this.cfgMenu.configMenu(this.clientsList, this.records);
          }
          break;
        case 4:
          this.recordMenu.recordMenu(this.clientsList, this.records);
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
        "\t1. List of clients\n" +
        "\t2. Add client\n" +
        "\t3. Client configuration\n" +
        "\t4. Client records\n" +
        "\t9. Back\n"
    );
  }

}

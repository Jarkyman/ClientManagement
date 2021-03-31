package com.company.menu;

import com.company.clientBase.Client;
import com.company.clientBase.ClientsList;
import com.company.recordBase.RecordWrapper;
import com.company.validations.IntegerValidations;
import com.company.validations.StringValidations;

/**
 * The menu with options for configure a client.
 */
public class ConfigurationMenu {

  private ClientsList clientsList = new ClientsList();

  // Setter
  private void setClientsList(ClientsList clientsList) {
    this.clientsList = clientsList;
  }

  /**
   * The configuration options
   *
   * @param clientList list of clients.
   * @param records records.
   */
  // Methods
  public void configMenu(ClientsList clientList, RecordWrapper records) {

    setClientsList(clientList);
    Client client = chooseClient();

    boolean back = false;
    int option;

    while (!back) {
      System.out.println("Configure " + client.getName() + " " + client.getLastName());

      printConfigureClientMenu();
      option = new IntegerValidations().inputOption();

      switch (option) {

        case 1:
          editMenu(client);
          break;
        case 2:
          this.clientsList.removeClient(client, records);
          System.out.println(client.getName() + " " + client.getLastName() + " removed");
          back = true;
          break;
        case 3:
          int clientNumber = this.clientsList.getClientList().indexOf(client);
          this.clientsList.getClientList().get(clientNumber).printClient();
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


  private Client chooseClient() {
    String print = "Witch client do you want to configure";

    return this.clientsList.getClientList().get(new IntegerValidations().inputClientNumber(this.clientsList, print));
  }

  private void printConfigureClientMenu() {
    System.out.println("\n" +
        "\t1. Edit Client\n" +
        "\t2. Remove Client\n" +
        "\t3. Clients info\n" +
        "\t9. Back\n"
    );
  }

  /**
   * The menu to change a client.
   *
   * @param client to configure.
   */
  public void editMenu(Client client) {

    boolean back = false;
    int option;

    System.out.println("Edit " + client.getName() + " " + client.getLastName());

    while (!back) {
      printEditClientMenu();
      option = new IntegerValidations().inputOption();

      switch (option) {
        case 1:
          client.setName(new StringValidations().makeName());
          System.out.println("Name change to: " + client.getName());
          break;
        case 2:
          client.setLastName(new StringValidations().makeLastName());
          System.out.println("Lastname change to: " + client.getLastName());
          break;
        case 3:
          client.setBirthDay(new IntegerValidations().makeBirthDay(client.getBirthMonth(), client.getBirthYear()));
          client.setBirthMonth(new IntegerValidations().makeBirthMonth());
          client.setBirthYear(new IntegerValidations().makeBirthYear());
          System.out.println("Date change to: " + client.getBirthDay() + "/" + client.getBirthMonth() + "/" + client.getBirthYear());
          System.out.println("Age is now: " + client.getAge());
          break;
        case 4:
          client.setIndustry(new StringValidations().makeIndustry());
          System.out.println("Industry change to: " + client.getIndustry());
          break;
        case 5:
          client.setPhoneNumber(new StringValidations().makePhoneNumber());
          System.out.println("Phone number change to: " + client.getPhoneNumber());
          break;
        case 6:
          client.setEmail(new StringValidations().makeEmail());
          System.out.println("Email change to: " + client.getEmail() );
          break;
        case 7:
          client.setPresentingIssue(new StringValidations().makePresentingIssue());
          System.out.println("Presenting issue change to: " + client.getPresentingIssue());
          break;
        case 9:
          back = true;
          this.clientsList.saveClientsToFile();
          break;
        default:
          System.out.println("Not a valid option");
          break;
      }
    }


  }

  private void printEditClientMenu() {
    System.out.println("\n" +
        "\t1. Edit name\n" +
        "\t2. Edit Lastname\n" +
        "\t3. Edit birth date\n" +
        "\t4. Edit industry\n" +
        "\t5. Edit phone number\n" +
        "\t6. Edit email\n" +
        "\t7. Edit presenting issue\n" +
        "\t9. Back\n"
    );
  }

}

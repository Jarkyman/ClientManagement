package com.company.recordBase;

import com.company.clientBase.Client;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The record object class, make a record
 */
public class Record {

  private final Date date;
  private final String record;
  private Client client;

  private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

  /**
   * Make a record.
   *
   * @param date of record.
   * @param client to record.
   * @param record text.
   */
  public Record(String date, Client client, String record) {
    Date date1 = new Date();

    try {
      date1 = this.format.parse(date);
    } catch (Exception e) {
      System.out.println("ERROR - Date incorrect");
      //e.printStackTrace();
    }

    this.date = date1;
    this.client = client;
    this.record = record;
  }

  /**
   * @return record date.
   */
  // Getter
  public Date getDate() {
    return date;
  }

  /**
   * @return record.
   */
  public String getRecord() {
    return record;
  }

  /**
   * @return the client.
   */
  public Client getClient() {
    return client;
  }

  /**
   * @param client to set.
   */
  // Setter
  public void setClient(Client client) {
    this.client = client;
  }

  /**
   * @return toSting for saving the record.
   */
  //Method
  @Override
  public String toString() {
    return this.format.format(this.date) + ';' + client.getEmail() + ';' + this.record ;
  }
}

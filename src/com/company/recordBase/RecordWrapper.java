package com.company.recordBase;

import com.company.clientBase.ClientsList;

import java.util.ArrayList;

/**
 * Creating the records for each client with records.
 */
public class RecordWrapper {

  private final ArrayList<ReadRecord> readRecords = new ArrayList<>();
  private final ClientsList clientsList;

  /**
   * creating clients records.
   *
   * @param clientsList to get all clients.
   */
  public RecordWrapper(ClientsList clientsList) {
    this.clientsList = clientsList;
    createReadRecords();
  }

  /**
   * @return list of clients with records.
   */
  // Getter
  public ArrayList<ReadRecord> getReadRecords() {
    return readRecords;
  }

  /**
   * Remove record, by searching the email.
   *
   * @param email of client to remove.
   */
  public void removeRecord(String email) {
    ReadRecord readRecord = null;
    for (ReadRecord r : readRecords) {
      if (r.getClient().getEmail().equals(email)) {
        readRecord = r;
      }
    }

    readRecords.remove(readRecord);
  }

  /**
   * Creating all records, if client have a record.
   */
  public void createReadRecords() {

    for (int i = 0; i < clientsList.getClientList().size(); i++) {
      readRecords.add(new ReadRecord(clientsList.getClientList().get(i)));
    }

  }




}

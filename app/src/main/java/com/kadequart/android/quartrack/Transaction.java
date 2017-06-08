package com.kadequart.android.quartrack;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by jeetkunedo on 19/05/2017.
 */

public class Transaction extends RealmObject {
  private int id;
  private String type;
  private double amount;
  private String notes;
  private Date createdAt = new Date();

  public int getId () { return id; }

  public void setId(int id) {
                            this.id = id;
                                         }

  public String getType () {
    return type;
  }

  public void setType (String type) {
    this.type = type;
  }

  public double getAmount () {
                             return amount;
                                           }

  public void setAmount (double amount) {
                                        this.amount = amount;
                                                             }

  public String getNotes () {
                            return notes;
                                         }

  public void setNotes (String notes) {
                                      this.notes = notes;
                                                         }

  public Date getCreatedAt () {
                              return createdAt;
    }
}

package com.enba.integrate.kafka.message;

/**
 * Enba02Message
 */
public class Enba02Message {

  public static final String TOPIC = "ENBA_02";

  /**
   * 编号
   */
  private Integer id;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Enba02Message{" +
        "id=" + id +
        '}';
  }
}

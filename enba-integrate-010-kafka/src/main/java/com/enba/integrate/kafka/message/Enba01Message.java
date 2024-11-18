package com.enba.integrate.kafka.message;

/**
 * Enba01Message
 */
public class Enba01Message {

  public static final String TOPIC = "ENBA_01";

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
    return "Enba01Message{" +
        "id=" + id +
        '}';
  }
}

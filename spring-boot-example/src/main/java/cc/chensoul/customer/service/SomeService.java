package cc.chensoul.customer.service;

public class SomeService {
  public void performTask() {

    System.out.println("Performing task ...");

    try {
      Thread.sleep(1000L);
      System.gc();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}

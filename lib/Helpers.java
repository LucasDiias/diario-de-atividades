package lib;

import java.util.Scanner;

public class Helpers {
  Scanner sc = new Scanner(System.in);

  public String input(String titulo) {
    System.out.print(titulo);
    return sc.nextLine();
  }

  public void clear() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

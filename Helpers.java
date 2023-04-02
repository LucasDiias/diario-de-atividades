import java.util.Scanner;

public class Helpers {
  Scanner sc = new Scanner(System.in);

  public String input(String titulo) {
    System.out.print(titulo);
    return sc.nextLine();
  }
}

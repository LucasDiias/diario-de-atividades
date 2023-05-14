package lib;

import java.util.Scanner;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

// Todos os métodos são estáticos, então não é necessário instanciar a classe
public class Helpers {
  static Scanner sc = new Scanner(System.in);

  // Método baseado no input do python, reduzindo as linhas para o print e o
  // nextLine
  public static String input(String titulo) {
    System.out.print(titulo);
    return sc.nextLine();
  }

  // Método para limpar o terminal do windows
  public static void clear() {
    try {
      if (System.getProperty("os.name").contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        System.out.print("\033\143");
      }
    } catch (Exception e) {
      System.out.println("Erro ao limpar o terminal.\nMensagem de erro: " + e.getMessage());
    }
  }

  // Método para validar a data
  public static boolean validaData(String data) {
    try {
      // Lista com os meses que possuem 31 dias
      List<Integer> mesesTrintaEUm = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));

      // Verifica se a data está no formato correto (dd/mm/aaaa)
      if (!data.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/([0-9]{4})$")) {
        throw new Exception();
      }

      // Separa a data em dia, mês e ano
      String[] dataSplit = data.split("/");
      int dia = Integer.parseInt(dataSplit[0]);
      int mes = Integer.parseInt(dataSplit[1]);

      // Verifica se o dia está dentro do mês
      if (mesesTrintaEUm.contains(mes) && dia > 31) {
        throw new Exception();
      } else if (mes == 2 && dia > 29) {
        throw new Exception();
      } else if (dia > 30) {
        throw new Exception();
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static LocalDate stringToDate(String data) {
    String[] dataSplit = data.split("/");
    int dia = Integer.parseInt(dataSplit[0]);
    int mes = Integer.parseInt(dataSplit[1]);
    int ano = Integer.parseInt(dataSplit[2]);
    return LocalDate.of(ano, mes, dia);
  }
}

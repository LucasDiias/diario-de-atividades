import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class ControleDeAtividades {
  Scanner sc = new Scanner(System.in);
  List<Atividade> atividades = new ArrayList<Atividade>();

  public boolean cadastro(){
    return true; //Temporário
  }

  public boolean pesquisar(){
    return true; //Temporário
  }

  public boolean atualizar(){
    return true; //Temporário
  }

  public boolean remover(){
    return true; //Temporário
  }

  public boolean testar(){
    return true; //Temporário
  }
  
  public void inicia() {
    boolean controle = true;
    while(controle) {
      int escolha = mostraMenu();
      
      switch (escolha) {
        case 1:
          controle = cadastro();
          break;
        case 2:
          controle = pesquisar();
          break;
        case 3:
          controle = atualizar();
          break;
        case 4:
          controle = remover();
          break;
        case 5:
          controle = testar();
          break;
        case 6:
          controle = false;
          break;
      }
    }
  }
  
  public int mostraMenu() {
    List<Integer> opcoes = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
    
    System.out.println("DIÁRIO DE ATIVIDADES\n----------------------------------------------");
    System.out.println("1- Cadastrar Atividade\n2- Pesquisar Atividade\n3- Atualizar Atividade\n4- Remover Atividade\n5- Testar Programa\n6- Sair");
    
    while(true){
      try {
        System.out.print("Insira o número correspondente à sua escolha: ");
        int escolha = Integer.parseInt(sc.nextLine());
        if (!opcoes.contains(escolha)) {
          throw new Exception();
        }
        return escolha;
      } catch (Exception e) {
        System.err.println("Insira um valor válido");
      }
    }
  }
}
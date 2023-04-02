import java.util.List;

import lib.Helpers;

import java.util.ArrayList;
import java.util.Arrays;

public class ControleDeAtividades {
  List<Atividade> atividades = new ArrayList<Atividade>();
  Helpers helper = new Helpers();

  public boolean cadastro() {
    int escolha = mostraMenuCadastro();
    boolean retorno = false;
    try {
      switch (escolha) {
        case 1:
          cadastraAvFisica();
          retorno = true;
          break;
        case 2:
          cadastraAvLazer();
          retorno = true;
          break;
        case 3:
          cadastraAvTrabalho();
          retorno = true;
          break;
      }
    } catch (Exception e) {
      retorno = false;
    }
    return retorno;
  }

  public boolean pesquisar() {
    return true; // Temporário
  }

  public boolean atualizar() {
    return true; // Temporário
  }

  public boolean remover() {
    return true; // Temporário
  }

  public boolean testar() {
    for (Atividade a : atividades) {
      System.out.println(a.descricao);
    }
    return true; // Temporário
  }

  public void inicia() {
    boolean controle = true;
    while (controle) {
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
    System.out.println(
        "1- Cadastrar Atividade\n2- Pesquisar Atividade\n3- Atualizar Atividade\n4- Remover Atividade\n5- Testar Programa\n6- Sair");

    while (true) {
      try {
        int escolha = Integer.parseInt(helper.input("Insira o número correspondente à sua escolha: "));
        if (!opcoes.contains(escolha)) {
          throw new Exception();
        }
        return escolha;
      } catch (Exception e) {
        System.err.println("Insira um valor válido");
      }
    }
  }

  public int mostraMenuCadastro() {
    List<Integer> opcoes = new ArrayList<Integer>(Arrays.asList(1, 2, 3));

    System.out.println("CADASTRO DE ATIVIDADE\n----------------------------------------------");
    System.out.println("1- Atividade Física\n2- Atividade de Lazer\n3- Atividade de Trabalho");

    while (true) {
      try {
        int escolha = Integer.parseInt(helper.input("Insira o número correspondente à sua escolha: "));
        if (!opcoes.contains(escolha)) {
          throw new Exception();
        }
        return escolha;
      } catch (Exception e) {
        System.err.println("Insira um valor válido");
      }
    }
  }

  public void cadastraAvFisica() {
    System.out.println("CADASTRO DE ATIVIDADE FÍSICA\n----------------------------------------------");

    String descricao = helper.input("Insira uma descrição da atividade: ");
    String duracao = helper.input("Insira a duração da atividade (em minutos): ");
    String intensidade = helper.input(
        "Insira a intensidade da atividade \nFraco (2)\nIntenso (3)\nVigoroso (4)\n\nInsira o número correspondente: ");
    String satisfacao = helper.input(
        "Insira a satisfação com a atividade \nAgradável (1)\nDesagradável (-1)\n\nInsira o número correspondente: ");
    String data = helper.input("Insira a data da atividade (dd/mm/aaaa): ");

    AvFisica avFisica = new AvFisica(intensidade, duracao, satisfacao, descricao, data);
    atividades.add(avFisica);
  }

  public void cadastraAvLazer() {
    System.out.println("CADASTRO DE ATIVIDADE DE LAZER\n----------------------------------------------");
    String descricao = helper.input("Insira uma descrição da atividade: ");
    String duracao = helper.input("Insira a duração da atividade (em minutos): ");
    String satisfacao = helper.input(
        "Insira a satisfação com a atividade \nAgradável (1)\nDesagradável (-1)\n\nInsira o número correspondente: ");
    String data = helper.input("Insira a data da atividade (dd/mm/aaaa): ");

    AvLazer avLazer = new AvLazer(duracao, satisfacao, descricao, data);
    atividades.add(avLazer);
  }

  public void cadastraAvTrabalho() {
    System.out.println("CADASTRO DE ATIVIDADE DE TRABALHO\n----------------------------------------------");
    String descricao = helper.input("Insira uma descrição da atividade: ");
    String duracao = helper.input("Insira a duração da atividade (em minutos): ");
    String satisfacao = helper.input(
        "Insira a satisfação com a atividade \nAgradável (1)\nDesagradável (-1)\n\nInsira o número correspondente: ");
    String data = helper.input("Insira a data da atividade (dd/mm/aaaa): ");
    String dificuldade = helper.input(
        "Insira a intensidade da atividade \nFraco (2)\nIntenso (3)\nVigoroso (4)\n\nInsira o número correspondente: ");

    AvTrabalho avTrabalho = new AvTrabalho(dificuldade, duracao, satisfacao, descricao, data);
    atividades.add(avTrabalho);
  }
}
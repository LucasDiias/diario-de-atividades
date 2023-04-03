import atividades.Atividade;
import atividades.AvFisica;
import atividades.AvLazer;
import atividades.AvTrabalho;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import lib.Helpers;

public class ControleDeAtividades {
  List<Atividade> atividades = new ArrayList<Atividade>();
  Helpers helper = new Helpers();

  // FEITO //
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

    System.out.println("\n\nAtividade cadastrada com sucesso!");
    helper.input("Pressione ENTER para continuar...");
    return retorno;
  }

  // ANALISE 💀
  public boolean pesquisar() {
    return true; // Temporário
  }

  // FEITO //
  public boolean atualizar() {
    helper.clear();
    listarAtividades();
    int id;
    while (true) {
      id = Integer.parseInt(helper.input("Insira o ID da atividade que deseja atualizar: "));
      if (id < atividades.size()) {
        break;
      } else {
        helper.clear();
        System.err.println("ID inválido");
      }
    }
    Atividade a = atividades.get(id);
    a.atualizaAtividade();
    return true; // Temporário
  }

  // FEITO //
  public boolean remover() {
    helper.clear();
    listarAtividades();
    int id;
    while (true) {
      id = Integer.parseInt(helper.input("Insira o ID da atividade que deseja remover: "));
      if (id < atividades.size()) {
        break;
      } else {
        helper.clear();
        System.err.println("ID inválido");
      }
    }
    atividades.remove(id);
    return true;
  }

  // FEITO //
  public boolean testar() {
    helper.clear();

    atividades.add(new AvFisica("2", "30", "1", "Corrida", "10/10/2020"));
    atividades.add(new AvFisica("3", "30", "-1", "Caminhada", "10/10/2020"));
    atividades.add(new AvFisica("4", "30", "1", "Paintball", "12/10/2020"));
    atividades.add(new AvLazer("30", "-1", "Praia", "11/07/2020"));
    atividades.add(new AvLazer("30", "1", "Cinema", "11/07/2020"));
    atividades.add(new AvLazer("30", "-1", "Parque", "13/07/2020"));
    atividades.add(new AvTrabalho("1", "30", "1", "Projeto", "07/01/2020"));
    atividades.add(new AvTrabalho("2", "30", "-1", "Estágio", "08/01/2020"));
    atividades.add(new AvTrabalho("3", "30", "1", "Trabalho", "07/01/2020"));

    System.out.println("Atividades de teste cadastradas com sucesso!");
    helper.input("\n\nPressione ENTER para continuar...");
    return true;
  }

  // FEITO //
  public boolean listarAtividades() {
    helper.clear();
    if (atividades.size() <= 0) {
      System.out.println("Não há atividades cadastradas");
      return true;
    }
    for (Atividade a : atividades) {
      System.out.println("\n\nID: " + atividades.indexOf(a) + a.mostraAtividade());
    }

    helper.input("\n\nPressione ENTER para continuar...");
    return true;
  }

  // FEITO //
  public boolean resumo() {
    int escolha = mostraMenuResumo();
    switch (escolha) {
      case 1:
        resumoDia();
        break;
      case 2:
        resumoSemana();
        break;
      case 3:
        resumoMes();
        break;
    }

    helper.input("\n\nPressione ENTER para continuar...");
    return true; // Temporário
  }

  //
  public void resumoDia() {
    HashMap<Integer, ArrayList<Atividade>> dias = new HashMap<Integer, ArrayList<Atividade>>();
    helper.clear();

    System.out.println("RESUMO DE ATIVIDADES POR DIA");

    for (Atividade a : atividades) {
      if (!dias.containsKey(a.getDia())) {
        dias.put(a.getDia(), new ArrayList<Atividade>(Arrays.asList(a)));
      } else {
        dias.get(a.getDia()).add(a);
      }
    }

    List<Integer> keys = new ArrayList<Integer>(dias.keySet());
    Collections.sort(keys);

    for (Integer dia : keys) {
      List<Atividade> atividadesDoDia = dias.get(dia);
      System.out.println("\n\nDia " + atividadesDoDia.get(0).getData());

      for (Atividade a : atividadesDoDia) {
        if (a.getDia() == dia) {
          System.out.println(a.mostraAtividade());
        }
      }
    }
  }

  //
  public void resumoSemana() {
    List<Integer> semanas = new ArrayList<Integer>();
    helper.clear();

    System.out.println("RESUMO DE ATIVIDADES POR SEMANA");

    for (Atividade a : atividades) {
      if (!semanas.contains(a.getSemana())) {
        semanas.add(a.getSemana());
      }
    }
    Collections.sort(semanas);
    for (Integer semana : semanas) {
      System.out.println("\n\nSemana " + semana);
      for (Atividade a : atividades) {
        if (a.getSemana() == semana) {
          System.out.println(a.mostraAtividade());
        }
      }
    }
  }

  //
  public void resumoMes() {
    List<String> meses = new ArrayList<String>();
    helper.clear();

    System.out.println("RESUMO DE ATIVIDADES POR MÊS");

    for (Atividade a : atividades) {
      if (!meses.contains(a.getMes())) {
        meses.add(a.getMes());
      }
    }
    Collections.sort(meses);
    for (String mes : meses) {
      System.out.println("\n\n" + mes);
      for (Atividade a : atividades) {
        if (a.getMes() == mes) {
          System.out.println(a.mostraAtividade());
        }
      }
    }
  }

  //
  public int mostraMenuResumo() {
    List<Integer> opcoes = new ArrayList<Integer>(Arrays.asList(1, 2, 3));

    helper.clear();
    System.out.println("RESUMO DE ATIVIDADES\n----------------------------------------------");
    System.out.println("1- Resumo do Dia\n2- Resumo da Semana\n3- Resumo do Mês");

    while (true) {
      try {
        int escolha = Integer.parseInt(helper.input("Insira o número correspondente à sua escolha: "));
        if (!opcoes.contains(escolha)) {
          throw new Exception();
        }
        return escolha;
      } catch (Exception e) {
        helper.clear();
        System.err.println("Insira um valor válido");
      }
    }
  }

  //
  public int mostraMenu() {
    List<Integer> opcoes = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
    helper.clear();

    System.out.println("DIÁRIO DE ATIVIDADES\n----------------------------------------------");
    System.out.println(
        "1- Cadastrar Atividade\n2- Pesquisar Atividade\n3- Atualizar Atividade\n4- Remover Atividade\n5- Listar Atividades\n6- Apresentar Resumos\n7- Testar Programa\n8- Sair");

    while (true) {
      try {
        int escolha = Integer.parseInt(helper.input("Insira o número correspondente à sua escolha: "));
        if (!opcoes.contains(escolha)) {
          throw new Exception();
        }
        return escolha;
      } catch (Exception e) {
        helper.clear();
        System.err.println("Insira um valor válido");
      }
    }
  }

  //
  public int mostraMenuCadastro() {
    List<Integer> opcoes = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    helper.clear();

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
        helper.clear();
        System.err.println("Insira um valor válido");
      }
    }
  }

  //
  public void cadastraAvFisica() {
    helper.clear();
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

  //
  public void cadastraAvLazer() {
    helper.clear();
    System.out.println("CADASTRO DE ATIVIDADE DE LAZER\n----------------------------------------------");
    String descricao = helper.input("Insira uma descrição da atividade: ");
    String duracao = helper.input("Insira a duração da atividade (em minutos): ");
    String satisfacao = helper.input(
        "Insira a satisfação com a atividade \nAgradável (1)\nDesagradável (-1)\n\nInsira o número correspondente: ");
    String data = helper.input("Insira a data da atividade (dd/mm/aaaa): ");

    AvLazer avLazer = new AvLazer(duracao, satisfacao, descricao, data);
    atividades.add(avLazer);
  }

  //
  public void cadastraAvTrabalho() {
    helper.clear();
    System.out.println("CADASTRO DE ATIVIDADE DE TRABALHO\n----------------------------------------------");
    String descricao = helper.input("\nInsira uma descrição da atividade: ");
    String duracao = helper.input("\nInsira a duração da atividade (em minutos): ");
    String satisfacao = helper.input(
        "\nInsira a satisfação com a atividade \nAgradável (1)\nDesagradável (-1)\n\nInsira o número correspondente: ");
    String data = helper.input("\nInsira a data da atividade (dd/mm/aaaa): ");
    String dificuldade = helper.input(
        "\nInsira a dificuldade da atividade \nFácil (1)\nMédia (2)\nDifícil (3)\n\nInsira o número correspondente: ");

    AvTrabalho avTrabalho = new AvTrabalho(dificuldade, duracao, satisfacao, descricao, data);
    atividades.add(avTrabalho);
  }
}
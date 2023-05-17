import atividades.Atividade;
import atividades.AvFisica;
import atividades.AvLazer;
import atividades.AvTrabalho;
import dao.AtividadeDAO;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.sql.SQLException;

import lib.Helpers;

public class ControleAtividades {
  // List<Atividade> atividades = new ArrayList<Atividade>();
  AtividadeDAO dao = new AtividadeDAO();

  // Método para mostrar o Menu Principal
  public int mostraMenuPrincipal() {
    Helpers.clear(); // Limpa a tela
    // Mostra o menu
    System.out.println("DIÁRIO DE ATIVIDADES\n----------------------------------------------");
    System.out.println(
        "1- Cadastrar Atividade\n2- Pesquisar Atividade\n3- Atualizar Atividade\n4- Remover Atividade\n5- Listar Atividades\n6- Apresentar Resumos\n7- Top 3 Energia\n8- Sair\n");

    // Retorna a opção escolhida
    while (true) {
      try {
        int escolha = Integer.parseInt(Helpers.input("Insira o número correspondente à sua escolha: "));
        // Verifica se a opção escolhida é válida
        if (escolha < 1 || escolha > 8) {
          throw new Exception();
        }
        return escolha;
      } catch (Exception e) {
        System.err.println("\nInsira um valor válido");
      }
    }
  }

  // CADASTRO ******************************************************************
  // Método de controle para cadastrar nova atividade
  public void cadastro() {
    boolean varControle = true;
    while (varControle) {
      // Mostra o menu de cadastro e retorna a opção escolhida
      int escolha = mostraMenuCadastro();

      // Verifica a opção escolhida e chama o método de cadastro correspondente
      try {
        switch (escolha) {
          case 1:
            varControle = !cadastraAvFisica();
            break;
          case 2:
            varControle = !cadastraAvLazer();
            break;
          case 3:
            varControle = !cadastraAvTrabalho();
            break;
          case 4:
            return;
        }
      } catch (Exception e) {
        System.err.println("\nErro ao cadastrar atividade.\nMensagem de erro: " + e.getMessage());
        Helpers.input("Pressione ENTER para continuar...");
        return;
      }
    }

    // Retorna true se a atividade foi cadastrada com sucesso
    System.out.println("\n\nAtividade cadastrada com sucesso!");
    Helpers.input("Pressione ENTER para continuar...");
  }

  // Método para mostrar o menu de cadastro
  public int mostraMenuCadastro() {
    Helpers.clear();
    // Mostra o menu
    System.out.println("CADASTRO DE ATIVIDADE\n----------------------------------------------");
    System.out.println("1- Atividade Física\n2- Atividade de Lazer\n3- Atividade de Trabalho\n4- Voltar\n");

    // Retorna a opção escolhida
    while (true) {
      try {
        int escolha = Integer.parseInt(Helpers.input("Insira o número correspondente à sua escolha: "));
        // Verifica se a opção escolhida é válida
        if (escolha < 1 || escolha > 4) {
          throw new Exception();
        }
        return escolha;
      } catch (Exception e) {
        System.err.println("\nInsira um valor válido");
      }
    }
  }

  // Método para cadastrar uma atividade física
  public boolean cadastraAvFisica() {
    Helpers.clear();
    System.out.println("CADASTRO DE ATIVIDADE FÍSICA\n----------------------------------------------");
    System.out.println("Digite 0 para cancelar o cadastro.");

    // Pega os dados da atividade
    String descricao = Helpers.input("\nInsira uma descrição da atividade: ");
    if (descricao.equals("0")) {
      return false;
    }

    String duracao = Helpers.input("\nInsira a duração da atividade (em minutos): ");
    if (duracao.equals("0")) {
      return false;
    }

    String intensidade = Helpers.input(
        "\nInsira a intensidade da atividade \nFraco (2)\nIntenso (3)\nVigoroso (4)\n\nInsira o número correspondente: ");
    if (intensidade.equals("0")) {
      return false;
    }

    String satisfacao = Helpers.input(
        "\nInsira a satisfação com a atividade \nAgradável (1)\nDesagradável (-1)\n\nInsira o número correspondente: ");
    if (satisfacao.equals("0")) {
      return false;
    }

    String data = Helpers.input("\nInsira a data da atividade (dd/mm/aaaa): ");
    if (data.equals("0")) {
      return false;
    }

    // Cria a atividade
    AvFisica avFisica = new AvFisica(intensidade, duracao, satisfacao, descricao, data);
    // Adiciona a atividade ao banco de dados
    try {
      dao.add(avFisica);
    } catch (Exception e) {
      System.err.println("Erro ao cadastrar atividade.\nMensagem de erro: " + e.getMessage());
      Helpers.input("Pressione ENTER para continuar...");
      return false;
    }
    return true;
  }

  // Método para cadastrar uma atividade de lazer
  public boolean cadastraAvLazer() {
    Helpers.clear();
    System.out.println("CADASTRO DE ATIVIDADE DE LAZER\n----------------------------------------------");
    System.out.println("Digite 0 para cancelar o cadastro.");

    // Pega os dados da atividade
    String descricao = Helpers.input("\nInsira uma descrição da atividade: ");
    if (descricao.equals("0")) {
      return false;
    }

    String duracao = Helpers.input("\nInsira a duração da atividade (em minutos): ");
    if (duracao.equals("0")) {
      return false;
    }

    String satisfacao = Helpers.input(
        "\nInsira a satisfação com a atividade \nAgradável (1)\nDesagradável (-1)\n\nInsira o número correspondente: ");
    if (satisfacao.equals("0")) {
      return false;
    }

    String data = Helpers.input("\nInsira a data da atividade (dd/mm/aaaa): ");
    if (data.equals("0")) {
      return false;
    }

    // Cria a atividade
    AvLazer avLazer = new AvLazer(duracao, satisfacao, descricao, data);
    // Adiciona a atividade ao banco de dados
    try {
      dao.add(avLazer);
    } catch (Exception e) {
      System.err.println("Erro ao cadastrar atividade.\nMensagem de erro: " + e.getMessage());
      Helpers.input("Pressione ENTER para continuar...");
      return false;
    }
    return true;
  }

  // Método para cadastrar uma atividade de trabalho
  public boolean cadastraAvTrabalho() {
    Helpers.clear();
    System.out.println("CADASTRO DE ATIVIDADE DE TRABALHO\n----------------------------------------------");
    System.out.println("Digite 0 para cancelar o cadastro.");

    // Pega os dados da atividade
    String descricao = Helpers.input("\nInsira uma descrição da atividade: ");
    if (descricao.equals("0")) {
      return false;
    }

    String duracao = Helpers.input("\nInsira a duração da atividade (em minutos): ");
    if (duracao.equals("0")) {
      return false;
    }

    String satisfacao = Helpers.input(
        "\nInsira a satisfação com a atividade \nAgradável (1)\nDesagradável (-1)\n\nInsira o número correspondente: ");
    if (satisfacao.equals("0")) {
      return false;
    }

    String data = Helpers.input("\nInsira a data da atividade (dd/mm/aaaa): ");
    if (data.equals("0")) {
      return false;
    }

    String dificuldade = Helpers.input(
        "\nInsira a dificuldade da atividade \nFácil (1)\nMédia (2)\nDifícil (3)\n\nInsira o número correspondente: ");
    if (dificuldade.equals("0")) {
      return false;
    }

    // Cria a atividade
    AvTrabalho avTrabalho = new AvTrabalho(dificuldade, duracao, satisfacao, descricao, data);
    // Adiciona a atividade ao banco de dados
    try {
      dao.add(avTrabalho);
    } catch (Exception e) {
      System.err.println("Erro ao cadastrar atividade.\nMensagem de erro: " + e.getMessage());
      Helpers.input("Pressione ENTER para continuar...");
      return false;
    }
    return true;
  }

  // PESQUISA ******************************************************************
  // Método de controle para pesquisar atividades cadastradas
  public void pesquisar() {
    boolean varControle = true;
    // Enquanto o usuário não escolher a opção de sair, o menu de pesquisa é
    while (varControle) {
      // Mostra o menu de pesquisa
      int escolha = mostraMenuPesquisa();
      // Verifica a opção escolhida e chama o método de pesquisa correspondente
      try {
        switch (escolha) {
          case 1:
            varControle = !pesquisarPorData();
            break;
          case 2:
            varControle = !pesquisarPorTipo();
            break;
          case 3:
            varControle = !pesquisarPorDescricao();
            break;
          case 4:
            return;
        }
      } catch (Exception e) {
        System.err.println("Erro ao pesquisar atividade.\nMensagem de erro: " + e.getMessage());
        Helpers.input("Pressione ENTER para continuar...");
        return;
      }
    }
  }

  // Método para mostrar o menu de pesquisa
  public int mostraMenuPesquisa() {
    Helpers.clear();
    System.out.println("CADASTRO DE ATIVIDADE\n----------------------------------------------");
    System.out.println("1- Atividades por data");
    System.out.println("2- Atividades por tipo");
    System.out.println("3- Atividades por descrição");
    System.out.println("4- Voltar");
    int opcao;
    while (true) {
      try {
        opcao = Integer.parseInt(Helpers.input("\nInsira o número correspondente à sua escolha: "));
        if (opcao < 1 || opcao > 4) {
          throw new Exception();
        }
        break;
      } catch (Exception e) {
        System.err.println("\n\nInsira uma opção válida");
      }
    }
    return opcao;
  }

  // Método para pesquisar atividades por data
  public boolean pesquisarPorData() {
    Helpers.clear();
    System.out.println("PESQUISA POR DATA");
    System.out.println("Digite 0 para cancelar a pesquisa.\n");

    String data = Helpers.input("Insira a data que deseja pesquisar (dd/mm/aaaa): ");

    if (data.equals("0")) {
      return false;
    }

    // Verifica se a data inserida é válida
    while (!Helpers.validaData(data)) {
      System.err.println("\n\nInsira uma data válida");
      data = Helpers.input("Insira a data que deseja pesquisar (dd/mm/aaaa): ");
      if (data.equals("0")) {
        return false;
      }
    }

    List<Atividade> atividades = new ArrayList<Atividade>();
    try {
      atividades = dao.pesquisa(1, data);
    } catch (Exception e) {
      System.err.println("Erro ao pesquisar atividade no banco.\nMensagem de erro: " + e.getMessage());
      Helpers.input("Pressione ENTER para continuar...");
      return false;
    }
    if (atividades.size() == 0) {
      System.out.println("\n\nNenhuma atividade encontrada");
    } else {
      for (Atividade av : atividades) {
        System.out.println(av.mostraAtividade());
      }
    }

    Helpers.input("\n\nPressione ENTER para continuar...");
    return true;
  }

  // Método para pesquisar atividades por tipo
  public boolean pesquisarPorTipo() {
    Helpers.clear();
    System.out.println("PESQUISA POR TIPO");
    int tipo;

    while (true) {
      try {
        String entrada = Helpers.input(
            "Insira o tipo que deseja pesquisar:\n1- Atividade Física\n2- Atividade de Lazer\n3- Atividade de Trabalho\n4- Voltar\n\nInsira o número correspondente:	");
        tipo = Integer.parseInt(entrada);
        // Verifica se o tipo inserido é válido
        if (tipo < 1 || tipo > 4) {
          throw new Exception();
        }
        break;
      } catch (Exception e) {
        System.err.println("\n\nInsira uma opção válida");
      }
    }

    if (tipo == 4) {
      return false;
    }

    List<Atividade> atividades = new ArrayList<Atividade>();
    try {
      atividades = dao.pesquisa(2, tipo + "");
    } catch (Exception e) {
      System.err.println("Erro ao pesquisar atividade no banco.\nMensagem de erro: " + e.getMessage());
      Helpers.input("Pressione ENTER para continuar...");
      return false;
    }

    if (atividades.size() == 0) {
      System.out.println("\n\nNenhuma atividade encontrada");
    } else {
      for (Atividade av : atividades) {
        System.out.println(av.mostraAtividade());
      }
    }

    Helpers.input("\n\nPressione ENTER para continuar...");
    return true;
  }

  // Método para pesquisar atividades por descrição
  public boolean pesquisarPorDescricao() {
    Helpers.clear();
    System.out.println("PESQUISA POR DESCRIÇÃO");
    System.out.println("Digite 0 para cancelar a pesquisa.\n");

    String descricao = Helpers.input("Insira a descrição que deseja pesquisar: ");

    // Verifica se a descrição inserida é válida
    while (descricao.equals("")) {
      System.err.println("\n\nInsira uma descrição válida");
      descricao = Helpers.input("Insira a descrição que deseja pesquisar: ");
    }

    if (descricao.equals("0")) {
      return false;
    }

    List<Atividade> atividades = new ArrayList<Atividade>();
    try {
      atividades = dao.pesquisa(3, descricao);
    } catch (Exception e) {
      System.err.println("Erro ao pesquisar atividade no banco.\nMensagem de erro: " + e.getMessage());
      Helpers.input("Pressione ENTER para continuar...");
      return false;
    }
    if (atividades.size() == 0) {
      System.out.println("\n\nNenhuma atividade encontrada");
    } else {
      for (Atividade av : atividades) {
        System.out.println(av.mostraAtividade());
      }
    }

    Helpers.input("\n\nPressione ENTER para continuar...");
    return true;
  }

  // ATUALIZAR *****************************************************************
  // Método para atualizar atividade existente
  public void atualizar() {
    Helpers.clear();
    // Mostra as atividades cadastradas
    try {
      if (!listarAtividades()) {
        Helpers.input("\n\nPressione ENTER para continuar...");
        return;
      }
    } catch (Exception e) {
      System.err.println("Erro ao listar atividades.\nMensagem de erro: " + e.getMessage());
      Helpers.input("\n\nPressione ENTER para continuar...");
      return;
    }

    System.out.println("\n\nDigite 0 para cancelar a atualização.\n");
    int id;
    // Verifica se o ID inserido é válido
    while (true) {
      try {
        id = Integer.parseInt(Helpers.input("Insira o ID da atividade que deseja atualizar: "));
        if (!dao.getIds().contains(id) && id != 0) {
          throw new Exception();
        }
        break;
      } catch (Exception e) {
        System.err.println("\n\nInsira um ID válido");
      }
    }
    if (id == 0) {
      return;
    }

    // Chama o método atualizaAtividade da atividade correspondente
    try {
      Atividade a = dao.getAtividade(id);
      a.atualizaAtividade();
    } catch (Exception e) {
      System.err.println("Erro ao atualizar atividade.\nMensagem de erro: " + e.getMessage());
      Helpers.input("Pressione ENTER para continuar...");
      return;
    }
  }

  // REMOVER *******************************************************************
  // Método para remover atividade existente
  public void remover() {
    Helpers.clear();
    // Mostra as atividades cadastradas
    try {
      if (!listarAtividades()) {
        Helpers.input("\n\nPressione ENTER para continuar...");
        return;
      }
    } catch (Exception e) {
      System.err.println("Erro ao listar atividades.\nMensagem de erro: " + e.getMessage());
      Helpers.input("\n\nPressione ENTER para continuar...");
      return;
    }

    System.out.println("\n\nDigite 0 para cancelar a remoção.\n");
    int id;
    // Verifica se o ID inserido é válido
    while (true) {
      try {
        id = Integer.parseInt(Helpers.input("Insira o ID da atividade que deseja atualizar: "));
        if (!dao.getIds().contains(id) && id != 0) {
          throw new Exception("ID não encontrado");
        }
        break;
      } catch (Exception e) {
        System.err.println("\n\nInsira um ID válido");
      }
    }
    if (id == 0) {
      return;
    }

    // Remove a atividade do array de atividades
    try {
      dao.delete(id);
    } catch (Exception e) {
      System.err.println("Erro ao remover atividade.\nMensagem de erro: " + e.getMessage());
      Helpers.input("Pressione ENTER para continuar...");
      return;
    }
  }

  // LISTAR ********************************************************************
  // Método para listar as atividades cadastradas
  public boolean listarAtividades() throws SQLException {
    Helpers.clear();

    // Verifica se há atividades cadastradas
    if (dao.getAllAtividades().size() <= 0) {
      System.out.println("Não há atividades cadastradas");
      return false;
    }

    System.out.println("ATIVIDADES CADASTRADAS");

    // Mostra as atividades cadastradas
    for (Atividade a : dao.getAllAtividades()) {
      System.out.println(a.mostraAtividade());
    }
    return true;
  }

  // RESUMO ********************************************************************
  // Método para apresentar o resumo de atividades cadastradas
  public void resumo() {
    // Mostra o menu de resumo e retorna a opção escolhida
    int escolha = mostraMenuResumo();
    // Verifica a opção escolhida e chama o método de resumo correspondente
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
      case 4:
        return;
    }

    Helpers.input("\n\nPressione ENTER para continuar...");
  }

  // Método para mostrar o menu de resumo
  public int mostraMenuResumo() {
    Helpers.clear();
    // Mostra o menu
    System.out.println("RESUMO DE ATIVIDADES\n----------------------------------------------");
    System.out.println("1- Resumo do Dia\n2- Resumo da Semana\n3- Resumo do Mês\n4- Voltar\n");

    // Retorna a opção escolhida
    while (true) {
      try {
        int escolha = Integer.parseInt(Helpers.input("Insira o número correspondente à sua escolha: "));
        // Verifica se a opção escolhida é válida
        if (escolha < 1 || escolha > 4) {
          throw new Exception();
        }
        return escolha;
      } catch (Exception e) {
        System.err.println("\n\nInsira um valor válido");
      }
    }
  }

  // Método para mostrar o resumo por dia
  public void resumoDia() {
    Helpers.clear();
    // Cria um HashMap para armazenar as atividades por dia
    HashMap<Integer, ArrayList<Atividade>> dias = new HashMap<Integer, ArrayList<Atividade>>();

    // Adiciona as atividades ao HashMap
    try {
      for (Atividade a : dao.getAllAtividades()) {
        if (!dias.containsKey(a.getDia())) {
          dias.put(a.getDia(), new ArrayList<Atividade>(Arrays.asList(a)));
        } else {
          dias.get(a.getDia()).add(a);
        }
      }
    } catch (Exception e) {
      System.err.println("Erro ao adicionar atividades ao HashMap: " + e.getMessage());
      Helpers.input("\n\nPressione ENTER para continuar...");
      return;
    }

    // Verifica se há atividades cadastradas
    if (dias.size() == 0) {
      System.out.println("Não há atividades cadastradas");
      return;
    }

    System.out.println("RESUMO DE ATIVIDADES POR DIA");
    // Ordena as chaves do HashMap
    List<Integer> diasOrdenados = new ArrayList<Integer>(dias.keySet());
    Collections.sort(diasOrdenados);

    // Mostra as atividades por dia
    for (Integer dia : diasOrdenados) {
      // Pega as atividades do dia
      List<Atividade> atividadesDoDia = dias.get(dia);

      // Cria variáveis para armazenar os totais de cada categoria
      double totalEnergia = 0;
      double totalBemEstar = 0;

      System.out.println("\n\nDia " + atividadesDoDia.get(0).getData());
      // Mostra as atividades do dia
      for (Atividade a : atividadesDoDia) {
        totalEnergia += a.getGastoDeEnergia();
        totalBemEstar += a.getBemEstar();
        System.out.println("\n" + a.mostraAtividade());
      }
      // Mostra os totais do dia
      System.out.println(String.format("\nTotal de gasto de energia: %.2f", totalEnergia));
      System.out.println(String.format("Total de bem-estar: %.2f", totalBemEstar));
    }
  }

  // Método para mostrar o resumo por semana
  public void resumoSemana() {
    Helpers.clear();
    // Cria um ArrayList para armazenar as semanas
    List<Integer> semanas = new ArrayList<Integer>();

    // Adiciona as semanas ao ArrayList
    try {
      for (Atividade a : dao.getAllAtividades()) {
        if (!semanas.contains(a.getSemana())) {
          semanas.add(a.getSemana());
        }
      }
    } catch (Exception e) {
      System.err.println("Erro ao adicionar semanas ao ArrayList: " + e.getMessage());
      Helpers.input("\n\nPressione ENTER para continuar...");
      return;
    }

    // Verifica se há atividades cadastradas
    if (semanas.size() <= 0) {
      System.out.println("Não há atividades cadastradas");
      return;
    }

    System.out.println("RESUMO DE ATIVIDADES POR SEMANA");
    // Ordena as semanas
    Collections.sort(semanas);

    // Mostra as atividades por semana
    for (Integer semana : semanas) {
      // Cria variáveis para armazenar os totais de cada categoria
      double totalEnergia = 0;
      double totalBemEstar = 0;

      System.out.println("\n\nSemana " + semana);
      // Mostra as atividades da semana
      try {
        for (Atividade a : dao.getAllAtividades()) {
          if (a.getSemana() == semana) {
            totalEnergia += a.getGastoDeEnergia();
            totalBemEstar += a.getBemEstar();
            System.out.println(a.mostraAtividade());
          }
        }
      } catch (Exception e) {
        System.err.println("Erro ao mostrar atividades da semana: " + e.getMessage());
        Helpers.input("\n\nPressione ENTER para continuar...");
        return;
      }

      // Mostra os totais da semana
      System.out.println(String.format("\nTotal de gasto de energia: %.2f", totalEnergia));
      System.out.println(String.format("Total de bem-estar: %.2f", totalBemEstar));
    }
  }

  // Método para mostrar o resumo por mês
  public void resumoMes() {
    Helpers.clear();
    // Cria um ArrayList para armazenar os meses
    List<String> meses = new ArrayList<String>();

    // Adiciona os meses ao ArrayList
    try {
      for (Atividade a : dao.getAllAtividades()) {
        if (!meses.contains(a.getMes())) {
          meses.add(a.getMes());
        }
      }
    } catch (Exception e) {
      System.err.println("Erro ao adicionar meses ao ArrayList: " + e.getMessage());
      Helpers.input("\n\nPressione ENTER para continuar...");
      return;
    }

    // Verifica se há atividades cadastradas
    if (meses.size() == 0) {
      System.out.println("Não há atividades cadastradas");
      return;
    }

    System.out.println("RESUMO DE ATIVIDADES POR MÊS");
    // Ordena os meses
    Collections.sort(meses);

    // Mostra as atividades por mês
    for (String mes : meses) {
      // Cria variáveis para armazenar os totais de cada categoria
      double totalEnergia = 0;
      double totalBemEstar = 0;

      System.out.println("\n\n" + mes);
      // Mostra as atividades do mês
      try {
        for (Atividade a : dao.getAllAtividades()) {
          if (a.getMes() == mes) {
            totalEnergia += a.getGastoDeEnergia();
            totalBemEstar += a.getBemEstar();
            System.out.println(a.mostraAtividade());
          }
        }
      } catch (Exception e) {
        System.err.println("Erro ao mostrar atividades do mês: " + e.getMessage());
        Helpers.input("\n\nPressione ENTER para continuar...");
        return;
      }

      // Mostra os totais do mês
      System.out.println(String.format("\nTotal de gasto de energia: %.2f", totalEnergia));
      System.out.println(String.format("Total de bem-estar: %.2f", totalBemEstar));
    }
  }

  // TOP 3 *********************************************************************
  // Método para mostrar o top 3 de atividades com maior gasto de energia
  public void top3Energia() {
    Helpers.clear();
    // Cria um HashMap para armazenar as atividades por gasto de energia
    HashMap<Double, ArrayList<Atividade>> gastos = new HashMap<Double, ArrayList<Atividade>>();

    // Adiciona as atividades ao HashMap
    try {
      for (Atividade a : dao.getAllAtividades()) {
        if (!gastos.containsKey(a.getGastoDeEnergia())) {
          gastos.put(a.getGastoDeEnergia(), new ArrayList<Atividade>(Arrays.asList(a)));
        } else {
          gastos.get(a.getGastoDeEnergia()).add(a);
        }
      }
    } catch (Exception e) {
      System.err.println("Erro ao adicionar atividades ao HashMap: " + e.getMessage());
      Helpers.input("\n\nPressione ENTER para continuar...");
      return;
    }

    // Verifica se há atividades cadastradas
    if (gastos.size() == 0) {
      System.out.println("Não há atividades cadastradas");
      Helpers.input("\n\nPressione ENTER para continuar...");
      return;
    }

    System.out.println("TOP 3 DE ATIVIDADES COM MAIOR GASTO DE ENERGIA");
    // Ordena as chaves do HashMap
    List<Double> gastosOrdenados = new ArrayList<Double>(gastos.keySet());
    Collections.sort(gastosOrdenados);
    Collections.reverse(gastosOrdenados);

    // Mostra as atividades com maior gasto de energia
    for (int i = 0; i < 3; i++) {
      // Pega as atividades com maior gasto de energia
      List<Atividade> atividadesComMaiorGasto = gastos.get(gastosOrdenados.get(i));

      switch (i) {
        case 0:
          System.out.println("\n\n1º Lugar");
          break;
        case 1:
          System.out.println("\n\n2º Lugar");
          break;
        case 2:
          System.out.println("\n\n3º Lugar");
          break;
      }

      // Mostra as atividades com maior gasto de energia
      for (Atividade a : atividadesComMaiorGasto) {
        System.out.println(a.mostraAtividade());
      }
    }

    Helpers.input("\n\nPressione ENTER para continuar...");
  }
}
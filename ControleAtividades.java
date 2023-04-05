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

public class ControleAtividades {
  List<Atividade> atividades = new ArrayList<Atividade>();

  // Método para mostrar o Menu Principal
  public int mostraMenuPrincipal() {
    Helpers.clear(); // Limpa a tela
    // Mostra o menu
    System.out.println("DIÁRIO DE ATIVIDADES\n----------------------------------------------");
    System.out.println(
        "1- Cadastrar Atividade\n2- Pesquisar Atividade\n3- Atualizar Atividade\n4- Remover Atividade\n5- Listar Atividades\n6- Apresentar Resumos\n7- Top 3 Energia\n8- Testar Programa\n9- Sair\n");

    // Retorna a opção escolhida
    while (true) {
      try {
        int escolha = Integer.parseInt(Helpers.input("Insira o número correspondente à sua escolha: "));
        // Verifica se a opção escolhida é válida
        if (escolha < 1 || escolha > 9) {
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
    // Mostra o menu de cadastro e retorna a opção escolhida
    int escolha = mostraMenuCadastro();
    // Verifica a opção escolhida e chama o método de cadastro correspondente
    try {
      switch (escolha) {
        case 1:
          cadastraAvFisica();
          break;
        case 2:
          cadastraAvLazer();
          break;
        case 3:
          cadastraAvTrabalho();
          break;
      }
    } catch (Exception e) {
      System.err.println("Erro ao cadastrar atividade.\nMensagem de erro: " + e.getMessage());
      Helpers.input("Pressione ENTER para continuar...");
      return;
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
    System.out.println("1- Atividade Física\n2- Atividade de Lazer\n3- Atividade de Trabalho\n");

    // Retorna a opção escolhida
    while (true) {
      try {
        int escolha = Integer.parseInt(Helpers.input("Insira o número correspondente à sua escolha: "));
        // Verifica se a opção escolhida é válida
        if (escolha < 1 || escolha > 3) {
          throw new Exception();
        }
        return escolha;
      } catch (Exception e) {
        System.err.println("\nInsira um valor válido");
      }
    }
  }

  // Método para cadastrar uma atividade física
  public void cadastraAvFisica() {
    Helpers.clear();
    System.out.println("CADASTRO DE ATIVIDADE FÍSICA\n----------------------------------------------");

    // Pega os dados da atividade
    String descricao = Helpers.input("\nInsira uma descrição da atividade: ");
    String duracao = Helpers.input("\nInsira a duração da atividade (em minutos): ");
    String intensidade = Helpers.input(
        "\nInsira a intensidade da atividade \nFraco (2)\nIntenso (3)\nVigoroso (4)\n\nInsira o número correspondente: ");
    String satisfacao = Helpers.input(
        "\nInsira a satisfação com a atividade \nAgradável (1)\nDesagradável (-1)\n\nInsira o número correspondente: ");
    String data = Helpers.input("\nInsira a data da atividade (dd/mm/aaaa): ");

    // Cria a atividade
    AvFisica avFisica = new AvFisica(intensidade, duracao, satisfacao, descricao, data);
    // Adiciona a atividade ao ArrayList de atividades
    atividades.add(avFisica);
  }

  // Método para cadastrar uma atividade de lazer
  public void cadastraAvLazer() {
    Helpers.clear();
    System.out.println("CADASTRO DE ATIVIDADE DE LAZER\n----------------------------------------------");

    // Pega os dados da atividade
    String descricao = Helpers.input("\nInsira uma descrição da atividade: ");
    String duracao = Helpers.input("\nInsira a duração da atividade (em minutos): ");
    String satisfacao = Helpers.input(
        "\nInsira a satisfação com a atividade \nAgradável (1)\nDesagradável (-1)\n\nInsira o número correspondente: ");
    String data = Helpers.input("\nInsira a data da atividade (dd/mm/aaaa): ");

    // Cria a atividade
    AvLazer avLazer = new AvLazer(duracao, satisfacao, descricao, data);
    // Adiciona a atividade ao ArrayList de atividades
    atividades.add(avLazer);
  }

  // Método para cadastrar uma atividade de trabalho
  public void cadastraAvTrabalho() {
    Helpers.clear();
    System.out.println("CADASTRO DE ATIVIDADE DE TRABALHO\n----------------------------------------------");

    // Pega os dados da atividade
    String descricao = Helpers.input("\nInsira uma descrição da atividade: ");
    String duracao = Helpers.input("\nInsira a duração da atividade (em minutos): ");
    String satisfacao = Helpers.input(
        "\nInsira a satisfação com a atividade \nAgradável (1)\nDesagradável (-1)\n\nInsira o número correspondente: ");
    String data = Helpers.input("\nInsira a data da atividade (dd/mm/aaaa): ");
    String dificuldade = Helpers.input(
        "\nInsira a dificuldade da atividade \nFácil (1)\nMédia (2)\nDifícil (3)\n\nInsira o número correspondente: ");

    // Cria a atividade
    AvTrabalho avTrabalho = new AvTrabalho(dificuldade, duracao, satisfacao, descricao, data);
    // Adiciona a atividade ao ArrayList de atividades
    atividades.add(avTrabalho);
  }

  // PESQUISA ******************************************************************
  // Método de controle para pesquisar atividades cadastradas
  public void pesquisar() {
    int escolha = mostraMenuPesquisa();
    // Verifica a opção escolhida e chama o método de pesquisa correspondente
    try {
      switch (escolha) {
        case 1:
          pesquisarPorData();
          break;
        case 2:
          pesquisarPorTipo();
          break;
        case 3:
          pesquisarPorDescricao();
          break;
      }
    } catch (Exception e) {
      System.err.println("Erro ao pesquisar atividade.\nMensagem de erro: " + e.getMessage());
      Helpers.input("Pressione ENTER para continuar...");
      return;
    }
  }

  // Método para mostrar o menu de pesquisa
  public int mostraMenuPesquisa() {
    Helpers.clear();
    System.out.println("O que deseja pesquisar?");
    System.out.println("1 - Atividades por data");
    System.out.println("2 - Atividades por tipo");
    System.out.println("3 - Atividades por descrição");
    int opcao;
    while (true) {
      try {
        opcao = Integer.parseInt(Helpers.input("\nInsira o número correspondente à sua escolha: "));
        if (opcao < 1 || opcao > 3) {
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
  public void pesquisarPorData() {
    Helpers.clear();
    System.out.println("PESQUISA POR DATA\n");

    String data = Helpers.input("Insira a data que deseja pesquisar (dd/mm/aaaa): ");

    // Verifica se a data inserida é válida
    while (!Helpers.validaData(data)) {
      System.err.println("\n\nInsira uma data válida");
      data = Helpers.input("Insira a data que deseja pesquisar (dd/mm/aaaa): ");
    }

    // Cria uma lista com as atividades que possuem a data inserida
    List<Atividade> atividadesMostrar = new ArrayList<Atividade>();

    // Adiciona as atividades que possuem a data inserida na lista
    for (Atividade atividade : atividades) {
      if (atividade.getData().equals(data)) {
        atividadesMostrar.add(atividade);
      }
    }

    // Verifica se a lista está vazia
    if (atividadesMostrar.isEmpty()) {
      Helpers.clear();
      System.out.println("Não foram encontradas atividades para a data inserida.");
    } else { // Mostra as atividades encontradas
      Helpers.clear();
      System.out.println("Atividades na data " + data + ":");
      for (Atividade a : atividadesMostrar) {
        System.out.println("\n\nID: " + atividades.indexOf(a) + a.mostraAtividade());
      }
    }
    Helpers.input("\n\nPressione ENTER para continuar...");
  }

  // Método para pesquisar atividades por tipo
  public void pesquisarPorTipo() {
    Helpers.clear();
    System.out.println("PESQUISA POR TIPO\n");
    int tipo;

    while (true) {
      try {
        String entrada = Helpers.input(
            "Insira o tipo que deseja pesquisar:\n1 - Atividade Física\n2 - Atividade de Lazer\n3 - Atividade de Trabalho\n\nInsira o número correspondente:	");
        tipo = Integer.parseInt(entrada);
        // Verifica se o tipo inserido é válido
        if (tipo < 1 || tipo > 3) {
          throw new Exception();
        }
        break;
      } catch (Exception e) {
        System.err.println("\n\nInsira uma opção válida");
      }
    }

    // Cria uma lista com as atividades que possuem o tipo inserido
    List<Atividade> atividadesMostrar = new ArrayList<Atividade>();

    // Adiciona as atividades que possuem o tipo inserido na lista
    for (Atividade atividade : atividades) {
      if (atividade.getTipoInt() == tipo) {
        atividadesMostrar.add(atividade);
      }
    }

    // Verifica se a lista está vazia
    if (atividadesMostrar.size() == 0) {
      Helpers.clear();
      System.out.println("Não foram encontradas atividades para o tipo inserido.");
    } else { // Se não estiver vazia, mostra as atividades
      Helpers.clear();
      System.out.println("Atividades do tipo " + atividadesMostrar.get(0).getTipo() + ":");
      for (Atividade a : atividadesMostrar) {
        System.out.println("\n\nID: " + atividades.indexOf(a) + a.mostraAtividade());
      }
    }
    Helpers.input("\n\nPressione ENTER para continuar...");
  }

  // Método para pesquisar atividades por descrição
  public void pesquisarPorDescricao() {
    Helpers.clear();
    System.out.println("PESQUISA POR DESCRIÇÃO\n");
    String descricao = Helpers.input("Insira a descrição que deseja pesquisar: ");

    // Verifica se a descrição inserida é válida
    while (descricao.equals("")) {
      System.err.println("\n\nInsira uma descrição válida");
      descricao = Helpers.input("Insira a descrição que deseja pesquisar: ");
    }

    // Cria uma lista com as atividades que possuem a descrição inserida
    List<Atividade> atividadesMostrar = new ArrayList<Atividade>();

    // Adiciona as atividades que possuem a descrição inserida na lista
    for (Atividade atividade : atividades) {
      if (atividade.getDescricao().toUpperCase().equals(descricao.toUpperCase())) {
        atividadesMostrar.add(atividade);
      }
    }

    // Verifica se a lista está vazia
    if (atividadesMostrar.size() == 0) {
      Helpers.clear();
      System.out.println("Não foram encontradas atividades para a descrição inserida.");
    } else { // Se não estiver vazia, mostra as atividades
      Helpers.clear();
      System.out.println("Atividades com a descrição " + descricao + ":");
      for (Atividade a : atividadesMostrar) {
        System.out.println("\n\nID: " + atividades.indexOf(a) + a.mostraAtividade());
      }
    }

    Helpers.input("\n\nPressione ENTER para continuar...");
  }

  // ATUALIZAR *****************************************************************
  // Método para atualizar atividade existente
  public void atualizar() {
    Helpers.clear();
    // Mostra as atividades cadastradas
    if (!listarAtividades()) {
      Helpers.input("\n\nPressione ENTER para continuar...");
      return;
    }

    int id;
    // Verifica se o ID inserido é válido
    while (true) {
      try {
        id = Integer.parseInt(Helpers.input("Insira o ID da atividade que deseja atualizar: "));
        if (id >= atividades.size() || id < 0) {
          throw new Exception();
        }
        break;
      } catch (Exception e) {
        System.err.println("\n\nInsira um ID válido");
      }
    }
    // Chama o método atualizaAtividade da atividade correspondente
    Atividade a = atividades.get(id);
    a.atualizaAtividade();
  }

  // REMOVER *******************************************************************
  // Método para remover atividade existente
  public void remover() {
    Helpers.clear();
    // Mostra as atividades cadastradas
    if (!listarAtividades()) {
      Helpers.input("\n\nPressione ENTER para continuar...");
      return;
    }

    int id;
    // Verifica se o ID inserido é válido
    while (true) {
      try {
        id = Integer.parseInt(Helpers.input("Insira o ID da atividade que deseja atualizar: "));
        if (id >= atividades.size() || id < 0) {
          throw new Exception();
        }
        break;
      } catch (Exception e) {
        System.err.println("\n\nInsira um ID válido");
      }
    }
    // Remove a atividade do array de atividades
    atividades.remove(id);
  }

  // LISTAR ********************************************************************
  // Método para listar as atividades cadastradas
  public boolean listarAtividades() {
    Helpers.clear();

    // Verifica se há atividades cadastradas
    if (atividades.size() <= 0) {
      System.out.println("Não há atividades cadastradas");
      return false;
    }

    System.out.println("ATIVIDADES CADASTRADAS");

    // Mostra as atividades cadastradas
    for (Atividade a : atividades) {
      System.out.println("\n\nID: " + atividades.indexOf(a) + a.mostraAtividade());
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
    }

    Helpers.input("\n\nPressione ENTER para continuar...");
  }

  // Método para mostrar o menu de resumo
  public int mostraMenuResumo() {
    Helpers.clear();
    // Mostra o menu
    System.out.println("RESUMO DE ATIVIDADES\n----------------------------------------------");
    System.out.println("1- Resumo do Dia\n2- Resumo da Semana\n3- Resumo do Mês\n");

    // Retorna a opção escolhida
    while (true) {
      try {
        int escolha = Integer.parseInt(Helpers.input("Insira o número correspondente à sua escolha: "));
        // Verifica se a opção escolhida é válida
        if (escolha < 1 || escolha > 3) {
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
    for (Atividade a : atividades) {
      if (!dias.containsKey(a.getDia())) {
        dias.put(a.getDia(), new ArrayList<Atividade>(Arrays.asList(a)));
      } else {
        dias.get(a.getDia()).add(a);
      }
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
        System.out.println(a.mostraAtividade());
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
    for (Atividade a : atividades) {
      if (!semanas.contains(a.getSemana())) {
        semanas.add(a.getSemana());
      }
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
      for (Atividade a : atividades) {
        if (a.getSemana() == semana) {
          totalEnergia += a.getGastoDeEnergia();
          totalBemEstar += a.getBemEstar();
          System.out.println(a.mostraAtividade());
        }
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
    for (Atividade a : atividades) {
      if (!meses.contains(a.getMes())) {
        meses.add(a.getMes());
      }
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
      for (Atividade a : atividades) {
        if (a.getMes() == mes) {
          totalEnergia += a.getGastoDeEnergia();
          totalBemEstar += a.getBemEstar();
          System.out.println(a.mostraAtividade());
        }
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
    for (Atividade a : atividades) {
      if (!gastos.containsKey(a.getGastoDeEnergia())) {
        gastos.put(a.getGastoDeEnergia(), new ArrayList<Atividade>(Arrays.asList(a)));
      } else {
        gastos.get(a.getGastoDeEnergia()).add(a);
      }
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

  // TESTAR ********************************************************************
  // Método para adicionar atividades de teste
  public void testar() {
    Helpers.clear();

    // Adiciona 9 atividades de teste
    try {
      atividades.add(new AvFisica("2", "30", "1", "Corrida", "10/10/2023"));
      atividades.add(new AvFisica("3", "30", "-1", "Caminhada", "10/10/2023"));
      atividades.add(new AvFisica("4", "30", "1", "Paintball", "12/10/2023"));
      atividades.add(new AvLazer("30", "-1", "Praia", "11/07/2023"));
      atividades.add(new AvLazer("30", "1", "Cinema", "11/07/2023"));
      atividades.add(new AvLazer("30", "-1", "Parque", "13/07/2023"));
      atividades.add(new AvTrabalho("1", "30", "1", "Projeto", "07/01/2023"));
      atividades.add(new AvTrabalho("2", "30", "-1", "Estágio", "08/01/2023"));
      atividades.add(new AvTrabalho("3", "30", "1", "Trabalho", "07/01/2023"));
    } catch (Exception e) {
      System.err.println("Erro ao adicionar atividades de teste");
      return;
    }

    // Retorna true se as atividades foram adicionadas com sucesso
    System.out.println("Atividades de teste cadastradas com sucesso!");
    Helpers.input("\n\nPressione ENTER para continuar...");
  }
}
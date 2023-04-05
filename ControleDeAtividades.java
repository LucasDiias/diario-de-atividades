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

  // Método para mostrar o Menu Principal
  public int mostraMenuPrincipal() {
    Helpers.clear(); // Limpa a tela
    // Mostra o menu
    System.out.println("DIÁRIO DE ATIVIDADES\n----------------------------------------------");
    System.out.println(
        "1- Cadastrar Atividade\n2- Pesquisar Atividade\n3- Atualizar Atividade\n4- Remover Atividade\n5- Listar Atividades\n6- Apresentar Resumos\n7- Testar Programa\n8- Sair\n");

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
        Helpers.clear();
        System.err.println("\nInsira um valor válido");
      }
    }
  }

  // CADASTRO ******************************************************************
  // Método de controle para cadastrar nova atividade
  public boolean cadastro() {
    // Mostra o menu de cadastro e retorna a opção escolhida
    int escolha = mostraMenuCadastro();
    boolean retorno = false;
    // Verifica a opção escolhida e chama o método de cadastro correspondente
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

    // Retorna true se a atividade foi cadastrada com sucesso
    System.out.println("\n\nAtividade cadastrada com sucesso!");
    Helpers.input("Pressione ENTER para continuar...");

    return retorno;
  }

  // Método para mostrar o menu de cadastro
  public int mostraMenuCadastro() {
    Helpers.clear();
    // Cria um ArrayList com as opções do menu
    List<Integer> opcoes = new ArrayList<Integer>(Arrays.asList(1, 2, 3));

    // Mostra o menu
    System.out.println("CADASTRO DE ATIVIDADE\n----------------------------------------------");
    System.out.println("1- Atividade Física\n2- Atividade de Lazer\n3- Atividade de Trabalho\n");

    // Retorna a opção escolhida
    while (true) {
      try {
        int escolha = Integer.parseInt(Helpers.input("Insira o número correspondente à sua escolha: "));
        // Verifica se a opção escolhida é válida
        if (!opcoes.contains(escolha)) {
          throw new Exception();
        }
        return escolha;
      } catch (Exception e) {
        Helpers.clear();
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
  public boolean pesquisar() {
    int escolha = mostraMenuPesquisa();
    // Verifica a opção escolhida e chama o método de pesquisa correspondente
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
    return true;
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
        Helpers.clear();
        System.err.println("Insira uma opção válida");
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
      Helpers.clear();
      System.err.println("Insira uma data válida");
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
        System.out.println("\n\nID: " + atividadesMostrar.indexOf(a) + a.mostraAtividade());
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
        Helpers.clear();
        System.err.println("Insira uma opção válida");
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
        System.out.println("\n\nID: " + atividadesMostrar.indexOf(a) + a.mostraAtividade());
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
      Helpers.clear();
      System.err.println("Insira uma descrição válida");
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
        System.out.println("\n\nID: " + atividadesMostrar.indexOf(a) + a.mostraAtividade());
      }
    }

    Helpers.input("\n\nPressione ENTER para continuar...");
  }

  // ATUALIZAR *****************************************************************
  // Método para atualizar atividade existente
  public boolean atualizar() {
    Helpers.clear();
    // Mostra as atividades cadastradas
    listarAtividades();
    int id;
    // Verifica se o ID inserido é válido
    while (true) {
      id = Integer.parseInt(Helpers.input("Insira o ID da atividade que deseja atualizar: "));
      if (id < atividades.size()) {
        break;
      } else {
        Helpers.clear();
        System.err.println("ID inválido");
      }
    }
    // Chama o método atualizaAtividade da atividade correspondente
    Atividade a = atividades.get(id);
    a.atualizaAtividade();
    // Retorna true se a atividade foi atualizada com sucesso
    return true;
  }

  // REMOVER *******************************************************************
  // Método para remover atividade existente
  public boolean remover() {
    Helpers.clear();
    // Mostra as atividades cadastradas
    listarAtividades();
    int id;
    // Verifica se o ID inserido é válido
    while (true) {
      id = Integer.parseInt(Helpers.input("Insira o ID da atividade que deseja remover: "));
      if (id < atividades.size()) {
        break;
      } else {
        Helpers.clear();
        System.err.println("ID inválido");
      }
    }
    // Remove a atividade do array de atividades
    atividades.remove(id);
    // Retorna true se a atividade foi removida com sucesso
    return true;
  }

  // LISTAR ********************************************************************
  // Método para listar as atividades cadastradas
  public boolean listarAtividades() {
    Helpers.clear();

    // Verifica se há atividades cadastradas
    if (atividades.size() <= 0) {
      System.out.println("Não há atividades cadastradas");
      return true;
    }

    System.out.println("ATIVIDADES CADASTRADAS");

    // Mostra as atividades cadastradas
    for (Atividade a : atividades) {
      System.out.println("\n\nID: " + atividades.indexOf(a) + a.mostraAtividade());
    }

    Helpers.input("\n\nPressione ENTER para continuar...");
    return true;
  }

  // RESUMO ********************************************************************
  // Método para apresentar o resumo de atividades cadastradas
  public boolean resumo() {
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
    return true; // Temporário
  }

  // Método para mostrar o menu de resumo
  public int mostraMenuResumo() {
    Helpers.clear();
    // Cria um ArrayList com as opções do menu
    List<Integer> opcoes = new ArrayList<Integer>(Arrays.asList(1, 2, 3));

    // Mostra o menu
    System.out.println("RESUMO DE ATIVIDADES\n----------------------------------------------");
    System.out.println("1- Resumo do Dia\n2- Resumo da Semana\n3- Resumo do Mês\n");

    // Retorna a opção escolhida
    while (true) {
      try {
        int escolha = Integer.parseInt(Helpers.input("Insira o número correspondente à sua escolha: "));
        // Verifica se a opção escolhida é válida
        if (!opcoes.contains(escolha)) {
          throw new Exception();
        }
        return escolha;
      } catch (Exception e) {
        Helpers.clear();
        System.err.println("\nInsira um valor válido");
      }
    }
  }

  // Método para mostrar o resumo por dia
  public void resumoDia() {
    Helpers.clear();
    // Cria um HashMap para armazenar as atividades por dia
    HashMap<Integer, ArrayList<Atividade>> dias = new HashMap<Integer, ArrayList<Atividade>>();

    System.out.println("RESUMO DE ATIVIDADES POR DIA");

    // Adiciona as atividades ao HashMap
    for (Atividade a : atividades) {
      if (!dias.containsKey(a.getDia())) {
        dias.put(a.getDia(), new ArrayList<Atividade>(Arrays.asList(a)));
      } else {
        dias.get(a.getDia()).add(a);
      }
    }

    // Ordena as chaves do HashMap
    List<Integer> diasOrdenados = new ArrayList<Integer>(dias.keySet());
    Collections.sort(diasOrdenados);

    // Mostra as atividades por dia
    for (Integer dia : diasOrdenados) {
      // Pega as atividades do dia
      List<Atividade> atividadesDoDia = dias.get(dia);
      System.out.println("\n\nDia " + atividadesDoDia.get(0).getData());
      // Mostra as atividades do dia
      for (Atividade a : atividadesDoDia) {
        System.out.println(a.mostraAtividade());
      }
    }
  }

  // Método para mostrar o resumo por semana
  public void resumoSemana() {
    Helpers.clear();
    // Cria um ArrayList para armazenar as semanas
    List<Integer> semanas = new ArrayList<Integer>();

    System.out.println("RESUMO DE ATIVIDADES POR SEMANA");

    // Adiciona as semanas ao ArrayList
    for (Atividade a : atividades) {
      if (!semanas.contains(a.getSemana())) {
        semanas.add(a.getSemana());
      }
    }
    // Ordena as semanas
    Collections.sort(semanas);

    // Mostra as atividades por semana
    for (Integer semana : semanas) {
      System.out.println("\n\nSemana " + semana);
      // Mostra as atividades da semana
      for (Atividade a : atividades) {
        if (a.getSemana() == semana) {
          System.out.println(a.mostraAtividade());
        }
      }
    }
  }

  // Método para mostrar o resumo por mês
  public void resumoMes() {
    Helpers.clear();
    // Cria um ArrayList para armazenar os meses
    List<String> meses = new ArrayList<String>();

    System.out.println("RESUMO DE ATIVIDADES POR MÊS");

    // Adiciona os meses ao ArrayList
    for (Atividade a : atividades) {
      if (!meses.contains(a.getMes())) {
        meses.add(a.getMes());
      }
    }

    // Ordena os meses
    Collections.sort(meses);

    // Mostra as atividades por mês
    for (String mes : meses) {
      System.out.println("\n\n" + mes);
      // Mostra as atividades do mês
      for (Atividade a : atividades) {
        if (a.getMes() == mes) {
          System.out.println(a.mostraAtividade());
        }
      }
    }
  }

  // TESTAR ********************************************************************
  // Método para adicionar atividades de teste
  public boolean testar() {
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
      return false;
    }

    // Retorna true se as atividades foram adicionadas com sucesso
    System.out.println("Atividades de teste cadastradas com sucesso!");
    Helpers.input("\n\nPressione ENTER para continuar...");
    return true;
  }
}
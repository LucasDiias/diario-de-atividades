package atividades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dao.AtividadeDAO;
import lib.Helpers;

public abstract class Atividade {
  // Declaração de atributos comuns à todas as atividades
  protected int id;
  protected int duracao;
  protected int satisfacao;
  protected String descricao;
  protected LocalDate data;
  protected int tipo;

  protected AtividadeDAO dao = new AtividadeDAO();

  // GETTERS *******************************************************************
  public String getData() {
    // Formata a data para o padrão dd/mm/aaaa e retorna como String
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return this.data.format(formato);
  }

  // Retorna a data como LocalDate
  public LocalDate getDataSQL() {
    return this.data;
  }

  // Retorna a duração da atividade
  public int getDuracao() {
    return this.duracao;
  }

  // Retorna a satisfação da atividade
  public int getSatisfacao() {
    return this.satisfacao;
  }

  // Retorna a descrição da atividade
  public String getDescricao() {
    return this.descricao;
  }

  // Retorna o dia no ano da atividade
  public int getDia() {
    return this.data.getDayOfYear();
  }

  // Retorna o mês por extenso da atividade
  public String getMes() {
    int mes = this.data.getMonthValue();

    switch (mes) {
      case 1:
        return "Janeiro";
      case 2:
        return "Fevereiro";
      case 3:
        return "Março";
      case 4:
        return "Abril";
      case 5:
        return "Maio";
      case 6:
        return "Junho";
      case 7:
        return "Julho";
      case 8:
        return "Agosto";
      case 9:
        return "Setembro";
      case 10:
        return "Outubro";
      case 11:
        return "Novembro";
      case 12:
        return "Dezembro";
    }
    // Retorna uma string para o Java não reclamar do escopo do switch
    return "default";
  }

  // Retorna o ano da atividade
  public int getAno() {
    return this.data.getYear();
  }

  // Retorna a semana do ano da atividade
  public int getSemana() {
    return this.data.getDayOfYear() / 7;
  }

  // Retorna o tipo da atividade
  public String getTipo() {
    // Switch para retornar o tipo da atividade
    switch (tipo) {
      case 1:
        return "Atividade Física";
      case 2:
        return "Atividade de Lazer";
      case 3:
        return "Atividade de Trabalho";
    }

    // Retorna uma string para o Java não reclamar do escopo do switch
    return "default";
  }

  // Retorna o tipo da atividade em inteiro
  public int getTipoInt() {
    return this.tipo;
  }

  public int getId() {
    return this.id;
  }

  // SETTERS *******************************************************************
  // Lógica de tratamento do set de Data
  public void setData(String data) {
    // Loop para verificar se a data está no formato correto
    while (true) {
      try {
        // Verifica se a data está no formato correto e se é uma data válida
        if (!Helpers.validaData(data)) {
          throw new Exception();
        }

        // Transforma a data em um objeto LocalDate
        this.data = Helpers.stringToLocalDate(data);
        break;
      } catch (Exception e) {
        System.err.println("\n\nInsira uma data válida");
        data = Helpers.input("Insira a data da atividade (dd/mm/aaaa): ");
      }
    }
  }

  // Lógica de tratamento do set de Duração
  public void setDuracao(String d) {
    // Loop para verificar se a duração está no formato correto
    while (true) {
      try {
        int duracao = Integer.parseInt(d);
        // Verifica se a duração é maior que 0
        if (duracao <= 0) {
          throw new Exception();
        }
        this.duracao = duracao;
        break;
      } catch (Exception e) {
        System.err.println("\n\nInsira uma duração válida");
        d = Helpers.input("Insira a duração da atividade (em minutos): ");
      }
    }
  }

  // Lógica de tratamento do set de Satisfação
  public void setSatisfacao(String satisfacao) {
    // Loop para verificar se a satisfação está no formato correto
    while (true) {
      try {
        int s = Integer.parseInt(satisfacao);
        // Verifica se a satisfação é -1 ou 1
        if (s == 1 || s == -1) {
          this.satisfacao = s;
          break;
        } else {
          throw new Exception();
        }
      } catch (Exception e) {
        System.err.println("\n\nInsira uma satisfação válida");
        satisfacao = Helpers.input(
            "Insira a satisfação da atividade \nInsatisfeito (-1)\nSatisfeito (1)\n\nInsira o número correspondente: ");
      }
    }
  }

  // Lógica de tratamento do set de Descrição
  public void setDescricao(String descricao) {
    // Loop para verificar se a descrição está no formato correto
    while (true) {
      try {
        // Verifica se a descrição é maior que 0
        if (descricao.length() == 0 || !descricao.matches("^[A-Za-z]+$")) {
          throw new Exception();
        }
        this.descricao = descricao;
        break;
      } catch (Exception e) {
        System.err.println("\n\nInsira uma descrição válida");
        descricao = Helpers.input("Insira uma descrição da atividade: ");
      }
    }
  }

  // Define o tipo da atividade
  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public void setId(int id) {
    this.id = id;
  }

  // MÉTODOS *******************************************************************

  // Método abstrato para o cálculo do gasto de energia
  public abstract double getGastoDeEnergia();

  // Método comum para mostrar as informações completas da atividade
  public String mostraAtividade() {
    return String.format(
        "\nID: %d\nDescrição: %s\nTipo: %s\nData: %s\nDuração: %d minutos\nSatisfação: %s\nGasto de energia: %.2f\nBem-estar: %.2f",
        getId(), getDescricao(), getTipo(), getData(), getDuracao(),
        getSatisfacao() == 1 ? "Satisfeito" : "Insatisfeito",
        getGastoDeEnergia(), getBemEstar());
  }

  // Método comum para atualizar as informações da atividade
  public void atualizaAtividade() {
    Helpers.clear();
    // Mostra as informações da atividade que será atualizada
    System.out.println(mostraAtividade());

    System.out.println("\nO que deseja atualizar?");
    System.out.println("1 - Descrição");
    System.out.println("2 - Data");
    System.out.println("3 - Duração");
    System.out.println("4 - Satisfação");

    int opcao;
    // Loop para verificar se a opção está no formato correto e se é válida
    while (true) {
      try {
        opcao = Integer.parseInt(Helpers.input("\nOpção: "));
        if (opcao < 1 || opcao > 4) {
          throw new Exception();
        }
        break;
      } catch (Exception e) {
        System.err.println("Insira uma opção válida");
      }
    }
    // Switch para atualizar a informação escolhida
    switch (opcao) {
      case 1:
        setDescricao(Helpers.input("Insira uma nova descrição: "));
        dao.update(getId(), 3, getDescricao());
        break;
      case 2:
        setData(Helpers.input("Insira uma nova data (dd/mm/aaaa): "));
        dao.update(getId(), 4, getData());
        break;
      case 3:
        setDuracao(Helpers.input("Insira uma nova duração (em minutos): "));
        dao.update(getId(), 1, getDuracao() + "");
        break;
      case 4:
        setSatisfacao(Helpers.input("Insira uma nova satisfação (1 ou -1): "));
        dao.update(getId(), 2, getSatisfacao() + "");
        break;
    }
  }

  // Retorna o gasto de energia da atividade
  public double getBemEstar() {
    return getGastoDeEnergia() * satisfacao / 360;
  }
}
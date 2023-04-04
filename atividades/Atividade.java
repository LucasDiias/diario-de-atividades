package atividades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lib.Helpers;

public abstract class Atividade {
  // Declaração de atributos comuns à todas as atividades
  protected int duracao;
  protected int satisfacao;
  protected String descricao;
  protected LocalDate data;
  protected int tipo;

  // Instância da classe Helpers para uso dos métodos
  Helpers helper = new Helpers();

  // GETTERS *******************************************************************
  public String getData() {
    // Formata a data para o padrão dd/mm/aaaa e retorna como String
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return this.data.format(formato);
  }

  public int getDuracao() {
    return this.duracao;
  }

  public int getSatisfacao() {
    return this.satisfacao;
  }

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

  // SETTERS *******************************************************************
  // Lógica de tratamento do set de Data
  public void setData(String data) {
    // Loop para verificar se a data está no formato correto
    while (true) {
      try {
        // Verifica se a data está no formato correto e se é uma data válida
        if (!helper.validaData(data)) {
          throw new Exception();
        }

        // Separa a data em dia, mês e ano
        String[] dataSplit = data.split("/");
        int dia = Integer.parseInt(dataSplit[0]);
        int mes = Integer.parseInt(dataSplit[1]);
        int ano = Integer.parseInt(dataSplit[2]);

        // Transforma a data em um objeto LocalDate
        this.data = LocalDate.of(ano, mes, dia);
        break;
      } catch (Exception e) {
        helper.clear();
        System.err.println("Insira uma data válida");
        data = helper.input("Insira a data da atividade (dd/mm/aaaa): ");
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
        helper.clear();
        System.err.println("Insira uma duração válida");
        d = helper.input("Insira a duração da atividade (em minutos): ");
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
        helper.clear();
        System.err.println("Insira uma satisfação válida");
        satisfacao = helper.input(
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
        if (descricao.length() == 0) {
          throw new Exception();
        }
        this.descricao = descricao;
        break;
      } catch (Exception e) {
        helper.clear();
        System.err.println("Insira uma descrição válida");
        descricao = helper.input("Insira uma descrição da atividade: ");
      }
    }
  }

  // Define o tipo da atividade
  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  // MÉTODOS *******************************************************************

  // Método abstrato para o cálculo do gasto de energia
  public abstract double gastoDeEnergia();

  // Método comum para mostrar as informações completas da atividade
  public String mostraAtividade() {
    return String.format(
        "\nDescrição: %s\nTipo: %s\nData: %s\nDuração: %d minutos\nSatisfação: %s\nGasto de energia: %.2f",
        getDescricao(), getTipo(), getData(), getDuracao(), getSatisfacao() == 1 ? "Satisfeito" : "Insatisfeito",
        gastoDeEnergia());
  }

  // Método comum para atualizar as informações da atividade
  public void atualizaAtividade() {
    helper.clear();
    // Mostra as informações da atividade que será atualizada
    mostraAtividade();

    System.out.println("O que deseja atualizar?");
    System.out.println("1 - Descrição");
    System.out.println("2 - Data");
    System.out.println("3 - Duração");
    System.out.println("4 - Satisfação");
    System.out.println("5 - Voltar");
    int opcao;
    // Loop para verificar se a opção está no formato correto e se é válida
    while (true) {
      try {
        opcao = Integer.parseInt(helper.input("\nOpção: "));
        if (opcao < 1 || opcao > 5) {
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
        setDescricao(helper.input("Insira uma nova descrição: "));
        break;
      case 2:
        setData(helper.input("Insira uma nova data (dd/mm/aaaa): "));
        break;
      case 3:
        setDuracao(helper.input("Insira uma nova duração (em minutos): "));
        break;
      case 4:
        setSatisfacao(helper.input("Insira uma nova satisfação: "));
        break;
      case 5:
        break;
    }
    ;
  }

  // Retorna o gasto de energia da atividade
  public double bemEstar() {
    return gastoDeEnergia() * satisfacao / 360;
  }
}
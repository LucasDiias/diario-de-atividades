package atividades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lib.Helpers;

public abstract class Atividade {
  double duracao;
  int satisfacao;
  String descricao;
  LocalDate data;

  Helpers helper = new Helpers();

  //
  public void setData(String data) {
    while (true) {
      try {
        if (data.length() != 10) {
          throw new Exception();
        }
        String[] dataSplit = data.split("/");
        int dia = Integer.parseInt(dataSplit[0]);
        int mes = Integer.parseInt(dataSplit[1]);
        int ano = Integer.parseInt(dataSplit[2]);
        this.data = LocalDate.of(ano, mes, dia);
        break;
      } catch (Exception e) {
        helper.clear();
        System.err.println("Insira uma data válida");
        data = helper.input("Insira a data da atividade (dd/mm/aaaa): ");
      }
    }
  }

  public String getData() {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return this.data.format(formato);
  }

  //
  public void setDuracao(String d) {
    while (true) {
      try {
        double duracao = Double.parseDouble(d);
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

  public double getDuracao() {
    return this.duracao;
  }

  //
  public void setSatisfacao(String satisfacao) {
    while (true) {
      try {
        int s = Integer.parseInt(satisfacao);
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

  public int getSatisfacao() {
    return this.satisfacao;
  }

  //
  public void setDescricao(String descricao) {
    while (true) {
      try {
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

  public String getDescricao() {
    return this.descricao;
  }

  public abstract double gastoDeEnergia();

  public String mostraAtividade() {
    return String.format("\nDescrição: %s\nData: %s\nDuração: %.2f minutos\nSatisfação: %s\nGasto de energia: %.2f",
        getDescricao(), getData(), getDuracao(), getSatisfacao() == 1 ? "Satisfeito" : "Insatisfeito",
        gastoDeEnergia());
  }

  //
  public void atualizaAtividade() {
    helper.clear();
    mostraAtividade();

    System.out.println("O que deseja atualizar?");
    System.out.println("1 - Descrição");
    System.out.println("2 - Data");
    System.out.println("3 - Duração");
    System.out.println("4 - Satisfação");
    System.out.println("5 - Voltar");
    int opcao;
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
      default:
        System.out.println("Opção inválida");
        atualizaAtividade();
    }
    ;
  }

  public int getDia() {
    return this.data.getDayOfYear();
  }

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
    return "default";
  }

  public int getAno() {
    return this.data.getYear();
  }

  public int getSemana() {
    return this.data.getDayOfYear() / 7;
  }

  public double bemEstar() {
    return gastoDeEnergia() * satisfacao / 360;
  }
}

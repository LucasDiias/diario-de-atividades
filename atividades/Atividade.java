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
        System.err.println("Insira uma data válida");
        data = helper.input("Insira a data da atividade (dd/mm/aaaa): ");
      }
    }
  }

  public String getData() {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return this.data.format(formato);
  }

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
        System.err.println("Insira uma duração válida");
        d = helper.input("Insira a duração da atividade (em minutos): ");
      }
    }
  }

  public double getDuracao() {
    return this.duracao;
  }

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
        System.err.println("Insira uma satisfação válida");
        satisfacao = helper.input(
            "Insira a satisfação da atividade \nInsatisfeito (-1)\nSatisfeito (1)\n\nInsira o número correspondente: ");
      }
    }
  }

  public int getSatisfacao() {
    return this.satisfacao;
  }

  public void setDescricao(String descricao) {
    while (true) {
      try {
        if (descricao.length() == 0) {
          throw new Exception();
        }
        this.descricao = descricao;
        break;
      } catch (Exception e) {
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

  public double bemEstar() {
    return gastoDeEnergia() * satisfacao / 360;
  }
}

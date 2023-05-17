package atividades;

import java.time.LocalDate;

import lib.Helpers;

public class AvFisica extends Atividade {
  private int intensidade;

  // Construtor da classe AvFisica, inicializa as propriedades
  public AvFisica(String intensidade, String duracao, String satisfacao, String descricao, String data) {
    try {
      setIntensidade(intensidade);
      setDuracao(duracao);
      setSatisfacao(satisfacao);
      setDescricao(descricao);
      setData(data);
      setTipo(1);
    } catch (Exception e) {
      System.err.println("Erro ao criar atividade física.");
    }
  }

  // Construtor de retorno do SQL
  public AvFisica(int id, int intensidade, int duracao, int satisfacao, String descricao, LocalDate data) {
    try {
      this.id = id;
      this.intensidade = intensidade;
      this.duracao = duracao;
      this.satisfacao = satisfacao;
      this.descricao = descricao;
      this.data = data;
      setTipo(1);
    } catch (Exception e) {
      System.err.println("Erro ao criar atividade física.");
    }
  }

  // GETTERS *******************************************************************
  public int getIntensidade() {
    return this.intensidade;
  }

  // SETTERS *******************************************************************
  // Lógica de tratamento do set de Intensidade
  public void setIntensidade(String intensidade) {
    // Loop para verificar se a intensidade está no formato correto
    while (true) {
      try {
        int i = Integer.parseInt(intensidade);
        // Verifica se a intensidade é 2, 3 ou 4
        if (i == 2 || i == 3 || i == 4) {
          this.intensidade = i;
          break;
        } else {
          throw new Exception();
        }
      } catch (Exception e) {
        System.err.println("Insira uma intensidade válida");
        intensidade = Helpers.input(
            "Insira a intensidade da atividade \nFraco (2)\nIntenso (3)\nVigoroso (4)\n\nInsira o número correspondente: ");
      }
    }
  }

  // MÉTODOS *******************************************************************
  @Override // Override do método mostraAtividade da classe Atividade
  public String mostraAtividade() {
    // Retorna a string formatada com os dados da atividade adicionando a
    // Intensidade
    return super.mostraAtividade() + String.format("\nIntensidade: %s",
        (getIntensidade() == 2 ? "Fraco" : getIntensidade() == 3 ? "Intenso" : "Vigoroso"));
  }

  @Override // Override do método atualizaAtividade da classe Atividade
  public void atualizaAtividade() {
    Helpers.clear();
    // Mostra as informações da atividade que será atualizada
    System.out.println(mostraAtividade());

    System.out.println("\nO que deseja atualizar?");
    System.out.println("1 - Descrição");
    System.out.println("2 - Data");
    System.out.println("3 - Duração");
    System.out.println("4 - Satisfação");
    System.out.println("5 - Intensidade");

    int opcao;
    // Loop para verificar se a opção está no formato correto
    while (true) {
      try {
        opcao = Integer.parseInt(Helpers.input("\nInsira o número correspondente: "));
        if (opcao < 1 || opcao > 5) {
          throw new Exception();
        }
        break;
      } catch (Exception e) {
        System.err.println("\nInsira uma opção válida");
      }
    }
    switch (opcao) {
      case 1:
        setDescricao(Helpers.input("Insira uma nova descrição: "));
        try {
          dao.update(getId(), 3, getDescricao());
        } catch (Exception e) {
          System.err.println("Erro ao atualizar a descrição: " + e.getMessage() + "\n");
        }
        break;
      case 2:
        setData(Helpers.input("Insira uma nova data (dd/mm/aaaa): "));
        try {
          dao.update(getId(), 4, getData());
        } catch (Exception e) {
          System.err.println("Erro ao atualizar a data: " + e.getMessage() + "\n");
        }
        break;
      case 3:
        setDuracao(Helpers.input("Insira uma nova duração (em minutos): "));
        try {
          dao.update(getId(), 1, getDuracao() + "");
        } catch (Exception e) {
          System.err.println("Erro ao atualizar a duração: " + e.getMessage() + "\n");
        }
        break;
      case 4:
        setSatisfacao(Helpers.input("Insira uma nova satisfação (1 ou -1): "));
        try {
          dao.update(getId(), 2, getSatisfacao() + "");
        } catch (Exception e) {
          System.err.println("Erro ao atualizar a satisfação: " + e.getMessage() + "\n");
        }
        break;
      case 5:
        setIntensidade(Helpers.input("Insira uma nova intensidade (2, 3 ou 4): "));
        try {
          dao.update(getId(), 5, getIntensidade() + "");
        } catch (Exception e) {
          System.err.println("Erro ao atualizar a intensidade: " + e.getMessage() + "\n");
        }
        break;
    }
  }

  // Implementação do método gastoDeEnergia da classe AvFisica
  public double getGastoDeEnergia() {
    return duracao * intensidade * 3;
  }
}

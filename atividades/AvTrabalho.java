package atividades;

import lib.Helpers;

public class AvTrabalho extends Atividade {
  private int dificuldade;

  // Construtor da classe AvTrabalho, inicializa as propriedades
  public AvTrabalho(String dificuldade, String duracao, String satisfacao, String descricao, String data) {
    try {
      setDificuldade(dificuldade);
      setDuracao(duracao);
      setSatisfacao(satisfacao);
      setDescricao(descricao);
      setData(data);
      setTipo(3);
    } catch (Exception e) {
      System.err.println("Erro ao criar atividade de trabalho.");
    }
  }

  // GETTERS *******************************************************************
  public int getDificuldade() {
    return this.dificuldade;
  }

  // SETTERS *******************************************************************
  // Lógica de tratamento do set de Dificuldade
  public void setDificuldade(String dificuldade) {
    // Loop para verificar se a dificuldade está no formato correto
    while (true) {
      try {
        int d = Integer.parseInt(dificuldade);
        // Verifica se a dificuldade é 1, 2 ou 3
        if (d == 1 || d == 2 || d == 3) {
          this.dificuldade = d;
          break;
        } else {
          throw new Exception();
        }
      } catch (Exception e) {
        System.err.println("\n\nInsira uma dificuldade válida");
        dificuldade = Helpers.input(
            "Insira a dificuldade da atividade \nFácil (1)\nMédia (2)\nDifícil (3)\n\nInsira o número correspondente: ");
      }
    }
  }

  // MÉTODOS *******************************************************************
  @Override // Override do método mostraAtividade da classe Atividade
  public String mostraAtividade() {
    return super.mostraAtividade() + String.format("\nDificuldade: %s",
        getDificuldade() == 1 ? "Fácil" : getDificuldade() == 2 ? "Média" : "Difícil");
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
    System.out.println("5 - Dificuldade");
    int opcao;
    while (true) {
      try {
        opcao = Integer.parseInt(Helpers.input("\nInsira o número correspondente: "));
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
        setDescricao(Helpers.input("Insira uma nova descrição: "));
        break;
      case 2:
        setData(Helpers.input("Insira uma nova data (dd/mm/aaaa): "));
        break;
      case 3:
        setDuracao(Helpers.input("Insira uma nova duração (em minutos): "));
        break;
      case 4:
        setSatisfacao(Helpers.input("Insira uma nova satisfação (0-10): "));
        break;
      case 5:
        setDificuldade(Helpers.input("Insira uma nova dificuldade (0-10): "));
        break;
    }
  }

  // Implementação do método gastoDeEnergia da classe AvTrabalho
  public double getGastoDeEnergia() {
    return duracao * dificuldade * 2;
  }
}

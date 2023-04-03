package atividades;

public class AvTrabalho extends Atividade {
  int dificuldade;

  //
  public AvTrabalho(String dificuldade, String duracao, String satisfacao, String descricao, String data) {
    try {
      setDificuldade(dificuldade);
      setDuracao(duracao);
      setSatisfacao(satisfacao);
      setDescricao(descricao);
      setData(data);
    } catch (Exception e) {
      throw new IllegalArgumentException("Não foi possível criar a atividade.");
    }
  }

  public void setDificuldade(String dificuldade) {
    while (true) {
      try {
        int i = Integer.parseInt(dificuldade);
        if (i == 1 || i == 2 || i == 3) {
          this.dificuldade = i;
          break;
        } else {
          throw new Exception();
        }
      } catch (Exception e) {
        helper.clear();
        System.err.println("Insira uma dificuldade válida");
        dificuldade = helper.input(
            "Insira a dificuldade da atividade \nFácil (1)\nMédia (2)\nDifícil (3)\n\nInsira o número correspondente: ");
      }
    }
  }

  @Override
  public String mostraAtividade() {
    return super.mostraAtividade() + String.format("\nDificuldade: %d", dificuldade);
  }

  //
  @Override
  public void atualizaAtividade() {
    helper.clear();
    mostraAtividade();

    System.out.println("O que deseja atualizar?");
    System.out.println("1 - Descrição");
    System.out.println("2 - Data");
    System.out.println("3 - Duração");
    System.out.println("4 - Satisfação");
    System.out.println("5 - Dificuldade");
    System.out.println("6 - Voltar");
    int opcao;
    while (true) {
      try {
        opcao = Integer.parseInt(helper.input("\nOpção: "));
        if (opcao < 1 || opcao > 6) {
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
        setSatisfacao(helper.input("Insira uma nova satisfação (0-10): "));
        break;
      case 5:
        setDificuldade(helper.input("Insira uma nova dificuldade (0-10): "));
        break;
      case 6:
        break;
      default:
        System.err.println("Opção inválida");
        atualizaAtividade();
    }
  }

  public double gastoDeEnergia() {
    return duracao * dificuldade * 2;
  }
}

package atividades;

public class AvFisica extends Atividade {
  int intensidade;

  public AvFisica(String intensidade, String duracao, String satisfacao, String descricao, String data) {
    try {
      setIntensidade(intensidade);
      setDuracao(duracao);
      setSatisfacao(satisfacao);
      setDescricao(descricao);
      setData(data);
    } catch (Exception e) {
      throw new IllegalArgumentException("Não foi possível criar a atividade.");
    }
  }

  public void setIntensidade(String intensidade) {
    while (true) {
      try {
        int i = Integer.parseInt(intensidade);
        if (i == 2 || i == 3 || i == 4) {
          this.intensidade = i;
          break;
        } else {
          throw new Exception();
        }
      } catch (Exception e) {
        System.err.println("Insira uma intensidade válida");
        intensidade = helper.input(
            "Insira a intensidade da atividade \nFraco (2)\nIntenso (3)\nVigoroso (4)\n\nInsira o número correspondente: ");
      }
    }
  }

  @Override
  public String mostraAtividade() {
    return super.mostraAtividade() + String.format("\nIntensidade: %d", intensidade);
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
    System.out.println("5 - Intensidade");
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
        helper.clear();
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
        setIntensidade(helper.input("Insira uma nova intensidade: "));
        break;
      case 6:
        break;
      default:
        System.out.println("Opção inválida");
        atualizaAtividade();
    }
    ;
  }

  public double gastoDeEnergia() {
    return duracao * intensidade * 3;
  }
}

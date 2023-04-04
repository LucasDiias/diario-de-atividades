package atividades;

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
        intensidade = helper.input(
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

  // Implementação do método gastoDeEnergia da classe AvFisica
  public double gastoDeEnergia() {
    return duracao * intensidade * 3;
  }
}

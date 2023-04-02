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

  public double gastoDeEnergia() {
    return duracao * intensidade * 3;
  }
}

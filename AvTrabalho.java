public class AvTrabalho extends Atividade {
  int dificuldade;

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
        System.err.println("Insira uma dificuldade válida");
        dificuldade = helper.input(
            "Insira a dificuldade da atividade \nFácil (1)\nMédia (2)\nDifícil (3)\n\nInsira o número correspondente: ");
      }
    }
  }

  public double gastoDeEnergia() {
    return duracao * dificuldade * 2;
  }
}

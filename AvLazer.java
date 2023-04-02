
public class AvLazer extends Atividade {

  public AvLazer(String duracao, String satisfacao, String descricao, String data) {
    try {
      setData(data);
      setSatisfacao(satisfacao);
      setDuracao(duracao);
      setDescricao(descricao);
    } catch (Exception e) {
      throw new IllegalArgumentException("Não foi possível criar a atividade.");
    }
  }

  public double gastoDeEnergia() {
    return duracao;
  }
}
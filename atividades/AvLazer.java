package atividades;

public class AvLazer extends Atividade {
  // Construtor da classe AvLazer, inicializa as propriedades
  public AvLazer(String duracao, String satisfacao, String descricao, String data) {
    try {
      setData(data);
      setSatisfacao(satisfacao);
      setDuracao(duracao);
      setDescricao(descricao);
      setTipo(2);
    } catch (Exception e) {
      System.err.println("Erro ao criar atividade de lazer.");
    }
  }

  // MÉTODOS *******************************************************************
  // Implementação do método gastoDeEnergia da classe AvLazer
  public double getGastoDeEnergia() {
    return duracao * 1;
  }
}
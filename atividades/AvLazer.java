package atividades;

import java.time.LocalDate;

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

  // Construtor de retorno do SQL
  public AvLazer(int id, int duracao, int satisfacao, String descricao, LocalDate data) {
    try {
      this.id = id;
      this.duracao = duracao;
      this.satisfacao = satisfacao;
      this.descricao = descricao;
      this.data = data;
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
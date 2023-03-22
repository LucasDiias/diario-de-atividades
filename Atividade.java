public abstract class Atividade {
  double duracao;
  int satisfacao;
  String descricao;

  public void setDuracao(double duracao){
    this.duracao = duracao;
  }
  public double getDuracao(){
    return this.duracao;
  }

  public void setSatisfacao(int satisfacao){
    if (satisfacao == 1 || satisfacao == -1){
      this.satisfacao = satisfacao;
    } else {
      throw new IllegalArgumentException("Número inválido");
    }
  }

  public int getSatisfacao(){
    return this.satisfacao;
  }

  public abstract double gastoDeEnergia();

  public double bemEstar(){
    return gastoDeEnergia() * satisfacao / 360; 
  }
}

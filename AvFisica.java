public class AvFisica extends Atividade {
  int intensidade;

  public double gastoDeEnergia(){
    return duracao * intensidade * 3;
  }
}

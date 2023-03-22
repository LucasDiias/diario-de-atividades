public class AvTrabalho extends Atividade {
  int dificuldade;

  public double gastoDeEnergia(){
    return duracao * dificuldade * 2;
  }
}

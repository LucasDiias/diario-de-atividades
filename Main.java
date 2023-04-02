public class Main {
  public static void main(String[] args) {
    ControleDeAtividades controle = new ControleDeAtividades();

    boolean varControle = true;
    while (varControle) {
      int escolha = controle.mostraMenu();

      switch (escolha) {
        case 1:
          varControle = controle.cadastro();
          break;
        case 2:
          varControle = controle.pesquisar();
          break;
        case 3:
          varControle = controle.atualizar();
          break;
        case 4:
          varControle = controle.remover();
          break;
        case 5:
          varControle = controle.testar();
          break;
        case 6:
          varControle = false;
          break;
      }
    }
  }
}

public class Main {
  public static void main(String[] args) {
    // Instancia o controle de atividades
    ControleDeAtividades controle = new ControleDeAtividades();

    // Loop para mostrar o menu principal
    boolean varControle = true;
    while (varControle) {
      int escolha = controle.mostraMenuPrincipal();

      // Switch para escolher a opção do menu
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
          varControle = controle.listarAtividades();
          break;
        case 6:
          varControle = controle.resumo();
          break;
        case 7:
          varControle = controle.testar();
          break;
        case 8:
          varControle = false;
          break;
      }
    }
  }
}

import lib.Helpers;

public class Main {
  public static void main(String[] args) {
    // Instancia o controle de atividades
    ControleAtividades controle = new ControleAtividades();

    // Loop para mostrar o menu principal
    boolean varControle = true;
    while (varControle) {
      int escolha = controle.mostraMenuPrincipal();

      // Switch para escolher a opção do menu
      switch (escolha) {
        case 1:
          controle.cadastro();
          break;
        case 2:
          controle.pesquisar();
          break;
        case 3:
          controle.atualizar();
          break;
        case 4:
          controle.remover();
          break;
        case 5:
          try {
            controle.listarAtividades();
          } catch (Exception e) {
            System.err.println("Erro ao listar atividades: " + e.getMessage());
          }
          Helpers.input("\n\nPressione ENTER para continuar...");
          break;
        case 6:
          controle.resumo();
          break;
        case 7:
          controle.top3Energia();
          break;
        case 8:
          varControle = false;
          break;
      }
    }
  }
}

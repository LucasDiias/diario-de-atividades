package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import atividades.Atividade;
import atividades.AvFisica;
import atividades.AvLazer;
import atividades.AvTrabalho;
import lib.db.ConnectionFactory;
import lib.Helpers;

public class AtividadeDAO {
  private Connection connection;

  public AtividadeDAO() {
    // Utiliza a ConnectionFactory para estabelecer uma conexão com o banco
    try {
      this.connection = ConnectionFactory.getConnection();
    } catch (SQLException e) {
      System.out.println("Erro ao conectar com o banco de dados.\nMensagem de erro: " + e.getMessage() + "\n");
      System.out
          .println("Verifique se o banco de dados está rodando e se o usuário e senha estão corretos e reinicie.\n");
      Helpers.input("Pressione ENTER para continuar...");
      System.exit(1);
    }
  }

  // Cadastro de atividades no banco
  public void add(Atividade av) throws SQLException {
    String sql = "INSERT INTO atividades (tipo, duracao, satisfacao, descricao, data, intensidade, dificuldade) VALUES (?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement stmt = connection.prepareStatement(sql);

    stmt.setInt(1, av.getTipoInt());
    stmt.setInt(2, av.getDuracao());
    stmt.setInt(3, av.getSatisfacao());
    stmt.setString(4, av.getDescricao());
    stmt.setDate(5, java.sql.Date.valueOf(av.getDataSQL()));
    // Define os valores de intensidade e dificuldade como nulos inicialmente
    stmt.setNull(6, java.sql.Types.INTEGER);
    stmt.setNull(7, java.sql.Types.INTEGER);

    // Verifica o tipo da atividade para inserir os valores corretos
    if (av instanceof AvFisica) {
      stmt.setInt(6, ((AvFisica) av).getIntensidade());
    } else if (av instanceof AvTrabalho) {
      stmt.setInt(7, ((AvTrabalho) av).getDificuldade());
    }

    stmt.executeUpdate();
  }

  // Pesquisa de atividades no banco
  public List<Atividade> pesquisa(int tipo, String param) throws SQLException {
    List<Atividade> avs = new ArrayList<Atividade>();

    // Verifica o tipo da pesquisa onde:
    // 1 - Pesquisa por data
    // 2 - Pesquisa por tipo
    // 3 - Pesquisa por descrição
    if (tipo == 1) {
      String sql = "SELECT * FROM atividades WHERE data = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setDate(1, java.sql.Date.valueOf(Helpers.stringToLocalDate(param)));
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        avs.add(geraAtividade(rs));
      }
    } else if (tipo == 2) {
      String sql = "SELECT * FROM atividades WHERE tipo = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setInt(1, Integer.parseInt(param));
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        avs.add(geraAtividade(rs));
      }
    } else {
      String sql = "SELECT * FROM atividades WHERE descricao LIKE ?";
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, "%" + param + "%");
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        avs.add(geraAtividade(rs));
      }
    }

    return avs;
  }

  // Método para gerar uma atividade a partir de um ResultSet
  private Atividade geraAtividade(ResultSet rs) throws SQLException {
    int tipo = rs.getInt("tipo");
    int id = rs.getInt("id");
    int duracao = rs.getInt("duracao");
    int satisfacao = rs.getInt("satisfacao");
    String descricao = rs.getString("descricao");
    LocalDate data = rs.getDate("data").toLocalDate();

    Atividade av;
    // Verifica o tipo da atividade para instanciar o objeto correto
    if (tipo == 1) {
      int intensidade = rs.getInt("intensidade");
      av = new AvFisica(id, intensidade, duracao, satisfacao, descricao, data);
    } else if (tipo == 2) {
      av = new AvLazer(id, duracao, satisfacao, descricao, data);
    } else {
      int dificuldade = rs.getInt("dificuldade");
      av = new AvTrabalho(id, dificuldade, duracao, satisfacao, descricao, data);
    }

    return av;
  }

  // Retorna todas as atividades do banco
  public List<Atividade> getAllAtividades() throws SQLException {
    List<Atividade> avs = new ArrayList<Atividade>();

    String sql = "SELECT * FROM atividades";
    PreparedStatement stmt = connection.prepareStatement(sql);

    ResultSet rs = stmt.executeQuery();

    while (rs.next()) {
      avs.add(geraAtividade(rs));
    }

    return avs;
  }

  // Atualiza a coluna especificada de uma atividade no banco
  public void update(int id, int col, String valor) throws SQLException {
    if (col == 1) {
      String sql = "UPDATE atividades SET duracao = ? WHERE id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setInt(1, Integer.parseInt(valor));
      stmt.setInt(2, id);
      stmt.executeUpdate();

    } else if (col == 2) {
      String sql = "UPDATE atividades SET satisfacao = ? WHERE id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setInt(1, Integer.parseInt(valor));
      stmt.setInt(2, id);
      stmt.executeUpdate();

    } else if (col == 3) {
      String sql = "UPDATE atividades SET descricao = ? WHERE id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, valor);
      stmt.setInt(2, id);
      stmt.executeUpdate();

    } else if (col == 4) {
      String sql = "UPDATE atividades SET data = ? WHERE id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setDate(1, java.sql.Date.valueOf(Helpers.stringToLocalDate(valor)));
      stmt.setInt(2, id);
      stmt.executeUpdate();

    } else if (col == 5) {
      String sql = "UPDATE atividades SET intensidade = ? WHERE id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setInt(1, Integer.parseInt(valor));
      stmt.setInt(2, id);
      stmt.executeUpdate();

    } else if (col == 6) {
      String sql = "UPDATE atividades SET dificuldade = ? WHERE id = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, Integer.parseInt(valor));
      stmt.setInt(2, id);
      stmt.executeUpdate();
    }
  }

  // Deleta uma atividade do banco
  public void delete(int id) throws SQLException {
    String sql = "DELETE FROM atividades WHERE id = ?";
    PreparedStatement stmt = connection.prepareStatement(sql);

    stmt.setInt(1, id);
    stmt.executeUpdate();
  }

  public Atividade getAtividade(int id) throws SQLException {
    String sql = "SELECT * FROM atividades WHERE id = ?";
    PreparedStatement stmt = connection.prepareStatement(sql);

    stmt.setInt(1, id);
    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
      return geraAtividade(rs);
    }

    return null;
  }

  // Retorna uma lista com os ids de todas as atividades
  public List<Integer> getIds() throws SQLException {
    List<Integer> ids = new ArrayList<Integer>();

    String sql = "SELECT id FROM atividades";
    PreparedStatement stmt = connection.prepareStatement(sql);
    ResultSet rs = stmt.executeQuery();

    while (rs.next()) {
      ids.add(rs.getInt("id"));
    }

    return ids;
  }
}

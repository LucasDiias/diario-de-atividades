package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import atividades.Atividade;
import atividades.AvFisica;
import atividades.AvLazer;
import atividades.AvTrabalho;
import lib.Helpers;
import lib.db.ConnectionFactory;

public class AtividadeDAO {
  private Connection connection;

  public AtividadeDAO() {
    this.connection = ConnectionFactory.getConnection();
  }

  // Cadastro de atividades no banco
  public void add(Atividade av) {
    String sql = "INSERT INTO atividades (tipo, duracao, satisfacao, descricao, data, intensidade, dificuldade) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql);) {
      stmt.setInt(1, av.getTipoInt());
      stmt.setInt(2, av.getDuracao());
      stmt.setInt(3, av.getSatisfacao());
      stmt.setString(4, av.getDescricao());
      stmt.setDate(5, java.sql.Date.valueOf(av.getDataSQL()));
      stmt.setNull(6, java.sql.Types.INTEGER);
      stmt.setNull(7, java.sql.Types.INTEGER);

      if (av instanceof AvFisica) {
        stmt.setInt(6, ((AvFisica) av).getIntensidade());
      } else if (av instanceof AvTrabalho) {
        stmt.setInt(7, ((AvTrabalho) av).getDificuldade());
      }

      stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  public List<Atividade> pesquisa(int tipo, String param) {
    List<Atividade> avs = new ArrayList<Atividade>();

    if (tipo == 1) {
      String sql = "SELECT * FROM atividades WHERE data = ?";

      try (PreparedStatement stmt = connection.prepareStatement(sql);) {
        stmt.setDate(1, java.sql.Date.valueOf(Helpers.stringToDate(param)));
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
          avs.add(geraAtividade(rs));
        }
      } catch (SQLException e) {
        System.out.println(e);
      }
    } else if (tipo == 2) {
      String sql = "SELECT * FROM atividades WHERE tipo = ?";
      try (PreparedStatement stmt = connection.prepareStatement(sql);) {
        stmt.setInt(1, Integer.parseInt(param));
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
          avs.add(geraAtividade(rs));
        }
      } catch (SQLException e) {
        System.out.println(e);
      }
    } else {
      String sql = "SELECT * FROM atividades WHERE descricao LIKE ?";
      try (PreparedStatement stmt = connection.prepareStatement(sql);) {
        stmt.setString(1, "%" + param + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
          avs.add(geraAtividade(rs));
        }
      } catch (SQLException e) {
        System.out.println(e);
      }
    }

    return avs;
  }

  private Atividade geraAtividade(ResultSet rs) throws SQLException {
    int tipo = rs.getInt("tipo");
    int id = rs.getInt("id");
    int duracao = rs.getInt("duracao");
    int satisfacao = rs.getInt("satisfacao");
    String descricao = rs.getString("descricao");
    LocalDate data = rs.getDate("data").toLocalDate();

    Atividade av;
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

  public List<Atividade> getAllAtividades() {
    List<Atividade> avs = new ArrayList<Atividade>();

    String sql = "SELECT * FROM atividades";

    try (PreparedStatement stmt = connection.prepareStatement(sql);) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        avs.add(geraAtividade(rs));
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    return avs;
  }

  public void update(int id, int col, String valor) {
    if (col == 1) {
      String sql = "UPDATE atividades SET duracao = ? WHERE id = ?";

      try (PreparedStatement stmt = connection.prepareStatement(sql);) {
        stmt.setInt(1, Integer.parseInt(valor));
        stmt.setInt(2, id);
        stmt.executeUpdate();
      } catch (SQLException e) {
        System.out.println(e);
      }
    } else if (col == 2) {
      String sql = "UPDATE atividades SET satisfacao = ? WHERE id = ?";

      try (PreparedStatement stmt = connection.prepareStatement(sql);) {
        stmt.setInt(1, Integer.parseInt(valor));
        stmt.setInt(2, id);
        stmt.executeUpdate();
      } catch (SQLException e) {
        System.out.println(e);
      }
    } else if (col == 3) {
      String sql = "UPDATE atividades SET descricao = ? WHERE id = ?";

      try (PreparedStatement stmt = connection.prepareStatement(sql);) {
        stmt.setString(1, valor);
        stmt.setInt(2, id);
        stmt.executeUpdate();
      } catch (SQLException e) {
        System.out.println(e);
      }
    } else if (col == 4) {
      String sql = "UPDATE atividades SET data = ? WHERE id = ?";

      try (PreparedStatement stmt = connection.prepareStatement(sql);) {
        stmt.setDate(1, java.sql.Date.valueOf(Helpers.stringToDate(valor)));
        stmt.setInt(2, id);
        stmt.executeUpdate();
      } catch (SQLException e) {
        System.out.println(e);
      }
    } else if (col == 5) {
      String sql = "UPDATE atividades SET intensidade = ? WHERE id = ?";

      try (PreparedStatement stmt = connection.prepareStatement(sql);) {
        stmt.setInt(1, Integer.parseInt(valor));
        stmt.setInt(2, id);
        stmt.executeUpdate();
      } catch (SQLException e) {
        System.out.println(e);
      }
    } else if (col == 6) {
      String sql = "UPDATE atividades SET dificuldade = ? WHERE id = ?";

      try (PreparedStatement stmt = connection.prepareStatement(sql);) {
        stmt.setInt(1, Integer.parseInt(valor));
        stmt.setInt(2, id);
        stmt.executeUpdate();
      } catch (SQLException e) {
        System.out.println(e);
      }
    }
  }

  public void delete(int id) {
    String sql = "DELETE FROM atividades WHERE id = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql);) {
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  public List<Integer> getIds() {
    List<Integer> ids = new ArrayList<Integer>();

    String sql = "SELECT id FROM atividades";

    try (PreparedStatement stmt = connection.prepareStatement(sql);) {
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        ids.add(rs.getInt("id"));
      }
    } catch (SQLException e) {
      System.out.println(e);
    }

    return ids;
  }
}

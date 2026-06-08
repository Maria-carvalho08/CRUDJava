package com.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlunoDAO {

    private static final Logger logger =
            Logger.getLogger(AlunoDAO.class.getName());

    public void inserir(AlunoDTO aluno) {

        String sql =
                "INSERT INTO alunos (nome, idade, curso, nota_final) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setFloat(4, aluno.getNotaFinal());

            stmt.executeUpdate();

        } catch (Exception e) {

            logger.log(Level.SEVERE,
                    "Erro ao inserir aluno: " + e.getMessage(), e);
        }
    }

    public ArrayList<AlunoDTO> listar() {

        ArrayList<AlunoDTO> lista = new ArrayList<>();

        String sql = "SELECT * FROM alunos";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                AlunoDTO aluno = new AlunoDTO(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("curso"),
                        rs.getFloat("nota_final")
                );

                lista.add(aluno);
            }

        } catch (Exception e) {

            logger.log(Level.SEVERE,
                    "Erro ao listar alunos: " + e.getMessage(), e);
        }

        return lista;
    }

    public void atualizar(AlunoDTO aluno) {

        String sql =
                "UPDATE alunos SET nome=?, idade=?, curso=?, nota_final=? WHERE id=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setFloat(4, aluno.getNotaFinal());
            stmt.setInt(5, aluno.getId());

            stmt.executeUpdate();

        } catch (Exception e) {

            logger.log(Level.SEVERE,
                    "Erro ao atualizar aluno: " + e.getMessage(), e);
        }
    }

    public void excluir(int id) {

        String sql = "DELETE FROM alunos WHERE id=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (Exception e) {

            logger.log(Level.SEVERE,
                    "Erro ao excluir aluno: " + e.getMessage(), e);
        }
    }
}
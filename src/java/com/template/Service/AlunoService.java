package com.template.Service;

import com.template.model.dao.IAlunoDAO;
import com.template.model.dto.AlunoDTO;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AlunoService implements IAlunoService {

    private IAlunoDAO alunoDAO;

    public AlunoService(IAlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    @Override
    public void inserir(
            String nome,
            String idade,
            String curso,
            String notaFinal) {

        AlunoDTO aluno = new AlunoDTO(
                0,
                nome,
                Integer.parseInt(idade),
                curso,
                Float.parseFloat(
                        notaFinal.replace(",", ".")
                )
        );

        alunoDAO.inserir(aluno);
    }

    @Override
    public void excluir(int id) {
        alunoDAO.excluir(id);
    }

    @Override
    public void atualizar(
            AlunoDTO aluno,
            String nome,
            String idade,
            String curso,
            String notaFinal) {

        aluno.setNome(nome);
        aluno.setIdade(Integer.parseInt(idade));
        aluno.setCurso(curso);
        aluno.setNotaFinal(
                Float.parseFloat(
                        notaFinal.replace(",", ".")
                )
        );

        alunoDAO.atualizar(aluno);
    }

    @Override
    public ArrayList<AlunoDTO> listar() {
        return alunoDAO.listar();
    }

    @Override
    public void limparCampos(
            TextField txtID,
            TextField txtNome,
            TextField txtIdade,
            TextField txtCurso,
            TextField txtNotaFinal,
            Label lblMensagem) {

        txtID.clear();
        txtNome.clear();
        txtIdade.clear();
        txtCurso.clear();
        txtNotaFinal.clear();
        lblMensagem.setText("");
    }
}
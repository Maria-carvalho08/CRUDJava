package com.template.Service;

import com.template.model.dao.AlunoDAO;
import com.template.model.dto.AlunoDTO;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AlunoService {

    private AlunoDAO alunoDAO = new AlunoDAO();

    public void inserir(AlunoDTO aluno) {
        alunoDAO.inserir(aluno);
    }

    public void excluir(int id) {
        alunoDAO.excluir(id);
    }

    public void atualizar(AlunoDTO aluno) {
        alunoDAO.atualizar(aluno);
    }

    public ArrayList<AlunoDTO> listar() {
        return alunoDAO.listar();
    }

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
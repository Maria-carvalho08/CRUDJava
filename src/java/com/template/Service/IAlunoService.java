package com.template.Service;

import com.template.model.dto.AlunoDTO;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public interface IAlunoService {

    void inserir(
            String nome,
            String idade,
            String curso,
            String notaFinal
    );

    void excluir(int id);

    void atualizar(
            AlunoDTO aluno,
            String nome,
            String idade,
            String curso,
            String notaFinal
    );

    ArrayList<AlunoDTO> listar();

    void limparCampos(
            TextField txtID,
            TextField txtNome,
            TextField txtIdade,
            TextField txtCurso,
            TextField txtNotaFinal,
            Label lblMensagem
    );
}
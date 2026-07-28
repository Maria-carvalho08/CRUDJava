package com.template.controller;

import com.template.model.dao.AlunoDAO;
import com.template.model.dto.AlunoDTO;
import com.template.util.DialogUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Optional;

public class MainController {

    @FXML private Button btnSalvar, btnLimpar, btnDeletar, btnAtualizar;
    @FXML private TextField txtNome, txtID, txtIdade, txtCurso, txtNotaFinal;
    @FXML private TableView<AlunoDTO> tblAlunos;
    @FXML private TableColumn<AlunoDTO, Integer> colID;
    @FXML private TableColumn<AlunoDTO, String> colNome;
    @FXML private TableColumn<AlunoDTO, Integer> colIdade;
    @FXML private TableColumn<AlunoDTO, String> colCurso;
    @FXML private TableColumn<AlunoDTO, Float> colNotaFinal;
    @FXML private Label lblMensagem;

    AlunoDAO estudante = new AlunoDAO();

    // Função para atualizar o Label de mensagem com cor
    private void atualizarMensagem(String texto, boolean erro) {
        lblMensagem.setText(texto);
        if (erro) {
            lblMensagem.setTextFill(Color.BLACK);
        } else {
            lblMensagem.setTextFill(Color.BLACK);
        }
    }

    // NOVO: Atalho para a tecla ENTER
    @FXML
    private void onEnterPressed(ActionEvent event) {
        // Se o ID estiver preenchido, ele tenta atualizar, senão tenta salvar novo
        if (txtID.getText() != null && !txtID.getText().isEmpty()) {
            btnAtualizarAction(null);
        } else {
            btnSalvarAction(null);
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if (validarCampos()) {
            try {
                AlunoDTO aluno = new AlunoDTO(
                        0,
                        txtNome.getText(),
                        Integer.parseInt(txtIdade.getText()),
                        txtCurso.getText(),
                        Float.parseFloat(txtNotaFinal.getText().replace(",", "."))
                );
                estudante.inserir(aluno);
                atualizarMensagem("Aluno cadastrado com sucesso!", false);
                carregarAlunos();
                btnLimparAction(null);
            } catch (Exception e) {
                atualizarMensagem("Erro ao salvar: " + e.getMessage(), true);
            }
        }
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        AlunoDTO selecionado = tblAlunos.getSelectionModel().getSelectedItem();

        if (selecionado != null) {

            boolean confirmou = DialogUtil.showConfirmation(
                    "Confirmar Exclusão",
                    "Tem certeza que deseja excluir o aluno: " + selecionado.getNome() + "?"
            );

            if (confirmou) {
                estudante.excluir(selecionado.getId());
                atualizarMensagem("Aluno excluído com sucesso!", false);
                carregarAlunos();
                btnLimparAction(null);
            }

        } else {
            atualizarMensagem("Selecione um aluno para excluir!", true);
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        AlunoDTO selecionado = tblAlunos.getSelectionModel().getSelectedItem();

        if (selecionado != null) {

            if (validarCampos()) {

                boolean confirmou = DialogUtil.showConfirmation(
                        "Confirmar Atualização",
                        "Deseja salvar as alterações para "
                                + selecionado.getNome() + "?"
                );

                if (confirmou) {
                    selecionado.setNome(txtNome.getText());
                    selecionado.setIdade(Integer.parseInt(txtIdade.getText()));
                    selecionado.setCurso(txtCurso.getText());
                    selecionado.setNotaFinal(
                            Float.parseFloat(
                                    txtNotaFinal.getText().replace(",", ".")
                            )
                    );

                    estudante.atualizar(selecionado);
                    atualizarMensagem("Dados atualizados com sucesso!", false);
                    carregarAlunos();
                }
            }

        } else {
            atualizarMensagem("Selecione um aluno para atualizar!", true);
        }
    }
    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtID.clear();
        txtNome.clear();
        txtIdade.clear();
        txtCurso.clear();
        txtNotaFinal.clear();
        lblMensagem.setText("");
    }

    private boolean validarCampos() {
        if (txtNome.getText().isEmpty() || txtIdade.getText().isEmpty() ||
                txtCurso.getText().isEmpty() || txtNotaFinal.getText().isEmpty()) {
            atualizarMensagem("Preencha todos os campos!", true);
            return false;
        }
        try {
            Integer.parseInt(txtIdade.getText());
            Float.parseFloat(txtNotaFinal.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            atualizarMensagem("Idade ou Nota com formato inválido!", true);
            return false;
        }
        return true;
    }

    @FXML
    private void carregarAlunos() {
        ArrayList<AlunoDTO> lista = estudante.listar();
        tblAlunos.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    private void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        colNotaFinal.setCellValueFactory(new PropertyValueFactory<>("notaFinal"));
        tblAlunos.setOnMouseClicked(event -> selecionarAluno());
        carregarAlunos();
    }

    @FXML
    private void selecionarAluno() {
        AlunoDTO selecionado = tblAlunos.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            txtID.setText(String.valueOf(selecionado.getId()));
            txtNome.setText(selecionado.getNome());
            txtIdade.setText(String.valueOf(selecionado.getIdade()));
            txtCurso.setText(selecionado.getCurso());
            txtNotaFinal.setText(String.valueOf(selecionado.getNotaFinal()));
            lblMensagem.setText("");
        }
    }
}

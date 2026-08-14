package com.template.controller;

import com.template.Service.AlunoService;
import com.template.model.dto.AlunoDTO;
import com.template.util.DialogUtil;
import com.template.validator.AlunosValidator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

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

    private AlunoService alunoService = new AlunoService();

    private void atualizarMensagem(String texto) {
        lblMensagem.setText(texto);
    }

    @FXML
    private void onEnterPressed(ActionEvent event) {
        if (txtID.getText() != null && !txtID.getText().isEmpty()) {
            btnAtualizarAction(null);
        } else {
            btnSalvarAction(null);
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {

        if (!AlunosValidator.validarCampos(
                txtNome.getText(),
                txtIdade.getText(),
                txtCurso.getText(),
                txtNotaFinal.getText())) {

            atualizarMensagem("Preencha todos os campos corretamente!");
            return;
        }

        try {
            AlunoDTO aluno = new AlunoDTO(
                    0,
                    txtNome.getText(),
                    Integer.parseInt(txtIdade.getText()),
                    txtCurso.getText(),
                    Float.parseFloat(
                            txtNotaFinal.getText().replace(",", ".")
                    )
            );

            alunoService.inserir(aluno);

            atualizarMensagem("Aluno cadastrado com sucesso!");

            carregarAlunos();
            btnLimparAction(null);

        } catch (Exception e) {
            atualizarMensagem(
                    "Erro ao salvar: " + e.getMessage()
            );
        }
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {

        AlunoDTO selecionado =
                tblAlunos.getSelectionModel().getSelectedItem();

        if (selecionado != null) {

            boolean confirmou = DialogUtil.showConfirmation(
                    "Confirmar Exclusão",
                    "Tem certeza que deseja excluir o aluno: "
                            + selecionado.getNome() + "?"
            );

            if (confirmou) {

                alunoService.excluir(selecionado.getId());

                atualizarMensagem(
                        "Aluno excluído com sucesso!"
                );

                carregarAlunos();
                btnLimparAction(null);
            }

        } else {
            atualizarMensagem(
                    "Selecione um aluno para excluir!"
            );
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {

        AlunoDTO selecionado =
                tblAlunos.getSelectionModel().getSelectedItem();

        if (selecionado != null) {

            if (!AlunosValidator.validarCampos(
                    txtNome.getText(),
                    txtIdade.getText(),
                    txtCurso.getText(),
                    txtNotaFinal.getText())) {

                atualizarMensagem(
                        "Preencha todos os campos corretamente!"
                );
                return;
            }

            boolean confirmou = DialogUtil.showConfirmation(
                    "Confirmar Atualização",
                    "Deseja salvar as alterações para "
                            + selecionado.getNome() + "?"
            );

            if (confirmou) {

                selecionado.setNome(txtNome.getText());

                selecionado.setIdade(
                        Integer.parseInt(txtIdade.getText())
                );

                selecionado.setCurso(txtCurso.getText());

                selecionado.setNotaFinal(
                        Float.parseFloat(
                                txtNotaFinal.getText().replace(",", ".")
                        )
                );

                alunoService.atualizar(selecionado);

                atualizarMensagem(
                        "Dados atualizados com sucesso!"
                );

                carregarAlunos();
            }

        } else {
            atualizarMensagem(
                    "Selecione um aluno para atualizar!"
            );
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

    @FXML
    private void carregarAlunos() {
        ArrayList<AlunoDTO> lista = alunoService.listar();

        tblAlunos.setItems(
                FXCollections.observableArrayList(lista)
        );
    }

    @FXML
    private void initialize() {

        colID.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        colIdade.setCellValueFactory(
                new PropertyValueFactory<>("idade")
        );

        colCurso.setCellValueFactory(
                new PropertyValueFactory<>("curso")
        );

        colNotaFinal.setCellValueFactory(
                new PropertyValueFactory<>("notaFinal")
        );

        tblAlunos.setOnMouseClicked(
                event -> selecionarAluno()
        );

        carregarAlunos();
    }

    @FXML
    private void selecionarAluno() {

        AlunoDTO selecionado =
                tblAlunos.getSelectionModel().getSelectedItem();

        if (selecionado != null) {

            txtID.setText(
                    String.valueOf(selecionado.getId())
            );

            txtNome.setText(
                    selecionado.getNome()
            );

            txtIdade.setText(
                    String.valueOf(selecionado.getIdade())
            );

            txtCurso.setText(
                    selecionado.getCurso()
            );

            txtNotaFinal.setText(
                    String.valueOf(selecionado.getNotaFinal())
            );

            lblMensagem.setText("");
        }
    }
}
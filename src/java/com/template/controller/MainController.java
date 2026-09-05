package com.template.controller;

import com.template.Service.IAlunoService;
import com.template.model.dto.AlunoDTO;
import com.template.util.DialogUtil;
import com.template.validator.IAlunosValidator;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MainController {

    private IAlunosValidator alunosValidator;

    private IAlunoService alunoService;

    public void setAlunoService(IAlunoService alunoService) {
        this.alunoService = alunoService;
    }

    public void setAlunosValidator(IAlunosValidator alunosValidator) {
        this.alunosValidator = alunosValidator;
    }

    @FXML private Button btnSalvar, btnLimpar, btnDeletar, btnAtualizar;
    @FXML private TextField txtNome, txtID, txtIdade, txtCurso, txtNotaFinal;
    @FXML private TableView<AlunoDTO> tblAlunos;
    @FXML private TableColumn<AlunoDTO, Integer> colID;
    @FXML private TableColumn<AlunoDTO, String> colNome;
    @FXML private TableColumn<AlunoDTO, Integer> colIdade;
    @FXML private TableColumn<AlunoDTO, String> colCurso;
    @FXML private TableColumn<AlunoDTO, Float> colNotaFinal;
    @FXML private Label lblMensagem;

    private void atualizarMensagem(String texto) {
        lblMensagem.setText(texto);
    }

    @FXML
    private void onEnterPressed(ActionEvent event) {

        if (txtID.getText() != null &&
                !txtID.getText().isEmpty()) {

            btnAtualizarAction(null);

        } else {

            btnSalvarAction(null);
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {

        if (!alunosValidator.validarCampos(
                txtNome.getText(),
                txtIdade.getText(),
                txtCurso.getText(),
                txtNotaFinal.getText())) {

            atualizarMensagem(
                    alunosValidator.getMensagemErro()
            );

            return;
        }

        try {

            alunoService.inserir(
                    txtNome.getText(),
                    txtIdade.getText(),
                    txtCurso.getText(),
                    txtNotaFinal.getText()
            );

            atualizarMensagem(
                    "Aluno cadastrado com sucesso!"
            );

            carregarAlunos();

            alunoService.limparCampos(
                    txtID,
                    txtNome,
                    txtIdade,
                    txtCurso,
                    txtNotaFinal,
                    lblMensagem
            );

        } catch (Exception e) {

            atualizarMensagem(
                    "Erro ao salvar: " + e.getMessage()
            );
        }
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {

        AlunoDTO selecionado =
                tblAlunos.getSelectionModel()
                        .getSelectedItem();

        if (selecionado != null) {

            boolean confirmou =
                    DialogUtil.showConfirmation(
                            "Confirmar Exclusão",
                            "Tem certeza que deseja excluir o aluno: "
                                    + selecionado.getNome() + "?"
                    );

            if (confirmou) {

                alunoService.excluir(
                        selecionado.getId()
                );

                atualizarMensagem(
                        "Aluno excluído com sucesso!"
                );

                carregarAlunos();

                alunoService.limparCampos(
                        txtID,
                        txtNome,
                        txtIdade,
                        txtCurso,
                        txtNotaFinal,
                        lblMensagem
                );
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
                tblAlunos.getSelectionModel()
                        .getSelectedItem();

        if (selecionado != null) {

            if (!alunosValidator.validarCampos(
                    txtNome.getText(),
                    txtIdade.getText(),
                    txtCurso.getText(),
                    txtNotaFinal.getText())) {

                atualizarMensagem(
                        alunosValidator.getMensagemErro()
                );

                return;
            }

            boolean confirmou =
                    DialogUtil.showConfirmation(
                            "Confirmar Atualização",
                            "Deseja salvar as alterações para "
                                    + selecionado.getNome() + "?"
                    );

            if (confirmou) {

                alunoService.atualizar(
                        selecionado,
                        txtNome.getText(),
                        txtIdade.getText(),
                        txtCurso.getText(),
                        txtNotaFinal.getText()
                );

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

        alunoService.limparCampos(
                txtID,
                txtNome,
                txtIdade,
                txtCurso,
                txtNotaFinal,
                lblMensagem
        );
    }

    @FXML
    public void carregarAlunos() {

        ArrayList<AlunoDTO> lista =
                alunoService.listar();

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
    }

    @FXML
    private void selecionarAluno() {

        AlunoDTO selecionado =
                tblAlunos.getSelectionModel()
                        .getSelectedItem();

        if (selecionado != null) {

            txtID.setText(
                    String.valueOf(
                            selecionado.getId()
                    )
            );

            txtNome.setText(
                    selecionado.getNome()
            );

            txtIdade.setText(
                    String.valueOf(
                            selecionado.getIdade()
                    )
            );

            txtCurso.setText(
                    selecionado.getCurso()
            );

            txtNotaFinal.setText(
                    String.valueOf(
                            selecionado.getNotaFinal()
                    )
            );

            lblMensagem.setText("");
        }
    }
}
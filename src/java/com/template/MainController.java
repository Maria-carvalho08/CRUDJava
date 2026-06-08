package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private Button btnLimpar;
    @FXML private Button btnDeletar;
    @FXML private Button btnAtualizar;

    @FXML private TextField txtNome;
    @FXML private TextField txtID;
    @FXML private TextField txtIdade;
    @FXML private TextField txtCurso;
    @FXML private TextField txtNotaFinal;

    @FXML private TableView<AlunoDTO> tblAlunos;

    @FXML private TableColumn<AlunoDTO, Integer> colID;
    @FXML private TableColumn<AlunoDTO, String> colNome;
    @FXML private TableColumn<AlunoDTO, Integer> colIdade;
    @FXML private TableColumn<AlunoDTO, String> colCurso;
    @FXML private TableColumn<AlunoDTO, Float> colNotaFinal;

    AlunoDAO alunoDAO = new AlunoDAO();

    @FXML
    private void btnSalvarAction(ActionEvent event)
    {
        String nome = txtNome.getText();

        int idade =
                Integer.parseInt(txtIdade.getText());

        String curso = txtCurso.getText();

        float notaFinal =
                Float.parseFloat(txtNotaFinal.getText());

        AlunoDTO aluno = new AlunoDTO(0, nome, idade, curso, notaFinal);

        alunoDAO.inserir(aluno);

        carregarAlunos();


    }

    @FXML
    private void btnLimparAction(ActionEvent event)
    {
        txtID.clear();
        txtNome.clear();
        txtIdade.clear();
        txtCurso.clear();
        txtNotaFinal.clear();
    }

    @FXML
    private void btnDeletarAction(ActionEvent event)
    {
        AlunoDTO alunoSelecionado =
                tblAlunos.getSelectionModel().getSelectedItem();

        if(alunoSelecionado != null)
        {
            alunoDAO.excluir(
                    alunoSelecionado.getId());

            carregarAlunos();


        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event)
    {
        AlunoDTO alunoSelecionado = tblAlunos.getSelectionModel().getSelectedItem();

        if(alunoSelecionado != null)
        {
            alunoSelecionado.setId(
                    Integer.parseInt(txtID.getText()));

            alunoSelecionado.setNome(
                    txtNome.getText());

            alunoSelecionado.setIdade(
                    Integer.parseInt(txtIdade.getText()));

            alunoSelecionado.setCurso(
                    txtCurso.getText());

            alunoSelecionado.setNotaFinal(
                    Float.parseFloat(txtNotaFinal.getText()));

            alunoDAO.atualizar(alunoSelecionado);

            carregarAlunos();

        }
    }

    @FXML
    private void carregarAlunos()
    {
        ArrayList<AlunoDTO> listaAlunos =
                alunoDAO.listar();

        tblAlunos.setItems(
                FXCollections.observableArrayList(listaAlunos));
    }

    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");

        colID.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));

        colIdade.setCellValueFactory(
                new PropertyValueFactory<>("idade"));

        colCurso.setCellValueFactory(
                new PropertyValueFactory<>("curso"));

        colNotaFinal.setCellValueFactory(
                new PropertyValueFactory<>("notaFinal"));

        tblAlunos.setOnMouseClicked(
                event -> selecionarAluno());

        carregarAlunos();
    }

    @FXML
    private void selecionarAluno()
    {
        AlunoDTO alunoSelecionado =
                tblAlunos.getSelectionModel().getSelectedItem();

        if(alunoSelecionado != null)
        {
            txtID.setText(
                    String.valueOf(alunoSelecionado.getId()));

            txtNome.setText(
                    alunoSelecionado.getNome());

            txtIdade.setText(
                    String.valueOf(alunoSelecionado.getIdade()));

            txtCurso.setText(
                    alunoSelecionado.getCurso());

            txtNotaFinal.setText(
                    String.valueOf(alunoSelecionado.getNotaFinal()));
        }

    }


}

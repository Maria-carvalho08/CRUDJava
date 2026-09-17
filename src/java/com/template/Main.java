package com.template;

import com.template.Service.AlunoService;
import com.template.controller.MainController;
import com.template.model.dao.AlunoDAO;
import com.template.validator.AlunosValidator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader =
                new FXMLLoader(
                        Main.class.getResource("main.fxml")
                );

        loader.setControllerFactory(
                type -> new MainController(
                        new AlunoService(new AlunoDAO()),
                        new AlunosValidator()
                )
        );

        Scene scene =
                new Scene(loader.load(), 600, 400);

        MainController controller =
                loader.getController();

        controller.carregarAlunos();

        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
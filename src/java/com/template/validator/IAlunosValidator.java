package com.template.validator;

import javafx.scene.control.Label;

public interface IAlunosValidator {

    boolean validarCampos(
            String nome,
            String idade,
            String curso,
            String notaFinal,
            Label lblMensagem
    );
}
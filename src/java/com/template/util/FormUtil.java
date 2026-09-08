package com.template.util;

import javafx.scene.control.Label;

public class FormUtil {

    public static void atualizarMensagem(
            Label lblMensagem,
            String mensagem) {

        lblMensagem.setText(mensagem);
    }
}
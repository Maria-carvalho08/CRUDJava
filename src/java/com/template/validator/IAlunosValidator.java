package com.template.validator;

public interface IAlunosValidator {

    boolean validarCampos(
            String nome,
            String idade,
            String curso,
            String notaFinal
    );

    String getMensagemErro();
}
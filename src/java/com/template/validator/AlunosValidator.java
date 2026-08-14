package com.template.validator;

public class AlunosValidator {

    public static boolean validarCampos(
            String nome,
            String idade,
            String curso,
            String notaFinal) {

        if (nome == null || nome.isEmpty() ||
                idade == null || idade.isEmpty() ||
                curso == null || curso.isEmpty() ||
                notaFinal == null || notaFinal.isEmpty()) {

            return false;
        }

        try {
            Integer.parseInt(idade);
            Float.parseFloat(notaFinal.replace(",", "."));
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
package com.template.validator;

import java.util.ArrayList;
import java.util.List;

public class AlunosValidator implements IAlunosValidator {

    private String mensagemErro;

    @Override
    public boolean validarCampos(
            String nome,
            String idade,
            String curso,
            String notaFinal) {

        mensagemErro = "";

        List<Validador<String>> validadores = new ArrayList<>();

        validadores.add(
                new CampoObrigatorioValidador("Nome", nome)
        );

        validadores.add(
                new CampoObrigatorioValidador("Idade", idade)
        );

        validadores.add(
                new CampoObrigatorioValidador("Curso", curso)
        );

        validadores.add(
                new CampoObrigatorioValidador("Nota Final", notaFinal)
        );

        validadores.add(
                new NomeValidador(nome)
        );

        validadores.add(
                new IdadeValidador(idade)
        );

        validadores.add(
                new NotaValidador(notaFinal)
        );

        for (Validador<String> validador : validadores) {

            if (!validador.validar(validador.getValor())) {

                mensagemErro =
                        validador.getMensagemErro();

                return false;
            }
        }

        return true;
    }

    @Override
    public String getMensagemErro() {
        return mensagemErro;
    }
}
package com.template.validator;

import com.template.util.FormUtil;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class AlunosValidator implements IAlunosValidator {

    @Override
    public boolean validarCampos(
            String nome,
            String idade,
            String curso,
            String notaFinal,
            Label lblMensagem) {

        List<Validador<String>> validadores = new ArrayList<>();

        validadores.add(
                new CampoObrigatorioValidador(
                        nome,
                        "O nome é obrigatório!"
                )
        );

        validadores.add(
                new CampoObrigatorioValidador(
                        idade,
                        "A idade é obrigatória!"
                )
        );

        validadores.add(
                new CampoObrigatorioValidador(
                        curso,
                        "O curso é obrigatório!"
                )
        );

        validadores.add(
                new CampoObrigatorioValidador(
                        notaFinal,
                        "A nota final é obrigatória!"
                )
        );

        validadores.add(
                new NomeValidador(nome)
        );

        validadores.add(
                new IdadeValidador(idade)
        );

        for (Validador<String> validador : validadores) {

            if (!validador.validar(validador.getValor())) {

                FormUtil.atualizarMensagem(
                        lblMensagem,
                        validador.getMensagemErro()
                );

                return false;
            }
        }

        return true;
    }
}
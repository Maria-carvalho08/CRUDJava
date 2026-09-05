package com.template.validator;

import java.util.regex.Pattern;

class NomeValidador implements Validador<String> {

    private static final String NOME_REGEX =
            "^[A-Za-zÀ-ÿ]+( [A-Za-zÀ-ÿ]+)*$";

    private final Pattern pattern = Pattern.compile(NOME_REGEX);
    private final String nome;

    public NomeValidador(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean validar(String valor) {
        return valor != null && pattern.matcher(valor).matches();
    }

    @Override
    public String getMensagemErro() {
        return "Digite um nome válido (somente letras).";
    }

    @Override
    public String getValor() {
        return nome;
    }
}
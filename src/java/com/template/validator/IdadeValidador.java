package com.template.validator;

import java.util.regex.Pattern;

class IdadeValidador implements Validador<String> {
    private static final String IDADE_REGEX = "^\\d+$";
    private final Pattern pattern = Pattern.compile(IDADE_REGEX);
    private final String idade;

    public IdadeValidador(String idade) {
        this.idade = idade;
    }

    @Override
    public boolean validar(String valor) {
        return valor != null && pattern.matcher(valor).matches();
    }

    @Override
    public String getMensagemErro() {
        return "Digite uma idade válida usando apenas números.";
    }

    @Override
    public String getValor() {
        return idade;
    }
}
package com.template.validator;

class NotaValidador implements Validador<String> {

    private final String notaFinal;

    public NotaValidador(String notaFinal) {
        this.notaFinal = notaFinal;
    }

    @Override
    public boolean validar(String valor) {

        try {
            Float.parseFloat(
                    valor.replace(",", ".")
            );

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMensagemErro() {
        return "Digite uma nota válida.";
    }

    @Override
    public String getValor() {
        return notaFinal;
    }
}
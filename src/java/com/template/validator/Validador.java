package com.template.validator;

public interface Validador <T>{
    boolean validar (T valor);//T é um tipo generico permite a interface trabalhar com diferentes tipos de dados
    String getMensagemErro();
    T getValor();
}

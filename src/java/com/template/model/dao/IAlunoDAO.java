package com.template.model.dao;

import com.template.model.dto.AlunoDTO;

import java.util.ArrayList;

public interface IAlunoDAO {

    void inserir(AlunoDTO aluno);

    ArrayList<AlunoDTO> listar();

    void atualizar(AlunoDTO aluno);

    void excluir(int id);
}
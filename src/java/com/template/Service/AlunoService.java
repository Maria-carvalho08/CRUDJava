package com.template.Service;

import com.template.model.dao.AlunoDAO;
import com.template.model.dto.AlunoDTO;

import java.util.ArrayList;

public class AlunoService {

    private AlunoDAO alunoDAO = new AlunoDAO();

    public void inserir(AlunoDTO aluno) {
        alunoDAO.inserir(aluno);
    }

    public void excluir(int id) {
        alunoDAO.excluir(id);
    }

    public void atualizar(AlunoDTO aluno) {
        alunoDAO.atualizar(aluno);
    }

    public ArrayList<AlunoDTO> listar() {
        return alunoDAO.listar();
    }
}
package com.elotech.cadastroCerto.exceptions;

public class CpfAlreadyExistException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CpfAlreadyExistException(String mensagem) {
        super(mensagem);
    }
}

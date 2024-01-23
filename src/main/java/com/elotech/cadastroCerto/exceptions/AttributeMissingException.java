package com.elotech.cadastroCerto.exceptions;

public class AttributeMissingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AttributeMissingException(String mensagem) {
        super(mensagem);
    }

}

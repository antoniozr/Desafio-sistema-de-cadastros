package com.example.desafio_sistema_cadastro.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException {
  public OperacaoNaoPermitidaException(String message) {
    super(message);
  }
}

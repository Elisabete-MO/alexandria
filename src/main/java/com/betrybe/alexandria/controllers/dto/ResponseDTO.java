package com.betrybe.alexandria.controllers.dto;

/** Classe que representa o DTO de resposta.
 *
 * @param message Mensagem de resposta.
 * @param data Dados de resposta.
 * @param <T> Tipo de dado de resposta.
 */
public record ResponseDTO<T>(String message, T data) {
}

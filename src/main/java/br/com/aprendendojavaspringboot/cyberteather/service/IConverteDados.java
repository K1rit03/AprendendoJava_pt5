package br.com.aprendendojavaspringboot.cyberteather.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
package com.Reis.deesafio_uol.domain;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "tb_jogadores")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "codinome")
    private String codinome;
    @Column(name = "arquivoReferencia")
    private String arquivRef;

    //Contructors:


    public Jogador( ) {

    }

    public Jogador(String nome, String email, String telefone, String codinome, String arquivRef) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.codinome = codinome;
        this.arquivRef = arquivRef;
    }


    //Getters and Setters:
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCodinome() {
        return codinome;
    }

    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

    public String getArquivRef() {
        return arquivRef;
    }

    public void setArquivRef(String arquivRef) {
        this.arquivRef = arquivRef;
    }
}

package com.Reis.deesafio_uol.controllers;

import com.Reis.deesafio_uol.domain.Jogador;
import com.Reis.deesafio_uol.domain.dtos.JogadorDto;
import com.Reis.deesafio_uol.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("teste")
public class JogadorController {

    @Autowired
    JogadorService service;

    @PostMapping("/insert")
    public ResponseEntity insertJogador(@RequestBody JogadorDto dto) throws ParserConfigurationException, IOException, SAXException {
        if (dto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO! Todos os campos devem estar preenchidos!");
        }
        service.insertJogador(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário Cadastrado com Sucesso!");
    }

    @GetMapping("/listar")
    public List<Jogador> listAllJogadores(){
       return service.ListAllJogadores();
    }
}

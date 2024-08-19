package com.Reis.deesafio_uol.services;

import com.Reis.deesafio_uol.domain.Jogador;
import com.Reis.deesafio_uol.domain.dtos.JogadorDto;
import com.Reis.deesafio_uol.exceptions.EmptyGroupException;
import com.Reis.deesafio_uol.exceptions.FullGroupException;
import com.Reis.deesafio_uol.repositories.JogadorRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository repository;

//    static ArrayList<String> Vingadores = new ArrayList<>();
//    static ArrayList<String> LigaDaJustica = new ArrayList<>();

    public List<Jogador> ListAllJogadores(){
        List<Jogador> jogadores = repository.findAll();
        return jogadores;
    }

    public ResponseEntity insertJogador(JogadorDto dto) throws ParserConfigurationException, IOException, SAXException {
        String codinome = verificarLista(dto.grupo());
        String arquivRef = verificarArquivo(dto.grupo());
        Jogador jogador = new Jogador(dto.nome(), dto.email(), dto.telefone(), codinome, arquivRef);
        repository.save(jogador);
        return ResponseEntity.ok().build();
    }

    public String verificarLista(String grupo) throws ParserConfigurationException, IOException, SAXException {
        if (grupo.isEmpty()) {
            throw new EmptyGroupException();
        }

        String codinome = "";
        //Se o grupo escolhido foi VINGADORES
        if (grupo.equals("Vingadores")) {
            try {
                // URL do arquivo JSON
                URL url = new URL("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json");

                // Cria um ObjectMapper para processar o JSON
                ObjectMapper objectMapper = new ObjectMapper();

                // Faz o parsing do JSON a partir da URL e retorna um JsonNode
                JsonNode rootNode = objectMapper.readTree(url);

                // Obtém todos os codinomes do JSON (assumindo que a estrutura do JSON tem um array de codinomes)
                JsonNode codinomesNode = rootNode.path("vingadores");

                if (codinomesNode.isArray()) {
                    for (JsonNode codinomeNode : codinomesNode) {
                        String codinomeText = codinomeNode.path("codinome").asText().trim();
                        Jogador result = repository.findByCodinome(codinomeText);
                        if (result == null) {
                            codinome = codinomeText;
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Se o grupo escolhido foi LIGADAJUSTIÇA
        if (grupo.equals("Liga Da Justiça")) {

            try {
                // URL do arquivo XML
                URL url = new URL("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml");

                // Cria uma instância de DocumentBuilderFactory
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

                // Cria um DocumentBuilder
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

                // Faz o parsing do XML a partir da URL e retorna um objeto Document
                Document doc = dBuilder.parse(url.openStream());

                // Normaliza o documento XML
                doc.getDocumentElement().normalize();

                // Obtém todos os elementos <codinome>
                NodeList nodeList = doc.getElementsByTagName("codinome");

                // Itera sobre os nós e processa as informações
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    String codinomeText = element.getTextContent().trim(); // Obtém o texto do codinome e remove espaços extras
                    Jogador result = repository.findByCodinome(codinomeText);
                    if (result == null) {
                        codinome = codinomeText;
                        break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (codinome.equals("")){
            throw new FullGroupException();
        }
        return codinome;
    }

    public String verificarArquivo(String grupo){
        if (grupo.isEmpty()){
            return "aruqivo não encotrado";
        }
        if(grupo.equals("Vingadores")){
            return "vingadores.json";
        } else return "ligadajustica.xml";
    }
}


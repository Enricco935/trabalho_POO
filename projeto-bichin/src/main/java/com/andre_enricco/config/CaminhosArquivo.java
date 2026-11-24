package com.andre_enricco.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class CaminhosArquivo {

    private static final Path DIRETORIO_DADOS = localizarDiretorioDados();

    public static final String CLIENTES = caminho("clientes.txt");
    public static final String PETS = caminho("pets.txt");
    public static final String SERVICOS = caminho("servicos.txt");
    public static final String SERVICOS_PRESTADOS = caminho("servicos_prestados.txt");

    private CaminhosArquivo() {
    }

    private static Path localizarDiretorioDados() {
        Path diretorio = Paths.get(System.getProperty("user.dir")).toAbsolutePath();

        while (diretorio != null && !Files.exists(diretorio.resolve("pom.xml"))) {
            diretorio = diretorio.getParent();
        }

        if (diretorio == null) {
            throw new IllegalStateException("Não foi possível encontrar o pom.xml do projeto.");
        }

        Path pastaDados = diretorio.resolve("dados");
        try {
            Files.createDirectories(pastaDados);
        } catch (IOException erro) {
            throw new IllegalStateException("Não foi possível criar a pasta dados.", erro);
        }

        return pastaDados;
    }

    private static String caminho(String nomeArquivo) {
        return DIRETORIO_DADOS.resolve(nomeArquivo).toString();
    }
}

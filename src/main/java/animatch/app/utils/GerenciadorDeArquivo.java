package animatch.app.utils;

import animatch.app.domain.usuario.Usuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GerenciadorDeArquivo {
    public static void gravaArquivoCsv(ListaObj<Usuario> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try-catch para gravar o arquivo
        try {
            for (int i = 0; i < lista.getTamanho(); i++) {

                //Recupere um elemento da lista e formate aqui:
                Usuario usuario = lista.getElemento(i);
                saida.format("%d;%s;%s;%s;%s;%s;%s;%b\n",
                        usuario.getId(),
                        usuario.getName(),
                        usuario.getEmail(),
                        usuario.getGenero(),
                        usuario.getCriacao(),
                        usuario.getProfileImage(),
                        usuario.getCoverImage(),
                        usuario.isStatus());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leArquivoCsv(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo nao encontrado");
            System.exit(1);
        }

        // Bloco try-catch para ler o arquivo
        try {
            // Print Cabeçalho:
            System.out.printf("%-5S %-40S %-40S %-12S %-25S %23S %23S %10S\n",
                    "id",
                    "nome",
                    "email",
                    "gênero",
                    "data de criação",
                    "imagem de perfil",
                    "imagem de capa",
                    "status");

            while (entrada.hasNext()) {
                //Print Corpo:
                int id = entrada.nextInt();
                String nome = entrada.next();
                String email = entrada.next();
                String genero = entrada.next();
                String imgPerfil = entrada.next();
                String imgCapa = entrada.next();
                LocalDate dataNasc = LocalDate.parse(entrada.next());// irei arrumar aqui ainda
                boolean status = entrada.nextBoolean();


                System.out.printf("%05d %-40s %-40s -12s %-25s %23s %23s %10s\n",
                        id,
                        nome,
                        email,
                        genero,
                        dataNasc,
                        imgPerfil,
                        imgCapa,
                        status ? "Ativo" : "Não ativo");
            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
}

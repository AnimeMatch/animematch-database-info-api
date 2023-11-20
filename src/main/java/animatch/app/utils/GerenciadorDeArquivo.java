package animatch.app.utils;

import animatch.app.domain.usuario.Usuario;
import animatch.app.dto.UsuarioCsvDTO;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GerenciadorDeArquivo {
    public static void gravaArquivoCsv(ListaObj<Usuario> lista, String nomeArq, List<Integer> qtds) {
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
        List<Integer> quantidades = qtds;
        // Bloco try-catch para gravar o arquivo
        try {
            for (int i = 0; i < lista.getTamanho(); i++) {

                Integer qtd = quantidades.get(i);
                //Recupere um elemento da lista e formate aqui:
                Usuario usuario = lista.getElemento(i);
                saida.format("%d;%s;%s;%s;%s;%s;%s;%b;%d\n",
                        usuario.getId(),
                        usuario.getName(),
                        usuario.getEmail(),
                        usuario.getGenero(),
                        usuario.getCriacao(),
                        usuario.getProfileImage(),
                        usuario.getCoverImage(),
                        usuario.isStatus(),
                        qtd);
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
            System.out.printf("%-5S %-40S %-40S %-12S %-25S %23S %23S %10S %10s\n",
                    "id",
                    "nome",
                    "email",
                    "gênero",
                    "data de criação",
                    "imagem de perfil",
                    "imagem de capa",
                    "status",
                    "quantidade");

            while (entrada.hasNext()) {
                //Print Corpo:
                int id = entrada.nextInt();
                String nome = entrada.next();
                String email = entrada.next();
                String genero = entrada.next();
                String imgPerfil = entrada.next();
                String imgCapa = entrada.next();
                String criacao = entrada.next();// irei arrumar aqui ainda
                boolean status = entrada.nextBoolean();
                Integer quantidade = entrada.nextInt();


                System.out.printf("%05d %-40s %-40s -12s %-25s %23s %23s %10s %10d\n",
                        id,
                        nome,
                        email,
                        genero,
                        criacao,
                        imgPerfil,
                        imgCapa,
                        status ? "Ativo" : "Não ativo",
                        quantidade);
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

    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

// Abre o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq,true));
        }
        catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

// Grava o registro e fecha o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }

    public static void gravaArquivoTxt(ListaObj<Usuario> lista, String nomeArq, List<Integer> qtds) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "00USUARIO2023"; //Verificar documento de layout
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01"; // concatenando de acordo com os campos do header do aluno

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Grava os registros de dados (ou registros de corpo)
        for (Usuario usuario : lista) { //#todo
            String corpo = "02";
            corpo += String.format("%05d", usuario.getId()); //Completar de acordo com documento
            corpo += String.format("%-40S", usuario.getName());
            corpo += String.format("%-9S", usuario.getGenero());
            corpo += String.format("%-50S", usuario.getEmail());
            corpo += String.format("%-23S", usuario.getCriacao());
            corpo += String.format("%-25S", usuario.getProfileImage());
            corpo += String.format("%-25S", usuario.getCoverImage());
            corpo += String.format("%3b", usuario.isStatus());
            corpo += String.format("%3d", qtds);

            //Gravando corpo no arquivo:
            gravaRegistro(corpo, nomeArq);
            // Incrementa o contador de registros de dados gravados
            contaRegDados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);

        gravaRegistro(trailer, nomeArq);
    }

    public static void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        int contaRegDadosLidos = 0;
        int qtdRegDadosGravados;

        // Cria uma lista para armazenar os objetos criados com
        // os dados lidos do arquivo txt
        List<Usuario> listaLida = new ArrayList<>();

        // Abre o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

        // Leitura do arquivo
        try {
            registro = entrada.readLine();
            while (registro != null) {
                // obtem os 2 primeiros caracteres do registro lido
                // 1o argumento do substring é o indice do que se quer obter, iniciando de zero
                // 2o argumento do substring é o indice final do que se deseja, MAIS UM

                // 012345
                // 00NOTA
                tipoRegistro = registro.substring(0,2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("É um registro de header");
                    //Exibir informações do header
                    System.out.println("Tipo do Arquivo: " + registro.substring(2, 9));
                    System.out.println("Data e Hora: " + registro.substring(9, 28));
                    System.out.println("Versão do layout: " + registro.substring(28, 30));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("É um registro de trailer");
                    //Exibir quantidade de registros

                }
                else if (tipoRegistro.equals("02")) {
                    System.out.println("É um registro de corpo");
                    Integer id = Integer.valueOf(registro.substring(2, 7).trim());
                    String nome = registro.substring(7, 47).trim();
                    String genero = registro.substring(47, 56).trim();
                    String email = registro.substring(56, 96).trim();
                    String criacao = registro.substring(96, 101).trim();
                    Integer imgPerfil = Integer.valueOf(registro.substring(101, 125).trim());
                    Integer imgCapa = Integer.valueOf(registro.substring(125, 150).trim());
                    boolean status = Boolean.parseBoolean(registro.substring(150, 155).trim());
                    Integer quantidade = Integer.valueOf(registro.substring(155, 157).trim());
                    //Depois de guardar em varável, exiba:
                    System.out.println(String.format("%-5s %-40s %-9s %-40s %-5s %-25s %-25s %-5s %-3s",
                            id,
                            nome,
                            genero,
                            email,
                            criacao,
                            imgPerfil,
                            imgCapa,
                            status,
                            quantidade));
                    //Guardar dados do corpo em variáveis

                    // Incrementa o contador de reg de dados lidos
                    contaRegDadosLidos++;
                    // Cria um objeto com os dados lidos do registro
                    Usuario usuario = new Usuario(
                            id,
                            nome,
                            genero,
                            email,
                            criacao,
                            imgPerfil,
                            imgCapa,
                            status,
                            quantidade);

                    // Se estivesse conectado a um banco de dados,
//                    alunoRepository.save(aluno); #todo salvar usuario no repositorio
                    // como não estamos conectados a um BD, vamos adicionar na lista
                    listaLida.add(usuario);

                }
                else {
                    System.out.println("Registro inválido");
                }
                // Le o proximo registro
                registro = entrada.readLine();
            }  // fim do while
            // Fecha o arquivo
            entrada.close();
        } // fim do try
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }
        // Exibe a lista lida
        System.out.println("\nLista lida do arquivo:");
        for (Usuario usuario : listaLida) {
            System.out.println(usuario);
        }
        // Aqui tb seria possível salvar a lista no BD
        // repository.saveAll(lista); #todo
    }
}

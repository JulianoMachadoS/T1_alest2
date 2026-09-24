package main.java.pennywise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Top10Heap top10 = new Top10Heap();
        boolean rodando = true;

        System.out.println("Digite os números do menu ou os comandos diretos para navegar.\n\n"+"----Bem-vindo, Pennywise!----");

        while (rodando) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 consultar - Atualizar o Top-10 (abre opções)");
            System.out.println("2 mostrar   - Ver os mais covardes");
            System.out.println("3 limpar    - Esvaziar a lista");
            System.out.println("4 ajuda     - Ajuda completa");
            System.out.println("5 sair      - Fechar o programa");
            System.out.print("Pennywise> ");

            String entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) continue;

            String[] partes = entrada.split(" ", 2);
            String comando = partes[0].toLowerCase();

            switch (comando) {
                case "consultar":
                case "1":
                    System.out.println("\nSelecione o arquivo da região:");
                    System.out.println("1 - centro.txt");
                    System.out.println("2 - neibolt.txt");
                    System.out.println("3 - barrens.txt");
                    System.out.println("4 - canal.txt");
                    System.out.println("5 - quarry.txt");
                    System.out.print("Arquivo> ");

                    String opcaoArquivo = scanner.nextLine().trim().toLowerCase();
                    String arquivoSelecionado = "";

                    switch (opcaoArquivo) {
                        case "1":
                        case "centro":
                        case "centro.txt":
                            arquivoSelecionado = "docs/arquovosTexto/centro.txt";
                            break;
                        case "2":
                        case "neibolt":
                        case "neibolt.txt":
                            arquivoSelecionado = "docs/arquovosTexto/neibolt.txt";
                            break;
                        case "3":
                        case "barrens":
                        case "barrens.txt":
                            arquivoSelecionado = "docs/arquovosTexto/barrens.txt";
                            break;
                        case "4":
                        case "canal":
                        case "canal.txt":
                            arquivoSelecionado = "docs/arquovosTexto/canal.txt";
                            break;
                        case "5":
                        case "quarry":
                        case "quarry.txt":
                            arquivoSelecionado = "docs/arquovosTexto/quarry.txt";
                            break;
                        default:
                            System.out.println("Opção de arquivo inválida.");
                            break;
                    }

                    if (!arquivoSelecionado.isEmpty()) {
                        processarArquivo(arquivoSelecionado, top10);
                    }
                    break;

                case "mostrar":
                case "2":
                    mostrarTop10(top10);
                    break;

                case "limpar":
                case "3":
                    top10.limpar();
                    System.out.println("Lista esvaziada.");
                    break;

                case "ajuda":
                case "4":
                    System.out.println("Digite os números do menu ou os comandos diretos para navegar.");
                    break;

                case "sair":
                case "5":
                    rodando = false;
                    break;

                default:
                    System.out.println("Comando inválido. Tente novamente.");
                    break;
            }
        }
    }

    private static void processarArquivo(String nomeArquivo, Top10Heap top10) {
        Path path = Paths.get(nomeArquivo);
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] dados = linha.split(" ");
                if (dados.length == 2) {
                    try {
                        String nome = dados[0];
                        int escore = Integer.parseInt(dados[1]);
                        top10.inserir(new Crianca(nome, escore));
                    } catch (NumberFormatException ignored) {}
                }
            }
            System.out.println("Região " + nomeArquivo + " lida. Crianças no Top-10: " + top10.tamanho());
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado ou erro de leitura: " + nomeArquivo);
        }
    }

    private static void mostrarTop10(Top10Heap top10) {
        if (top10.tamanho() == 0) {
            System.out.println("Nenhuma criança na lista.");
            return;
        }
        List<Crianca> ordenadas = top10.getTop10Ordenado();
        for (int i = 0; i < ordenadas.size(); i++) {
            Crianca c = ordenadas.get(i);
            System.out.printf("%d. %s %d\n", (i + 1), c.getNome(), c.getEscore());
        }
    }
}
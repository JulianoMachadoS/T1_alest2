//ALUNO: JULIANO MACHADO DA SILVA
// MATRICULA: 25108646-8

package main.java.pennywise;

import java.util.ArrayList;
import java.util.List;

public class Top10Heap {
    // Representação do Heap em array (índice 0 não é utilizado)[cite: 4]
    private Crianca[] pq;
    private int n; // Quantidade atual de elementos na fila
    private static final int CAPACIDADE = 10;

    public Top10Heap() {
        // Aloca capacidade + 1 porque os índices começam em 1[cite: 4]
        pq = new Crianca[CAPACIDADE + 1];
        n = 0;
    }

    public void inserir(Crianca nova) {
        if (n < CAPACIDADE) {
            // Adiciona no final e reordena flutuando o nó para cima[cite: 4]
            pq[++n] = nova;
            swim(n);
        } else {
            // Se já temos 10 elementos, avaliamos o topo do Max-Heap (a menos covarde)
            Crianca max = pq[1];

            // Só substitui a raiz se a nova criança for MAIS covarde (escore menor)
            // ou se houver empate de escore, mas o nome for lexicograficamente menor
            if (nova.getEscore() < max.getEscore() ||
                    (nova.getEscore() == max.getEscore() && nova.getNome().compareTo(max.getNome()) < 0)) {

                // Substitui a raiz pela nova criança e afunda o nó para manter a ordem[cite: 4]
                pq[1] = nova;
                sink(1);
            }
        }
    }

    public void limpar() {
        // Limpa as referências para evitar lixo de memória e zera o contador
        for (int i = 1; i <= n; i++) {
            pq[i] = null;
        }
        n = 0;
    }

    public List<Crianca> getTop10Ordenado() {
        // Extrai os elementos do Heap para um array temporário com tamanho exato (0-indexed)
        Crianca[] array = new Crianca[n];
        for (int i = 0; i < n; i++) {
            array[i] = pq[i + 1];
        }

        // Nosso próprio algoritmo de ordenação: Insertion Sort
        // Utiliza o compareTo para isolar a lógica de comparação[cite: 5]
        for (int i = 1; i < array.length; i++) {
            Crianca chave = array[i];
            int j = i - 1;

            // Move os elementos maiores para a direita
            while (j >= 0 && array[j].compareTo(chave) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = chave;
        }

        // Converte o array ordenado de volta para uma Lista
        List<Crianca> listaOrdenada = new ArrayList<>();
        for (Crianca c : array) {
            listaOrdenada.add(c);
        }

        return listaOrdenada;
    }

    public int tamanho() {
        return n;
    }

    // --- Operações internas do Heap Binário ---

    // Promoção: Move um nó recém-inserido para cima caso seja maior que o pai[cite: 4]
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    // Demoção: Afunda um nó substituído caso seja menor que seus filhos[cite: 4]
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) j++; // Seleciona o maior filho[cite: 4]
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    // Compara dois nós do heap. Como queremos um Max-Heap com desempate alfabético invertido:
    private boolean less(int i, int j) {
        if (pq[i].getEscore() == pq[j].getEscore()) {
            return pq[i].getNome().compareTo(pq[j].getNome()) < 0;
        }
        return pq[i].getEscore() < pq[j].getEscore();
    }

    // Método auxiliar para trocar a posição de dois elementos no array[cite: 4]
    private void exch(int i, int j) {
        Crianca t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
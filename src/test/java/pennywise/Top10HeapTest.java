//ALUNO: JULIANO MACHADO DA SILVA
// MATRICULA: 25108646-8

package test.java.pennywise;

import main.java.pennywise.Crianca;
import main.java.pennywise.Top10Heap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Top10HeapTest {

    private Top10Heap top10;

    @BeforeEach
    void setUp() {
        top10 = new Top10Heap();
    }

    @Test
    void testOnPoint() {
        // On Point: Exatamente o limite permitido (10 itens)
        for (int i = 1; i <= 10; i++) {
            top10.inserir(new Crianca("Crianca_" + i, 100 - i));
        }
        assertEquals(10, top10.tamanho(), "On-point: O tamanho deve ser exatamente 10.");
    }

    @Test
    void testOffPoint() {
        // Off Point: Inserimos 11 itens. A fila deve ejetar o menos covarde.
        for (int i = 1; i <= 10; i++) {
            top10.inserir(new Crianca("Crianca_" + i, 50));
        }

        // Criança número 11, nota muito baixa (muito covarde), deve entrar no top 10
        top10.inserir(new Crianca("Super_Covarde", 5));

        assertEquals(10, top10.tamanho(), "Off-point: O tamanho não pode passar de 10.");

        boolean entrouNaLista = top10.getTop10Ordenado().stream()
                .anyMatch(c -> c.getNome().equals("Super_Covarde"));

        assertTrue(entrouNaLista, "Off-point: O elemento novo deveria ter entrado no lugar do maior escore.");
    }

    @Test
    void testOutPoint() {
        // Out Point / In Point: Situações de funcionamento antes do limite de stress
        assertEquals(0, top10.tamanho(), "Out-point: Estrutura inicial deve estar vazia.");

        top10.inserir(new Crianca("Isolada", 200));
        assertEquals(1, top10.tamanho(), "Out-point: O tamanho deve ser 1 após uma inserção livre.");
    }
}
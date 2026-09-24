//ALUNO: JULIANO MACHADO DA SILVA
// MATRICULA: 25108646-8

package main.java.pennywise;

public class Crianca implements Comparable<Crianca> {
    private String nome;
    private int escore;

    public Crianca(String nome, int escore) {
        this.nome = nome;
        this.escore = escore;
    }

    public String getNome() { return nome; }
    public int getEscore() { return escore; }

    @Override
    public int compareTo(Crianca outra) {
        if (this.escore == outra.escore) {
            return this.nome.compareTo(outra.nome);
        }
        return Integer.compare(this.escore, outra.escore);
    }
}
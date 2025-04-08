package src;

import java.time.LocalDate;

public class Transacao {
    public enum Tipo {
        RECEITA, DESPESA
    }

    private Tipo tipo;
    private double valor;
    private String categoria;
    private LocalDate data;
    private String descricao;

    public Transacao(Tipo tipo, double valor, String categoria, LocalDate data, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
        this.descricao = descricao;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }
}

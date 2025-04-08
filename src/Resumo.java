package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Resumo extends JPanel {

    private JLabel labelSaldo;
    private JLabel labelReceitas;
    private JLabel labelDespesas;

    public Resumo() {
        setLayout(new GridLayout(3, 1, 10, 10));

        labelSaldo = new JLabel();
        labelReceitas = new JLabel();
        labelDespesas = new JLabel();

        labelSaldo.setFont(new Font("Arial", Font.BOLD, 18));
        labelReceitas.setFont(new Font("Arial", Font.PLAIN, 16));
        labelDespesas.setFont(new Font("Arial", Font.PLAIN, 16));

        add(labelSaldo);
        add(labelReceitas);
        add(labelDespesas);

        atualizarResumo();
    }

    public void atualizarResumo() {
        List<Transacao> transacoes = TransacaoManager.listar();

        double totalReceitas = 0;
        double totalDespesas = 0;

        for (Transacao t : transacoes) {
            if (t.getTipo() == Transacao.Tipo.RECEITA) {
                totalReceitas += t.getValor();
            } else {
                totalDespesas += t.getValor();
            }
        }

        double saldo = totalReceitas - totalDespesas;

        labelSaldo.setText("Saldo: R$ " + String.format("%.2f", saldo));
        labelReceitas.setText("Total de Receitas: R$ " + String.format("%.2f", totalReceitas));
        labelDespesas.setText("Total de Despesas: R$ " + String.format("%.2f", totalDespesas));
    }
}

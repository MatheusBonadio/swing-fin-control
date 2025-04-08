package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {
    private CardLayout cardLayout;
    private JPanel painelCentral;

    public Principal() {
        setTitle("Sistema de Gestão Financeira Pessoal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem resumoItem = new JMenuItem("Resumo Financeiro");
        JMenuItem cadastrarItem = new JMenuItem("Cadastrar Transação");
        JMenuItem historicoItem = new JMenuItem("Histórico");
        JMenuItem categoriasItem = new JMenuItem("Categorias");
        JMenuItem sairItem = new JMenuItem("Sair");

        menu.add(resumoItem);
        menu.add(cadastrarItem);
        menu.add(historicoItem);
        menu.add(categoriasItem);
        menu.addSeparator();
        menu.add(sairItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        cardLayout = new CardLayout();
        painelCentral = new JPanel(cardLayout);

        Resumo resumo = new Resumo();
        CadastroTransacao cadastro = new CadastroTransacao(resumo);

        painelCentral.add(resumo, "resumo");
        painelCentral.add(cadastro, "cadastrar");
        painelCentral.add(new Historico(), "historico");
        painelCentral.add(new Categorias(), "categorias");

        add(painelCentral, BorderLayout.CENTER);

        resumoItem.addActionListener(e -> {
            resumo.atualizarResumo();
            cardLayout.show(painelCentral, "resumo");
        });

        cadastrarItem.addActionListener(e -> cardLayout.show(painelCentral, "cadastrar"));
        historicoItem.addActionListener(e -> cardLayout.show(painelCentral, "historico"));
        categoriasItem.addActionListener(e -> cardLayout.show(painelCentral, "categorias"));
        sairItem.addActionListener(this::sair);

        setVisible(true);
    }

    private void sair(ActionEvent e) {
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            dispose();
            new Login();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Principal::new);
    }
}

package src.screen;

import src.model.User;
import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    public MainScreen(User user) {
        setTitle("Sistema de Gestão Financeira Pessoal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);  // Tamanho ampliado para acomodar melhor os componentes
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuOpcoes = new JMenu("Opções");
        JMenuItem menuCriarTransacao = new JMenuItem("Criar Transação");
        JMenuItem menuGerenciarCategorias = new JMenuItem("Gerenciar Categorias");
        menuOpcoes.add(menuCriarTransacao);
        menuOpcoes.add(menuGerenciarCategorias);
        menuBar.add(menuOpcoes);
        setJMenuBar(menuBar);

        TransactionHistoryPanel historyPanel = new TransactionHistoryPanel(user);
        add(historyPanel, BorderLayout.CENTER);

        menuCriarTransacao.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "Criar Transação", true);
            CreateTransaction createTransactionPanel = new CreateTransaction(historyPanel, user);
            dialog.getContentPane().add(createTransactionPanel);
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        });

        menuGerenciarCategorias.addActionListener(e -> new CategoryManagementFrame(user));

        setVisible(true);
    }
}

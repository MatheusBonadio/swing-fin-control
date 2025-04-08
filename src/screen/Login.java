package src.screen;

import javax.swing.*;
import java.awt.*;

public class Login {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 180);
        frame.setLocationRelativeTo(null);

        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JLabel usuarioLabel = new JLabel("Usuário:");
        JTextField usuarioCampo = new JTextField(20);
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaCampo = new JPasswordField(15);

        formPanel.add(usuarioLabel);
        formPanel.add(usuarioCampo);
        formPanel.add(senhaLabel);
        formPanel.add(senhaCampo);

        frame.add(formPanel, BorderLayout.CENTER);

        JButton loginBotao = new JButton("Login");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginBotao);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

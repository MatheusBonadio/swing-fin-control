package src;

import src.model.UserManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationFrame extends JFrame {
    public RegistrationFrame() {
        setTitle("Cadastro de Usuário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        JLabel userLabel = new JLabel("Usuário:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Senha:");
        JPasswordField passField = new JPasswordField();
        JLabel confirmPassLabel = new JLabel("Confirmar Senha:");
        JPasswordField confirmPassField = new JPasswordField();

        JButton registerButton = new JButton("Registrar");

        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(confirmPassLabel);
        add(confirmPassField);
        add(new JLabel());
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                String confirmPassword = new String(confirmPassField.getPassword());

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "As senhas não conferem!");
                    return;
                }
                if (UserManager.registerUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário já existe!");
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

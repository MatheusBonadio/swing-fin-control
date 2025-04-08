package src;

import src.model.UserManager;
import src.model.User;
import src.screen.MainScreen;
import src.screen.RegistrationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        JLabel usuarioLabel = new JLabel("Usuário:");
        JTextField usuarioCampo = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaCampo = new JPasswordField();

        JButton loginBotao = new JButton("Login");
        JButton registrarBotao = new JButton("Registrar");

        add(usuarioLabel);
        add(usuarioCampo);
        add(senhaLabel);
        add(senhaCampo);
        add(new JLabel());
        add(loginBotao);
        add(new JLabel());
        add(registrarBotao);

        loginBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuarioCampo.getText();
                String password = new String(senhaCampo.getPassword());
                if (UserManager.authenticateUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                    User user = UserManager.getUser(username);
                    new MainScreen(user);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
                }
            }
        });

        registrarBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationFrame();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        UserManager.registerUser("admin", "admin");
        new Login();
    }
}

package src;

import javax.swing.*;
import java.awt.*;


public class Login {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 2));


        JLabel usuarioLabel = new JLabel("Usuário:");
        JTextField usuarioCampo = new JTextField(20);
        JLabel senhaLabel = new JLabel("Senha:");
        JTextField senhaCampo = new JTextField(15);


        JButton loginBotao = new JButton("Login");


        frame.add(usuarioLabel);
        frame.add(usuarioCampo);
        frame.add(senhaLabel);
        frame.add(senhaCampo);
        frame.add(new JLabel());
        frame.add(loginBotao);
        frame.setVisible(true);
    }
}
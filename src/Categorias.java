package src;

import javax.swing.*;
import java.awt.*;

public class Categorias extends JPanel {
    private DefaultListModel<String> categoriaModel;
    private JList<String> listaCategorias;
    private JTextField campoCategoria;
    private JButton adicionarBtn, editarBtn, excluirBtn;

    public Categorias() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Gerenciamento de Categorias", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        categoriaModel = new DefaultListModel<>();
        atualizarLista();

        listaCategorias = new JList<>(categoriaModel);
        add(new JScrollPane(listaCategorias), BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new GridLayout(2, 1, 5, 5));

        campoCategoria = new JTextField();
        painelInferior.add(campoCategoria);

        JPanel botoesPanel = new JPanel(new FlowLayout());

        adicionarBtn = new JButton("Adicionar");
        editarBtn = new JButton("Editar");
        excluirBtn = new JButton("Excluir");

        botoesPanel.add(adicionarBtn);
        botoesPanel.add(editarBtn);
        botoesPanel.add(excluirBtn);
        painelInferior.add(botoesPanel);

        add(painelInferior, BorderLayout.SOUTH);

        adicionarBtn.addActionListener(e -> adicionarCategoria());
        editarBtn.addActionListener(e -> editarCategoria());
        excluirBtn.addActionListener(e -> excluirCategoria());
    }

    private void adicionarCategoria() {
        String nova = campoCategoria.getText().trim();
        if (!nova.isEmpty()) {
            CategoriaManager.adicionar(nova);
            campoCategoria.setText("");
            atualizarLista();
        } else {
            JOptionPane.showMessageDialog(this, "Informe o nome da categoria");
        }
    }

    private void editarCategoria() {
        String selecionada = listaCategorias.getSelectedValue();
        String nova = campoCategoria.getText().trim();
        if (selecionada != null && !nova.isEmpty()) {
            CategoriaManager.editar(selecionada, nova);
            campoCategoria.setText("");
            atualizarLista();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria e informe o novo nome");
        }
    }

    private void excluirCategoria() {
        String selecionada = listaCategorias.getSelectedValue();
        if (selecionada != null) {
            CategoriaManager.excluir(selecionada);
            campoCategoria.setText("");
            atualizarLista();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria para excluir.");
        }
    }

    private void atualizarLista() {
        categoriaModel.clear();
        for (String cat : CategoriaManager.getCategorias()) {
            categoriaModel.addElement(cat);
        }
    }
}

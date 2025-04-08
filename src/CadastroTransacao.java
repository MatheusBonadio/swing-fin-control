package src;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CadastroTransacao extends JPanel {

    private JComboBox<String> tipoCombo;
    private JComboBox<String> categoriaCombo;
    private JTextField valorCampo;
    private JTextField dataCampo;
    private JTextField descricaoCampo;
    private JButton salvarBotao;
    private Resumo painelResumo;

    public CadastroTransacao(Resumo painelResumo) {
        this.painelResumo = painelResumo;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Cadastro de Transação", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        tipoCombo = new JComboBox<>(new String[]{"Receita", "Despesa"});
        categoriaCombo = new JComboBox<>();
        atualizarCategorias();
        CategoriaManager.adicionarOuvinte(this::atualizarCategorias);
        valorCampo = new JTextField();
        dataCampo = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        descricaoCampo = new JTextField();

        formPanel.add(new JLabel("Tipo:"));
        formPanel.add(tipoCombo);
        formPanel.add(new JLabel("Categoria:"));
        formPanel.add(categoriaCombo);
        formPanel.add(new JLabel("Valor:"));
        formPanel.add(valorCampo);
        formPanel.add(new JLabel("Data:"));
        formPanel.add(dataCampo);
        formPanel.add(new JLabel("Descrição:"));
        formPanel.add(descricaoCampo);

        add(formPanel, BorderLayout.CENTER);

        salvarBotao = new JButton("Salvar Transação");
        JPanel botaoPanel = new JPanel();
        botaoPanel.add(salvarBotao);
        add(botaoPanel, BorderLayout.SOUTH);

        salvarBotao.addActionListener(e -> salvarTransacao());
    }

    private void atualizarCategorias() {
        categoriaCombo.removeAllItems();
        for (String cat : CategoriaManager.getCategorias()) {
            categoriaCombo.addItem(cat);
        }
    }

    private void salvarTransacao() {
        String tipo = (String) tipoCombo.getSelectedItem();
        String categoria = (String) categoriaCombo.getSelectedItem();
        String valorTexto = valorCampo.getText().trim();
        String dataTexto = dataCampo.getText().trim();
        String descricao = descricaoCampo.getText().trim();

        if (valorTexto.isEmpty() || dataTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha um valor e data!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double valor = Double.parseDouble(valorTexto);
            LocalDate data = LocalDate.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Transacao.Tipo tipoEnum = tipo.equals("Receita") ? Transacao.Tipo.RECEITA : Transacao.Tipo.DESPESA;

            Transacao novaTransacao = new Transacao(tipoEnum, valor, categoria, data, descricao);
            TransacaoManager.adicionar(novaTransacao);
            painelResumo.atualizarResumo();

            JOptionPane.showMessageDialog(this, "Transação salva com sucesso!");

            valorCampo.setText("");
            descricaoCampo.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar transação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

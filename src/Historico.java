package src;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Historico extends JPanel {
    private JComboBox<String> filtroTipo;
    private JComboBox<String> filtroCategoria;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaTransacoes;
    private JButton aplicarFiltroBtn;

    public Historico() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Histórico de Transações", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        JPanel painelFiltros = new JPanel(new FlowLayout());

        filtroTipo = new JComboBox<>(new String[]{"Todas", "Receita", "Despesa"});
        filtroCategoria = new JComboBox<>();
        filtroCategoria.addItem("Todas");
        for (String cat : CategoriaManager.getCategorias()) {
            filtroCategoria.addItem(cat);
        }

        aplicarFiltroBtn = new JButton("Aplicar Filtros");
        aplicarFiltroBtn.addActionListener(e -> atualizarLista());

        painelFiltros.add(new JLabel("Tipo:"));
        painelFiltros.add(filtroTipo);
        painelFiltros.add(new JLabel("Categoria:"));
        painelFiltros.add(filtroCategoria);
        painelFiltros.add(aplicarFiltroBtn);

        add(painelFiltros, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        listaTransacoes = new JList<>(modeloLista);
        add(new JScrollPane(listaTransacoes), BorderLayout.CENTER);

        atualizarLista();
    }

    private void atualizarLista() {
        modeloLista.clear();

        String tipoSelecionado = (String) filtroTipo.getSelectedItem();
        String categoriaSelecionada = (String) filtroCategoria.getSelectedItem();

        List<Transacao> filtradas = TransacaoManager.getTransacoes().stream()
                .filter(t -> tipoSelecionado.equals("Todas") || t.getTipo().name().equalsIgnoreCase(tipoSelecionado))
                .filter(t -> categoriaSelecionada.equals("Todas") || t.getCategoria().equals(categoriaSelecionada))
                .collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Transacao t : filtradas) {
            String linha = String.format("[%s] %s - R$%.2f (%s)",
                    t.getData().format(formatter),
                    t.getDescricao(),
                    t.getValor(),
                    t.getCategoria());
            modeloLista.addElement(linha);
        }
    }
}

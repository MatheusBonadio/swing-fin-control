package src.screen;

import src.model.Category;
import src.model.User;
import javax.swing.*;
import java.awt.*;

public class CategoryManagementFrame extends JFrame {
    private User user;
    private DefaultListModel<String> categoryListModel;
    private JList<String> categoryList;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    public CategoryManagementFrame(User user) {
        this.user = user;
        setTitle("Gerenciamento de Categorias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {
        categoryListModel = new DefaultListModel<>();
        updateCategoryListModel();

        categoryList = new JList<>(categoryListModel);
        JScrollPane scrollPane = new JScrollPane(categoryList);

        addButton = new JButton("Adicionar");
        editButton = new JButton("Editar");
        deleteButton = new JButton("Excluir");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        setLayout(new BorderLayout(10, 10));
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addCategory());
        editButton.addActionListener(e -> editCategory());
        deleteButton.addActionListener(e -> deleteCategory());
    }

    private void updateCategoryListModel() {
        categoryListModel.clear();
        for (Category cat : user.getCategories()) {
            categoryListModel.addElement(cat.toString());
        }
    }

    private void addCategory() {
        String newCategoryName = JOptionPane.showInputDialog(this, "Digite o nome da nova categoria:");
        if (newCategoryName != null && !newCategoryName.trim().isEmpty()) {
            String[] options = {"Receita", "Despesa"};
            String type = (String) JOptionPane.showInputDialog(this, "Selecione o tipo da categoria:", "Tipo de Categoria", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (type != null && !type.trim().isEmpty()) {
                user.addCategory(new Category(newCategoryName.trim(), type));
                updateCategoryListModel();
                TransactionHistoryPanel.refreshCurrentCategoryFilter();
            }
        }
    }

    private void editCategory() {
        int selectedIndex = categoryList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria para editar.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Category currentCategory = user.getCategories().get(selectedIndex);
        String newName = JOptionPane.showInputDialog(this, "Editar categoria:", currentCategory.getName());
        if (newName != null && !newName.trim().isEmpty()) {
            String[] options = {"Receita", "Despesa"};
            String newType = (String) JOptionPane.showInputDialog(this, "Selecione o novo tipo da categoria:", "Tipo de Categoria", JOptionPane.QUESTION_MESSAGE, null, options, currentCategory.getType());
            if (newType != null && !newType.trim().isEmpty()) {
                currentCategory.setName(newName.trim());
                currentCategory.setType(newType);
                updateCategoryListModel();
                TransactionHistoryPanel.refreshCurrentCategoryFilter();
            }
        }
    }

    private void deleteCategory() {
        int selectedIndex = categoryList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria para excluir.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir a categoria selecionada?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            user.getCategories().remove(selectedIndex);
            updateCategoryListModel();
            TransactionHistoryPanel.refreshCurrentCategoryFilter();
        }
    }
}

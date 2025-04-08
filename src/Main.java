package src;

import src.model.Category;
import src.model.Transaction;
import src.model.TransactionType;
import src.model.User;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user = new User("ana", "senha123");

        user.addCategory(new Category("Alimentação"));
        user.addCategory(new Category("Salário"));
        user.addCategory(new Category("Transporte"));


        user.addTransaction(new Transaction(
                2500.00,
                "Salário",
                LocalDate.of(2025, 4, 1),
                "Salário mensal",
                TransactionType.RECEITA
        ));

        user.addTransaction(new Transaction(
                60.00,
                "Alimentação",
                LocalDate.of(2025, 4, 2),
                "Mercado",
                TransactionType.DESPESA
        ));

        user.addTransaction(new Transaction(
                15.50,
                "Transporte",
                LocalDate.of(2025, 4, 3),
                "Ônibus",
                TransactionType.DESPESA
        ));

        user.addTransaction(new Transaction(
                200.00,
                "Freelancer",
                LocalDate.of(2025, 4, 5),
                "Projeto de design",
                TransactionType.RECEITA
        ));

        System.out.println("=== Histórico de Transações ===");
        for (Transaction t : user.transactions) {
            System.out.println(t.date + " | " + t.type + " | R$" + t.value + " | " + t.category + " | " + t.description);
        }

        double totalReceitas = calcularTotalPorTipo(user.transactions, TransactionType.RECEITA);
        double totalDespesas = calcularTotalPorTipo(user.transactions, TransactionType.DESPESA);
        double saldo = totalReceitas - totalDespesas;

        System.out.println("\n=== Resumo Financeiro ===");
        System.out.printf("Total de Receitas: R$ %.2f%n", totalReceitas);
        System.out.printf("Total de Despesas: R$ %.2f%n", totalDespesas);
        System.out.printf("Saldo Atual: R$ %.2f%n", saldo);
    }

    public static double calcularTotalPorTipo(List<Transaction> transacoes, TransactionType tipo) {
        double total = 0;
        for (Transaction t : transacoes) {
            if (t.type == tipo) {
                total += t.value;
            }
        }
        return total;
    }
}


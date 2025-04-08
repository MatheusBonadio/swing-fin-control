package src;

import java.util.ArrayList;

public class CategoriaManager {
    private static final ArrayList<String> categorias = new ArrayList<>();
    private static final ArrayList<Runnable> ouvintes = new ArrayList<>();

    static {
        categorias.add("Salário");
        categorias.add("Alimentação");
        categorias.add("Transporte");
        categorias.add("Lazer");
        categorias.add("Outros");
    }

    public static ArrayList<String> getCategorias() {
        return new ArrayList<>(categorias);
    }

    public static void adicionar(String cat) {
        if (!categorias.contains(cat)) {
            categorias.add(cat);
            notificar();
        }
    }

    public static void editar(String antiga, String nova) {
        int index = categorias.indexOf(antiga);
        if (index != -1 && !categorias.contains(nova)) {
            categorias.set(index, nova);
            notificar();
        }
    }

    public static void excluir(String cat) {
        if (categorias.remove(cat)) {
            notificar();
        }
    }

    public static void adicionarOuvinte(Runnable r) {
        ouvintes.add(r);
    }

    private static void notificar() {
        for (Runnable r : ouvintes) {
            r.run();
        }
    }
}

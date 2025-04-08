package src;

import java.util.ArrayList;
import java.util.List;

public class TransacaoManager {
    private static final List<Transacao> transacoes = new ArrayList<>();

    public static void adicionar(Transacao t) {
        transacoes.add(t);
    }

    public static List<Transacao> getTransacoes() {
        return transacoes;
    }

    public static void limpar() {
        transacoes.clear();
    }

    public static List<Transacao> listar() {
        return transacoes;
    }

}

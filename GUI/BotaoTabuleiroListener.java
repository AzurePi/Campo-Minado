package GUI;

import java.util.EventListener;

public interface BotaoTabuleiroListener extends EventListener {
    void botaoClicado(int linha, int coluna);
}

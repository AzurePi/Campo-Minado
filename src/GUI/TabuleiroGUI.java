package src.GUI;

import src.campoMinado.Quadrado;
import src.campoMinado.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class TabuleiroGUI extends JPanel {
    private static Tabuleiro t;
    private static NossoBotao[][] botoes;
    private static Timer cronometro;
    private static boolean cronometroIniciado;
    private static JLabel labelCronometro;
    private static ImageIcon iconeBandeira;
    private static ImageIcon iconeBomba;
    private static int cliques;

    //Construtor -------------------------------------------------------------------------------------------------------
    public TabuleiroGUI() {
        URL bandeiraURL = getClass().getResource("GUI/assets/noun-flag-5786229.png");
        URL bombaURL = getClass().getResource("GUI/assets/noun-bomb-238911.png");

        if (bandeiraURL != null)
            iconeBandeira = new ImageIcon(bandeiraURL);
        else
            iconeBandeira = new ImageIcon("GUI/assets/noun-flag-5786229.png");

        if (bombaURL != null)
            iconeBomba = new ImageIcon(bombaURL);
        else
            iconeBomba = new ImageIcon("GUI/assets/noun-bomb-238911.png");

        cliques = 0;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public static Tabuleiro getT() {
        return t;
    }

    public static void setT(Tabuleiro t) {
        TabuleiroGUI.t = t;
    }

    public static NossoBotao[][] getBotoes() {
        return botoes;
    }

    public static void setBotoes(NossoBotao[][] botoes) {
        TabuleiroGUI.botoes = botoes;
    }

    public static Timer getCronometro() {
        return cronometro;
    }

    public static void setCronometro(Timer cronometro) {
        TabuleiroGUI.cronometro = cronometro;
    }

    public static boolean isCronometroIniciado() {
        return cronometroIniciado;
    }

    public static void setCronometroIniciado(boolean cronometroIniciado) {
        TabuleiroGUI.cronometroIniciado = cronometroIniciado;
    }

    public static JLabel getLabelCronometro() {
        return labelCronometro;
    }

    public static void setLabelCronometro(JLabel labelCronometro) {
        TabuleiroGUI.labelCronometro = labelCronometro;
    }

    public static ImageIcon getIconeBandeira() {
        return iconeBandeira;
    }

    public static void setIconeBandeira(ImageIcon iconeBandeira) {
        TabuleiroGUI.iconeBandeira = iconeBandeira;
    }

    public static ImageIcon getIconeBomba() {
        return iconeBomba;
    }

    public static void setIconeBomba(ImageIcon iconeBomba) {
        TabuleiroGUI.iconeBomba = iconeBomba;
    }

    public static int getCliques() {
        return cliques;
    }

    public static void setCliques(int cliques) {
        TabuleiroGUI.cliques = cliques;
    }

    //Subclasses --------------------------------------------------------------------------------------------------------
    private static class ListenerJogo implements MouseListener {
        int tamanho = t.getTamanho();

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!cronometroIniciado) {
                iniciarCronometro();
                cronometroIniciado = true;
            }
            int button = e.getButton();
            NossoBotao source = (NossoBotao) e.getSource();

            //botão esquerdo -> revela o conteúdo
            //botão direito -> coloca uma bandeira
            if (button == 1) {
                cliques++;

                int i = source.getI();
                int j = source.getJ();

                Quadrado<?> quadrado = t.getBoard().get(i).get(j);
                int conteudo = quadrado.getConteudo().revelar();

                if (conteudo == -1) {
                    abrirBombas();
                    cronometro.stop();
                    //mensagem de perda
                    //retorna à tela inicial
                } else if (conteudo == 0)
                    abrirVazio(i, j);
                else
                    abrirNumerado(i, j);

            } else if (button == 3 && !source.isFoiClickado()) {
                //se não há bandeira, desativamos o botão
                //se há bandeira, reativamos o botão
                source.setEnabled(!source.isEnabled());
                source.setDisabledIcon(iconeBandeira);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        private void abrirBombas() {
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    int conteudo = t.getBoard().get(i).get(j).getConteudo().revelar();
                    if (conteudo == -1) {
                        botoes[i][j].setEnabled(false);
                        botoes[i][j].setDisabledIcon(iconeBomba);
                    }
                }
            }
        }

        public void abrirVazio(int linha, int coluna) {
            NossoBotao botao = botoes[linha][coluna];
            if (botao.isEnabled())
                botao.setEnabled(false);
            botao.setFoiClickado(true);

            for (int i = linha - 1; i <= linha + 1; i++) {
                for (int j = coluna - 1; j <= coluna + 1; j++) {
                    if (Tabuleiro.coordenadaValida(i, j, t.getTamanho())) {
                        int conteudo = t.getBoard().get(i).get(j).getConteudo().revelar();
                        if (conteudo == 0 && botoes[i][j].isEnabled())
                            abrirVazio(i, j);
                        else if (conteudo != -1)
                            abrirNumerado(i, j);
                    }
                }
            }
        }

        private void abrirNumerado(int linha, int coluna) {
            NossoBotao numerado = botoes[linha][coluna];
            numerado.setEnabled(false);
            numerado.setFoiClickado(true);

            int numero = t.getBoard().get(linha).get(coluna).getConteudo().revelar();
            numerado.setText(String.valueOf(numero));
        }
    }

    //Métodos ----------------------------------------------------------------------------------------------------------
    public void inicializar(int tamanho) {
        t = new Tabuleiro(tamanho);

        this.setLayout(new GridLayout(tamanho + 1, tamanho, 2, 2));

        for (int i = 0; i < tamanho / 2; i++)
            this.add(new JLabel()); //preenche com fillers
        labelCronometro = new JLabel("0 : 0");
        labelCronometro.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(labelCronometro);
        for (int i = 0; i < tamanho / 2; i++)
            this.add(new JLabel()); //preenche com fillers


        botoes = new NossoBotao[tamanho][tamanho];
        ListenerJogo listener = new ListenerJogo();

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                NossoBotao botao = new NossoBotao(i, j);

                /*      Teste para verificar a disposição das coisas no tabuleiro
                botao.setEnabled(false);
                botao.setFoiClickado(true);
                if (t.getBoard().get(i).get(j).getConteudo().revelar() == 0) {
                    botao.setText("vazio");
                } else if (t.getBoard().get(i).get(j).getConteudo().revelar() == -1) {
                    botao.setDisabledIcon(iconeBomba);
                    botao.setText("bomba");
                } else {
                    int numero = t.getBoard().get(i).get(i).getConteudo().revelar();
                    botao.setText(String.valueOf(numero));
                }
                */

                botao.addMouseListener(listener);
                botoes[i][j] = botao;
                this.add(botao);
            }
        }
    }

    private static void iniciarCronometro() {
        cronometro = new Timer(1000, new ActionListener() {
            int segundos = 0;
            int minutos = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                segundos++;
                if (segundos == 60) {
                    minutos++;
                    segundos = 0;
                }
                labelCronometro.setText(minutos + " : " + segundos);
            }
        });
        cronometro.start();
    }
}

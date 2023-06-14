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
        URL bandeiraURL = getClass().getResource("src/GUI/assets/noun-flag-5786229.png");
        URL bombaURL = getClass().getResource("src/GUI/assets/noun-bomb-238911.png");

        if (bandeiraURL != null)
            iconeBandeira = new ImageIcon(bandeiraURL);
        else
            iconeBandeira = new ImageIcon("src/GUI/assets/noun-flag-5786229.png");

        if (bombaURL != null)
            iconeBomba = new ImageIcon(bombaURL);
        else
            iconeBomba = new ImageIcon("src/GUI/assets/noun-bomb-238911.png");

        cliques = 0;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------


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
                    encerrar();
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

    private static void encerrar(){

        calcularPontos();
    }

    private static void calcularPontos() {
    }
}

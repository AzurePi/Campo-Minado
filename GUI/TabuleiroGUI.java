package GUI;

import campoMinado.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class TabuleiroGUI extends JPanel {
    private static Tabuleiro t;
    private static NossoBotao[][] botoes;
    private static Timer cronometro;
    private static boolean cronometroIniciado;
    private static JLabel labelCronometro;

    public TabuleiroGUI() {
    }

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
            // botão direito -> coloca uma bandeira
            if (button == 0) {
                int i = source.getI();
                int j = source.getJ();

                Quadrado<?> quadrado = t.getBoard().get(i).get(j);

                int conteudo = quadrado.getConteudo().revelar();

                if (conteudo == -1) {

                } else if (conteudo == 0) {
                    abrirVazio(i,j);
                } else {
                    abrirNumerado(i, j);
                }

            } else if (button == 3 && !source.isFoiClickado()) {
                //se não há bandeira, desativamos o botão
                //se há bandeira, reativamos o botão
                source.setEnabled(!source.isEnabled());
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

        public void abrirVazio(int linha, int coluna){
            botoes[linha][coluna].setEnabled(false);
            botoes[linha][coluna].setFoiClickado(true);

            for (int i = linha - 1; i <= linha + 1; i++) {
                for (int j = coluna - 1; j <= coluna + 1; j++) {
                    if(Tabuleiro.coordenadaValida(i , j, t.getTamanho())){
                        if(t.getBoard().get(i).get(j).getConteudo().revelar() == 0){
                            abrirVazio(i, j);
                        }
                        else if(t.getBoard().get(i).get(j).getConteudo().revelar() != -1){
                            abrirNumerado(i, j);
                        }
                    }
                }
            }

        }

        private void abrirNumerado(int i, int j) {
            botoes[i][j].setEnabled(false);
            botoes[i][j].setFoiClickado(true);
            botoes[i][j].setText(String.valueOf(t.getBoard().get(i).get(j).getConteudo().revelar()));
        }
    }

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
        ImageIcon bandeira;

        //redimensiona a imagem da bandeira
        try {
            File bandeiraFile = new File("GUI/assets/noun-flag-5786229.png");
            Image bandeiraImagem = ImageIO.read(bandeiraFile);
            Image resized = bandeiraImagem.getScaledInstance(30, -1, Image.SCALE_DEFAULT);

            bandeira = new ImageIcon(resized);
        } catch (IOException e) {
            bandeira = new ImageIcon("GUI/assets/noun-flag-5786229.png");
        }

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                NossoBotao botao = new NossoBotao(i,j);
                botao.setPreferredSize(new Dimension(30, 30));
                botao.setDisabledIcon(bandeira); //ícone que só aparece quando o botão está desativado
                //TODO: fazer a imagem aparecer de fato (:

                botao.addMouseListener(new ListenerJogo());

                botoes[i][j] = botao;
                this.add(botao); //adiciona o botão ao JPanel
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

package GUI;

import campoMinado.Quadrado;
import campoMinado.Tabuleiro;

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
    private static JButton[][] botoes;
    private Timer cronometro;
    private boolean cronometroIniciado;
    private JLabel labelCronometro;

    public TabuleiroGUI() {
    }

    private static class ListenerMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class ListenerJogo implements MouseListener {
        int tamanho;

        public ListenerJogo(JButton[][] botoes) {
            tamanho = botoes.length;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int button = e.getButton();
            JButton source = (JButton) e.getSource();

            //botão esquerdo -> revela o conteúdo
            // botão direito -> coloca uma bandeira
            if (button == 0) {
                int i = 0, j = 0;

                //TODO: esse while pode ser uma outra função
                while (i < tamanho && j < tamanho && botoes[i][j] != source) {
                    j++; //passamos para o próximo quadrado
                    if (j == tamanho) {
                        j = 0; //voltamos à primeira coluna
                        i++; //passamos para a próxima linha
                    }
                }
                Quadrado<?> quadrado = t.getBoard().get(i).get(j);
                int conteudo = quadrado.getConteudo().revelar();

                if (conteudo == -1) {
                    //é bomba
                } else if (conteudo == 0) {
                    //é quadrado vazio
                    //faz toda a maracutaia de abrir o resto
                } else {
                    //é quadrado numerado
                    //trocamos a imagem, o fundo, etc
                }

            } else if (button == 3) {
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


        botoes = new JButton[tamanho][tamanho];
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
                JButton botao = new JButton();
                botao.setPreferredSize(new Dimension(30, 30));
                botao.setDisabledIcon(bandeira); //ícone que só aparece quando o botão está desativado
                //TODO: fazer a imagem aparecer de fato (:


                //adiciona um ActionListener para controlar o cronômetro
                botao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!cronometroIniciado) {
                            iniciarCronometro();
                            cronometroIniciado = true;
                        }
                    }
                });
                botao.addMouseListener(new ListenerJogo(botoes));

                botoes[i][j] = botao;
                this.add(botao); //adiciona o botão ao JPanel
            }
        }
    }

    private void iniciarCronometro() {
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

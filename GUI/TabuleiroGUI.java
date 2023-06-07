package GUI;

import campoMinado.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabuleiroGUI extends JPanel {
    private Tabuleiro t;
    private Timer cronometro;
    private boolean cronometroIniciado;
    private JLabel labelCronometro;

    public TabuleiroGUI(){
    }

    private static class ListenerMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class ListenerJogo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public void inicializar(int tamanho) {
        t = new Tabuleiro(tamanho);

        this.setLayout(new GridLayout(tamanho+1, tamanho, 2, 2));

        for(int i = 0; i < tamanho/2; i++){
            JLabel filler = new JLabel();
            this.add(filler);
        }
        labelCronometro = new JLabel("0 : 0");
        labelCronometro.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(labelCronometro);
        for(int i = 0; i < tamanho/2; i++){
            JLabel filler = new JLabel();
            this.add(filler);
        }

        JButton[][] botoes = new JButton[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                JButton botao = new JButton();
                botao.setPreferredSize(new Dimension(30, 30));

                botao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!cronometroIniciado) {
                            iniciarCronometro();
                            cronometroIniciado = true;
                        }
                    }
                });

                botoes[i][j] = botao;
                this.add(botao);
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

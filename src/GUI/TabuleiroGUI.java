package src.GUI;

import src.campoMinado.Quadrado;
import src.campoMinado.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class TabuleiroGUI extends JPanel {
    private static Tabuleiro t;
    private static NossoBotao[][] botoes;
    private static Timer cronometro;
    private static boolean cronometroIniciado;
    private static JLabel labelCronometro;
    private static ImageIcon iconeBandeira;
    private static ImageIcon iconeBomba;
    private static int cliques = 0;

    public TabuleiroGUI(){
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
    }

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

    public static void incrementarCliques(){
        cliques++;
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

        //TODO: contador de bandeiras

        botoes = new NossoBotao[tamanho][tamanho];
        ListenerJogo listener = new ListenerJogo();

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                NossoBotao botao = new NossoBotao(i, j);
                botao.addMouseListener(listener);

                /* mostrando os valores para testar
                botao.setEnabled(false);
                botao.setFoiClickado(true);
                if (t.getBoard().get(i).get(j).getConteudo().revelar() == 0) {
                    botao.setText("vazio");
                } else if (t.getBoard().get(i).get(j).getConteudo().revelar() == -1) {
                    botao.setDisabledIcon(iconeBomba);
                    botao.setText("bomba");
                } else {
                    int numero = t.getBoard().get(i).get(j).getConteudo().revelar();
                    botao.setText(String.valueOf(numero));
                }
                ------------------------------------ */

                botoes[i][j] = botao;
                this.add(botao);
            }
        }
        //t.imprimir();
    }

    public static void iniciarCronometro() {
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

    public static void encerrar(){

    }

}

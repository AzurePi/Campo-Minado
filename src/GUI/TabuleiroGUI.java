package src.GUI;

import src.campoMinado.Bomba;
import src.campoMinado.Conteudo;
import src.campoMinado.Quadrado;
import src.campoMinado.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

public class TabuleiroGUI extends JPanel implements MouseListener , ActionListener {
    private Tabuleiro t;
    private NossoBotao[][] botoes;
    private Timer cronometro;
    private boolean cronometroIniciado;
    private JLabel labelCronometro;
    private ImageIcon iconeBandeira;
    private ImageIcon iconeBomba;
    private int cliques = 0;
    private int segundos = 0;
    private int minutos = 0;
    private int bandeiras;

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


    public Tabuleiro getT() {
        return t;
    }

    public void setT(Tabuleiro t) {
        this.t = t;
    }

    public NossoBotao[][] getBotoes() {
        return botoes;
    }

    public void setBotoes(NossoBotao[][] botoes) {
        this.botoes = botoes;
    }

    public Timer getCronometro() {
        return cronometro;
    }

    public void setCronometro(Timer cronometro) {
        this.cronometro = cronometro;
    }

    public boolean isCronometroIniciado() {
        return cronometroIniciado;
    }

    public void setCronometroIniciado(boolean cronometroIniciado) {
        this.cronometroIniciado = cronometroIniciado;
    }

    public JLabel getLabelCronometro() {
        return labelCronometro;
    }

    public void setLabelCronometro(JLabel labelCronometro) {
        this.labelCronometro = labelCronometro;
    }

    public ImageIcon getIconeBandeira() {
        return iconeBandeira;
    }

    public void setIconeBandeira(ImageIcon iconeBandeira) {
        this.iconeBandeira = iconeBandeira;
    }

    public ImageIcon getIconeBomba() {
        return iconeBomba;
    }

    public void setIconeBomba(ImageIcon iconeBomba) {
        this.iconeBomba = iconeBomba;
    }

    public int getCliques() {
        return cliques;
    }

    public void setCliques(int cliques) {
        this.cliques = cliques;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void incrementarCliques(){
        cliques++;
    }

    public void inicializar(int tamanho) {
        t = new Tabuleiro(tamanho);

        bandeiras = t.getnBombas();

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

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                NossoBotao botao = new NossoBotao(i, j);
                botao.addMouseListener(this);

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

    public void iniciarCronometro() {
        cronometro = new Timer(1000, new ActionListener() {
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
        cronometroIniciado = true;
    }

    public void encerrar(boolean venceu){
        cronometro.stop();
        cronometroIniciado = false;
        String m;
        JOptionPane g;

        if(venceu){
            int score = contabilizaPontos();
            m = "Você Venceu! \nPontução: " + score  + "\nParabéns";

            JButton[] opcoes = new JButton[2];
            opcoes[0] = new JButton("Menu Inicial");
            opcoes[1] = new JButton("Placar");

            opcoes[0].addActionListener(this);

            opcoes[1].addActionListener(this);

            g = new JOptionPane(this, JOptionPane.DEFAULT_OPTION
            , JOptionPane.PLAIN_MESSAGE, iconeBomba, opcoes, null);
        }
        else{
            m = "Você Perdeu!";
            JButton[] opcoes = new JButton[1];
            opcoes[0] = new JButton("Menu Inicial");

            opcoes[0].addActionListener(this);

            JOptionPane.showOptionDialog(this,m, "Derrota!", JOptionPane.DEFAULT_OPTION
                    , JOptionPane.PLAIN_MESSAGE, iconeBomba, opcoes, null);

        }
    }

    private int contabilizaPontos() {
        int tempo = segundos + (minutos * 60);
        return  (int)((1 / (float)cliques) * (1 / (float)tempo) * 1000000);
    }

    public boolean jaAcabou (){
        int i = 0;
        int j = 0;
        int tamanho = getT().getTamanho();
        ArrayList<ArrayList<Quadrado<?>>> t = getT().getBoard();

        while(i < tamanho){
            Conteudo c = t.get(i).get(j).getConteudo();
            if(!(c instanceof Bomba)){
                if(!(botoes[i][j].isFoiClickado())){
                    return false;
                }
            }
            j++;
            if(j == tamanho){
                j = 0;
                i++;
            }
        }
        return true;
    }


    // ----------------------------------------------------------------------------
    // Listener Jogo
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!cronometroIniciado) {
            iniciarCronometro();
        }
        int button = e.getButton();
        NossoBotao source = (NossoBotao) e.getSource();

        //botão esquerdo -> revela o conteúdo
        //botão direito -> coloca uma bandeira
        if (button == 1) {
            incrementarCliques();

            int i = source.getI();
            int j = source.getJ();

            Quadrado<?> quadrado = t.getBoard().get(i).get(j);
            int conteudo = quadrado.getConteudo().revelar();

            if (conteudo == -1) {
                abrirBombas();
                encerrar(false);
            } else if (conteudo == 0)
                abrirVazio(i, j);
            else
                abrirNumerado(i, j);

            if(jaAcabou()){
                encerrar(true);
            }
        } else if (button == 3 && !source.isFoiClickado()) {
            if(source.isEnabled()){
                source.setEnabled(false);
                bandeiras--;
            }
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
        int tamanho = t.getTamanho();

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                int conteudo = t.getBoard().get(i).get(j).getConteudo().revelar();
                if (conteudo == -1) {
                    botoes[i][j].setEnabled(false);
                    botoes[i][j].setDisabledIcon(iconeBomba);
                    botoes[i][j].setText("bomba");
                }
            }
        }
    }

    public void abrirVazio(int linha, int coluna) {
        int tamanho = t.getTamanho();

        NossoBotao botao = botoes[linha][coluna];
        if (botao.isEnabled())
            botao.setEnabled(false);
        botao.setFoiClickado(true);

        for (int i = linha - 1; i <= linha + 1; i++) {
            for (int j = coluna - 1; j <= coluna + 1; j++) {
                if (Tabuleiro.coordenadaValida(i, j, tamanho)) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel parent = (MainPanel) this.getParent().getParent();
        CardLayout layout = parent.getLayout();
        JPanel telas = parent.getTelas();
        JButton source = (JButton) e.getSource();

        if(source.getText().equals("Menu Inicial")){
            layout.show(telas ,parent.getMENUINICIAL());
        }
        else if(source.getText().equals("Placar")){
            layout.show(telas ,parent.getPLACAR());
        }
        destruir();
    }

    private void destruir() {
        while(this.getComponentCount() != 0){
            this.remove(0);
        }
    }
}

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

public class TabuleiroGUI extends JPanel implements MouseListener, ActionListener {
    private Tabuleiro t;
    private int tamanho;
    private NossoBotao[][] botoes;
    private Timer cronometro;
    private boolean cronometroIniciado;
    private JLabel labelCronometro;
    private ImageIcon iconeBandeira;
    private ImageIcon iconeBomba;
    private int cliques;
    private int segundos;
    private int minutos;
    private int bandeiras;

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
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
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

    //Métodos ----------------------------------------------------------------------------------------------------------
    public void inicializar(int tamanho) {
        this.tamanho = tamanho;
        t = new Tabuleiro(tamanho);
        bandeiras = t.getnBombas();

        segundos = 0;
        minutos = 0;
        cliques = 0;

        this.setLayout(new GridLayout(tamanho + 1, tamanho, 2, 2));

        for (int i = 0; i < tamanho / 2; i++)
            this.add(new JLabel()); //preenche com fillers
        labelCronometro = new JLabel("0 : 0");
        labelCronometro.setHorizontalAlignment(SwingConstants.CENTER);
        labelCronometro.setFont(new Font(labelCronometro.getFont().getName(), Font.PLAIN, 25));
        this.add(labelCronometro);
        for (int i = 0; i < tamanho / 2; i++)
            this.add(new JLabel()); //preenche com fillers

        //TODO: contador de bandeiras

        botoes = new NossoBotao[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                NossoBotao botao = new NossoBotao(i, j);
                botao.addMouseListener(this);

                /* mostrando os valores para testar --------------------
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
                -------------------------------------------------------- */

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

    public void encerrar(boolean venceu) {
        cronometro.stop();
        cronometroIniciado = false;
        String m;
        JOptionPane g;

        if (venceu) {
            int score = contabilizaPontos();
            m = "Você Venceu!\nPontução: " + score + "\nParabéns";

            JButton[] opcoes = new JButton[2];
            opcoes[0] = new JButton("Menu Inicial");
            opcoes[1] = new JButton("Placar");

            opcoes[0].addActionListener(this);

            opcoes[1].addActionListener(this);

            g = new JOptionPane(this,
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    iconeBomba,
                    opcoes,
                    null);
        } else {
            m = "Você Perdeu!";
            JButton[] opcoes = new JButton[1];
            opcoes[0] = new JButton("Menu Inicial");

            opcoes[0].addActionListener(this);

            JOptionPane.showOptionDialog(this,
                    m,
                    "Derrota!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    iconeBomba,
                    opcoes,
                    null);
        }
    }

    private int contabilizaPontos() {
        int tempo = segundos + (minutos * 60);
        return (int) ((1 / (float) cliques) * (1 / (float) tempo) * 1000000);
    }

    public boolean jaAcabou() {
        int i = 0;
        int j = 0;
        ArrayList<ArrayList<Quadrado<?>>> t = getT().getBoard();

        while (i < tamanho) {
            Conteudo c = t.get(i).get(j).getConteudo();
            if (!(c instanceof Bomba)) {
                if (!(botoes[i][j].isFoiClickado()))
                    return false;
            }
            j++;
            if (j == tamanho) {
                j = 0;
                i++;
            }
        }
        return true;
    }

    private void destruir() {
        while (this.getComponentCount() != 0)
            this.remove(0);
    }

    //Mouse Listener ---------------------------------------------------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!cronometroIniciado)
            iniciarCronometro();

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
                encerrar(false);
            } else if (conteudo == 0)
                abrirVazio(i, j);
            else
                abrirNumerado(i, j);

            if (jaAcabou())
                encerrar(true);

        } else if (button == 3 && !source.isFoiClickado()) {
            if (source.isEnabled()) {
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
        int conteudo;

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                conteudo = t.getBoard().get(i).get(j).getConteudo().revelar();
                if (conteudo == -1) {
                    NossoBotao aux = botoes[i][j];
                    aux.setEnabled(false);
                    aux.setDisabledIcon(iconeBomba);
                    aux.setText("bomba");
                }
            }
        }
    }

    public void abrirVazio(int linha, int coluna) {
        int conteudo;

        NossoBotao botao = botoes[linha][coluna];
        if (botao.isEnabled())
            botao.setEnabled(false);
        botao.setFoiClickado(true);

        for (int i = linha - 1; i <= linha + 1; i++) {
            for (int j = coluna - 1; j <= coluna + 1; j++) {
                if (Tabuleiro.coordenadaValida(i, j, tamanho)) {
                    conteudo = t.getBoard().get(i).get(j).getConteudo().revelar();
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

    //Action Listener --------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String sourceText = source.getText();

        MainPanel parent = (MainPanel) this.getParent().getParent();
        CardLayout layout = parent.getLayout();
        JPanel telas = parent.getTELAS();

        if (sourceText.equals("Menu Inicial")) {
            layout.show(telas, parent.getMENUINICIAL());
        } else if (sourceText.equals("Placar")) {
            layout.show(telas, parent.getPLACAR());
        }
        destruir();
    }
}
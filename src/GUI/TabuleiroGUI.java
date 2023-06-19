package src.GUI;

import exceptions.InvalidNameException;
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
import java.util.ArrayList;

import static src.Main.f;

public class TabuleiroGUI extends JPanel implements MouseListener, ActionListener {
    private Tabuleiro t;
    private int tamanho;
    private NossoBotao[][] botoes;
    private Timer cronometro;
    private boolean cronometroIniciado;
    private JLabel labelCronometro;
    private JLabel labelBandeiras;
    private ImageIcon iconeBomba;
    private ImageIcon iconeBandeira;
    private int cliques;
    private int segundos;
    private int minutos;
    private int bandeiras;
    private final Color vermelhoBandeira;
    private final Color vermelhoBomba;
    private Font fonteBomba;

    //Construtor -------------------------------------------------------------------------------------------------------
    public TabuleiroGUI() {
        iconeBomba = new ImageIcon("src/GUI/assets/noun-bomb-238911.png");
        iconeBandeira = new ImageIcon("src/GUI/assets/noun-flag-5786229.png");

        UIManager.put("Button.disabledText", Color.BLACK);
        vermelhoBomba = new Color(100, 0, 0);
        vermelhoBandeira = new Color(234, 119, 119);
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public Tabuleiro getT() {
        return t;
    }

    public void setT(Tabuleiro t) {
        this.t = t;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
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

    public JLabel getLabelBandeiras() {
        return labelBandeiras;
    }

    public void setLabelBandeiras(JLabel labelBandeiras) {
        this.labelBandeiras = labelBandeiras;
    }

    public ImageIcon getIconeBomba() {
        return iconeBomba;
    }

    public void setIconeBomba(ImageIcon iconeBomba) {
        this.iconeBomba = iconeBomba;
    }

    public ImageIcon getIconeBandeira() {
        return iconeBandeira;
    }

    public void setIconeBandeira(ImageIcon iconeBandeira) {
        this.iconeBandeira = iconeBandeira;
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

    public int getBandeiras() {
        return bandeiras;
    }

    public void setBandeiras(int bandeiras) {
        this.bandeiras = bandeiras;
    }

    public Color getVermelhoBandeira() {
        return vermelhoBandeira;
    }

    public Color getVermelhoBomba() {
        return vermelhoBomba;
    }

    public Font getFonteBomba() {
        return fonteBomba;
    }

    public void setFonteBomba(Font fonteBomba) {
        this.fonteBomba = fonteBomba;
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
        for (int i = 1; i < tamanho / 2; i++)
            this.add(new JLabel()); //preenche com fillers

        iconeBandeira = new ImageIcon(iconeBandeira.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        labelBandeiras = new JLabel(String.valueOf(bandeiras), iconeBandeira, SwingConstants.CENTER);
        this.add(labelBandeiras);

        fonteBomba = new Font(labelCronometro.getFont().getName(), Font.BOLD, 15);
        iconeBomba = new ImageIcon(iconeBomba.getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT));

        botoes = new NossoBotao[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                NossoBotao botao = new NossoBotao(i, j);
                botao.addMouseListener(this);

                botoes[i][j] = botao;
                this.add(botao);
            }
        }
        t.imprimir();
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
        String m, t;
        Object[] opcoes;

        if (venceu) {
            int score = contabilizaPontos();
            String nome;
            m = "Você Venceu! Parabéns!\nPontução: " + score;
            t = "Vitória :)";

            opcoes = new Object[1];
            JTextField inputNome = new JTextField("Nome para o placar", 12);
            opcoes[0] = inputNome;

            MainPanel parent = (MainPanel) this.getParent().getParent();

            boolean flag;
            do {
                try {
                    nome = String.valueOf(JOptionPane.showInputDialog(f, m, t, JOptionPane.PLAIN_MESSAGE, null, null, "SeuNome"));

                    parent.getPlacar().adicionarPontuacao(score, nome, tamanho);
                    flag = false;
                } catch (InvalidNameException e) {
                    System.out.println("ERRO: " + e.getMessage());
                    flag = true;
                }
            } while (flag);

            CardLayout layout = parent.getLayout();
            JPanel telas = parent.getTELAS();

            layout.show(telas, parent.getMENUINICIAL());
            destruir();
        } else {
            m = "Você Perdeu!";
            t = "Derrota :(";
            opcoes = new JButton[1];
            opcoes[0] = new JButton("Menu Inicial");

            ((JButton) opcoes[0]).addActionListener(this);

            JOptionPane.showOptionDialog(f,
                    m,
                    t,
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
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
        if (button == 1 && source.isEnabled()) {
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
            if (source.isEnabled() && bandeiras > 0) {
                source.setEnabled(false);
                source.setBackground(vermelhoBandeira);
                source.setText("Bandeira");
                bandeiras--;
            } else if (!source.isEnabled()) {
                source.setEnabled(true);
                source.setBackground(null);
                source.setText("");
                bandeiras++;
            }
            labelBandeiras.setText(String.valueOf(bandeiras));
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
                    aux.setText("Bomba");
                    aux.setFont(fonteBomba);
                    aux.setBackground(vermelhoBomba);
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

        if (sourceText.equals("Menu Inicial"))
            layout.show(telas, parent.getMENUINICIAL());

        Window w = SwingUtilities.getWindowAncestor(source);
        if (w != null)
            w.dispose();

        destruir();
    }
}
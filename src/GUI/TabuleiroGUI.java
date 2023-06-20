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

/**
 * O painel (extende <code>JPanel</code>) do menu inicial do campo minado. <br>Implementa <code>ActionListener</code> para gerenciar as ações dos botões de JOptionPane. Implementa <code>MouseListener</code> para gerenciar o comportamento dos <code>BotaoCampoMinado</code> que compõem o tabuleiro.
 * <br>
 * Possui um cronômetro, um contador de bandeiras, e um contador de cliques.
 */
public class TabuleiroGUI extends JPanel implements MouseListener, ActionListener {
    private Tabuleiro t;
    private int tamanho;
    private BotaoCampoMinado[][] botoes;
    private Timer cronometro;
    private boolean cronometroIniciado;
    private final JLabel labelCronometro;
    private final JLabel labelBandeiras;
    private int cliques;
    private int segundos;
    private int minutos;
    private int bandeiras;
    private final Color vermelhoBandeira;
    private final Color vermelhoBomba;
    private final Font fonteBomba;

    //Construtor -------------------------------------------------------------------------------------------------------

    /**
     * Contrói um novo painel de tabuleiro. Define um ícone para a bandeira, inicializa uma label para o cronômetro, e inicializa fontes e cores.
     */
    public TabuleiroGUI() {

        //cria um ícone, e com base nele cria uma versão escalonada
        ImageIcon iconeBandeira = new ImageIcon("src/GUI/assets/noun-flag-5786229.png");
        iconeBandeira = new ImageIcon(iconeBandeira.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        labelCronometro = new JLabel();
        labelCronometro.setHorizontalAlignment(SwingConstants.CENTER);
        labelCronometro.setFont(new Font(labelCronometro.getFont().getName(), Font.PLAIN, 25));

        labelBandeiras = new JLabel(iconeBandeira, SwingConstants.CENTER);

        fonteBomba = new Font(labelCronometro.getFont().getName(), Font.BOLD, 15);

        UIManager.put("Button.disabledText", Color.BLACK);
        vermelhoBomba = new Color(100, 0, 0);
        vermelhoBandeira = new Color(234, 119, 119);
    }

    //Métodos ----------------------------------------------------------------------------------------------------------

    /**
     * Inicializa o tabuleiro com <code>tamanho</code> (dificuldade), criando um novo <code>Tabuleiro</code>, estabelecendo um <code>GridLayout</code> para organizar as labels do cronômetro, do conados de bandeiras, e os <code>BotaocampoMinado/code>.
     *
     * @param tamanho define a dificuldade do jogo, e tamanho utilizado em <code>GridLayout</code>
     */
    public void inicializar(int tamanho) {
        this.tamanho = tamanho;
        t = new Tabuleiro(tamanho);
        bandeiras = t.getnBombas();

        segundos = 0;
        minutos = 0;
        cliques = 0;

        this.setLayout(new GridLayout(tamanho + 1, tamanho, 2, 2));

        //cria a faixa superior do tabuleiro, com um timer e um contador de bandeiras
        for (int i = 0; i < tamanho / 2; i++)
            this.add(new JLabel()); //preenche com fillers
        labelCronometro.setText(minutos + " : " + segundos);
        this.add(labelCronometro);
        for (int i = 1; i < tamanho / 2; i++)
            this.add(new JLabel()); //preenche com fillers

        labelBandeiras.setText(String.valueOf(bandeiras));
        this.add(labelBandeiras);
        //----------------------------------------------------------------------------

        botoes = new BotaoCampoMinado[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                BotaoCampoMinado botao = new BotaoCampoMinado(i, j);
                botao.addMouseListener(this);

                botoes[i][j] = botao;
                this.add(botao);
            }
        }
        //t.imprimir();        //caso queiramos imprimir o campo no terminal
    }

    /**
     * Inicializa o cronômetro, com um <code>ActionListener</code> anônimo para contabilizar a passagem do tempo.
     */
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

    /**
     * Cria um JOptionPane para informar o resultado do jogo.
     * <br>
     * Se é vitória, permite a inserção de um nome para armazenamento da pontuação em um <code>Placar</code>.
     *
     * @param venceu se o encerramento do jogo é devido à vitória, ou derrota.
     */
    public void encerrar(boolean venceu) {
        cronometro.stop();
        cronometroIniciado = false;
        String m, t;

        if (venceu) {
            int score = contabilizaPontos();
            String nome;
            t = "Vitória :)";
            m = "Você Venceu! Parabéns!\nPontução: " + score;

            MainPanel mainPanel = (MainPanel) this.getParent().getParent();

            boolean flag;
            do {
                try {
                    nome = String.valueOf(JOptionPane.showInputDialog(f,
                            m,
                            t,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "SeuNome"));

                    if (nome != null)
                        mainPanel.getPlacarGUI().adicionarPontuacao(score, nome, tamanho);
                    flag = false;
                } catch (InvalidNameException e) {
                    JOptionPane.showMessageDialog(f, e.getMessage(), "ERRO", JOptionPane.PLAIN_MESSAGE);
                    flag = true;
                }
            } while (flag);

            CardLayout layout = mainPanel.getLayout();
            JPanel telas = mainPanel.getTelas();

            layout.show(telas, MainPanel.MENU_INICIAL);
            destruir();
        } else {
            t = "Derrota :(";
            m = "Você Perdeu!";
            Object[] opcoes = new JButton[1];
            opcoes[0] = new JButton("Menu Inicial");

            ((JButton) opcoes[0]).addActionListener(this);

            int opcao = JOptionPane.showOptionDialog(f,
                    m,
                    t,
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    null);
            if (opcao == JOptionPane.CLOSED_OPTION)
                ((JButton) opcoes[0]).doClick();
        }
    }

    /**
     * Calcula os pontos de um jogo finalizado.
     * @return a quantidade de pontos
     */
    private int contabilizaPontos() {
        int tempo = segundos + (minutos * 60);
        return (int) ((1 / (float) cliques) * (1 / (float) tempo) * 1000000);
    }

    /**
     * Percorre o <code>Tabuleiro</code> e compara com os <code>BotaoCampoMinado</code> já clicados, verificando se já chegamos ao final do jogo.
     *
     * @return <code>true</code> se todos os <code>BotaoCampoMinado</code> que não contém bombas já foram clicados, <code>false</code> do contrário
     */
    public boolean jaAcabou() {
        int i = 0;
        int j = 0;

        ArrayList<ArrayList<Quadrado<?>>> board = t.getBoard();

        while (i < tamanho) {
            Conteudo c = board.get(i).get(j).getConteudo();
            if (!(c instanceof Bomba) && !(botoes[i][j].isFoiClickado()))
                return false;
            j++;
            if (j == tamanho) {
                j = 0;
                i++;
            }
        }
        return true;
    }

    /**
     * Remove todos os elementos da GUI para permitir uma posterior reinicialização.
     */
    private void destruir() {
        while (this.getComponentCount() != 0)
            this.remove(0);
    }

    //Mouse Listener ---------------------------------------------------------------------------------------------------

    /**
     * Inicia o cronômetro, caso este ainda não esteja iniciado, e analisa um clique em um <code>BotaoCampoMinado</code>.
     * <br>
     * Se o clique ocorre com o botão esquerdo, é feita uma análise do conteúdo do <code>Quadrado</code> correspondente em <code>Tabuleiro</code>, e o procedimento adequado é tomado.
     * <br>
     * Se o clique ocorre com o botão direito, o botão é marcado com uma bandeira, e cliques posteriores com o botão esquerdo são ignorados. O contador de bandeiras é atualizado de acordo.
     * <br>
     * Quando o clique ocorre em uma bomba, o jogo é encerrado como uma derrota. A cada clique,uma verificação é feita para a condição de vitória.
     *
     * @param e o evento sendo processado, oriundo de um clique em um <code>BotaoCampoMinado</code>
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!cronometroIniciado)
            iniciarCronometro();

        int button = e.getButton();
        BotaoCampoMinado source = (BotaoCampoMinado) e.getSource();

        //botão esquerdo ≥ revela o conteúdo
        //botão direito ≥ coloca uma bandeira
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

    /**
     * Percorre o <code>Tabuleiro</code>, verificando o <code>Conteudo</code> de todos os <code>Quadrado</code>. Sempre que uma <code>Bomba</code> é encontrada, atualiza a GUI de acordo.
     */
    private void abrirBombas() {
        int conteudo;

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                conteudo = t.getBoard().get(i).get(j).getConteudo().revelar();
                if (conteudo == -1) {
                    BotaoCampoMinado aux = botoes[i][j];
                    aux.setEnabled(false);
                    aux.setText("Bomba");
                    aux.setFont(fonteBomba);
                    aux.setBackground(vermelhoBomba);
                }
            }
        }
    }

    /**
     * Função recursiva que permite que o clique em um <code>BotaoCampoMinado</code> que equivale a um <code>Quadrado</code> com <code>Conteudo</code> <code>Vazio</code> revele o conteúdo de todos os quadrados <code>Vazio</code> e <code>Numerado</code> ao seu redor.
     * Ao final de seu percurso, faz com que todos os botões que percorreu que não equivaliam a bombas sejam marcados como clicados.
     *
     * @param linha a linha em que o botão atual se encontra
     * @param coluna a coluna em que o botão atual se encontra
     */
    public void abrirVazio(int linha, int coluna) {
        int conteudo;

        BotaoCampoMinado botao = botoes[linha][coluna];
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

    /**
     * Atualiza a aparência de um <code>BotaoCampoMinado</code> que equivale a um <code>Quadrado</code> <code>Numerado</code>, e o marca como clicado.
     *
     * @param linha a linha em que o botão atual se encontra
     * @param coluna a linha em que o botão atual se encontra
     */
    private void abrirNumerado(int linha, int coluna) {
        BotaoCampoMinado numerado = botoes[linha][coluna];
        numerado.setEnabled(false);
        numerado.setFoiClickado(true);

        int numero = t.getBoard().get(linha).get(coluna).getConteudo().revelar();
        numerado.setText(String.valueOf(numero));
    }

    //Action Listener --------------------------------------------------------------------------------------------------

    /**
     * Quando o botão "Menu Inicial" é clicado no <code>JOptionPane</code> que anuncia a derrota, muda o painel sendo mostrado pelo <code>CardLayout</code> de <code>MainPanel</code> para o <code>MenuInicialGUI</code>. Posteriormente, destrói os conteúdos de <code>TabuleiroGUI</code> e fecha o <code>JOptionPane</code>.
     *
     * @param e o evento sendo processado, um clique no botão de <code>JOptionPane</code>
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String sourceText = source.getText();

        MainPanel parent = (MainPanel) this.getParent().getParent();
        CardLayout layout = parent.getLayout();
        JPanel telas = parent.getTelas();

        if (sourceText.equals("Menu Inicial"))
            layout.show(telas, MainPanel.MENU_INICIAL);

        Window w = SwingUtilities.getWindowAncestor(source);
        if (w != null)
            w.dispose();

        destruir();
    }
}
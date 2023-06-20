package src.GUI;

import exceptions.InvalidNameException;
import src.placar.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * O painel (extende <code>JPanel</code>) do placar do campo minado. <br>Implementa <code>ActionListener</code> para gerenciar as ações de seus próprios botões.
 * <br>
 * Possui três paineis associados a um <code>Placar<></code> cada, um para cada dificuldade de jogo.
 */
public class PlacarGUI extends JPanel implements ActionListener {
    private final Placar<PontuacaoFacil> placarF;
    private final Placar<PontuacaoMedio> placarM;
    private final Placar<PontuacaoDificil> placarD;
    private final JPanel painelFacil;
    private final JPanel painelMedio;
    private final JPanel painelDificil;
    private final Font fontePlacar;

    //Construtor -------------------------------------------------------------------------------------------------------
    /**
     * Constrói um novo painel de placar, com um botão para retornar ao menu inicial, e uma estrutura de <code>tabs</code> para armazenar os paineis das diferentes dificuldades.
     */
    public PlacarGUI() {
        //a chamada do construtor de Placar<> já realiza a leitura de eventuais informações de arquivos
        placarF = new Placar<>("Fácil");
        placarM = new Placar<>("Médio");
        placarD = new Placar<>("Difícil");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton voltar = new JButton("Menu");
        voltar.addActionListener(this);
        voltar.setVisible(true);
        this.add(voltar);

        JLabel labelTitulo = new JLabel("Placar");
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 35));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setVisible(true);
        this.add(labelTitulo);

        fontePlacar = new Font(labelTitulo.getFont().getName(), Font.PLAIN, 15);

        painelFacil = new JPanel();
        painelMedio = new JPanel();
        painelDificil = new JPanel();

        atualizar(painelFacil, placarF);
        atualizar(painelMedio, placarM);
        atualizar(painelDificil, placarD);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Fácil", painelFacil);
        tabs.addTab("Médio", painelMedio);
        tabs.addTab("Difícil", painelDificil);

        this.add(tabs);
    }

    //Métodos ----------------------------------------------------------------------------------------------------------
    /**
     * Atualiza um <code>painel</code> para exibir as informações de <code>placar</code>.
     *
     * @param painel qual painel de pontuacao está sendo atualizado
     * @param placar informações de qual placar estão sendo usadas
     */
    private void atualizar(JPanel painel, Placar<?> placar) {
        while (painel.getComponentCount() > 0)
            painel.remove(0);

        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        int tamanho = Math.min(placar.getPontuacoes().size(), 10);

        Pontuacao pontuacao;
        String texto;
        for (int i = 0; i < tamanho; i++) {
            pontuacao = placar.getPontuacoes().get(i);
            texto = pontuacao.getNome() + "                 " + pontuacao.getPontos();

            JLabel l = new JLabel(texto);
            l.setFont(fontePlacar);
            painel.add(l);
        }
    }

    /**
     * Adiciona uma nova <code>Pontuacao</code>, do tipo apropriado, ao placar apropriado, e atualiza a exibição do painel correspondente.
     *
     * @param score   a pontuação obtida no jogo
     * @param nome    o nome associado à pontuação
     * @param tamanho o tamanho do tabuleiro (usado para determinar a dificuldade)
     * @throws InvalidNameException caso <code>nome</code> não se enquadre nos parâmetros esperados
     */
    public void adicionarPontuacao(int score, String nome, int tamanho) throws InvalidNameException {
        switch (tamanho) {
            case 5 -> {
                placarF.add(new PontuacaoFacil(nome, score));
                atualizar(painelFacil, placarF);
            }
            case 7 -> {
                placarM.add(new PontuacaoMedio(nome, score));
                atualizar(painelMedio, placarM);
            }
            case 11 -> {
                placarD.add(new PontuacaoDificil(nome, score));
                atualizar(painelDificil, placarD);
            }
        }
    }

    /**
     * Salva cada <code>Placar</code> em seu arquivo correspondente.
     */
    public void salvar() {
        placarF.printToFile();
        placarM.printToFile();
        placarD.printToFile();
    }

    /**
     * Troca, através do <code>CardLayout</code> de <code>MainPanel</code>, o painel sendo exibido, retornando ao menu inicial.
     *
     * @param e o evento sendo processado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel parent = (MainPanel) this.getParent().getParent();
        CardLayout layout = parent.getLayout();
        JPanel telas = parent.getTelas();

        layout.show(telas, MainPanel.MENU_INICIAL);
    }
}

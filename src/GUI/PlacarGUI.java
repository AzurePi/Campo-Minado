package src.GUI;

import exceptions.InvalidNameException;
import src.placar.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlacarGUI extends JPanel implements ActionListener {
    private final Placar<PontuacaoFacil> placarF;
    private final Placar<PontuacaoMedio> placarM;
    private final Placar<PontuacaoDificil> placarD;
    private final JPanel painelFacil;
    private final JPanel painelMedio;
    private final JPanel painelDificil;
    private final Font fontePlacar;

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

        iniciar(painelFacil, placarF);
        iniciar(painelMedio, placarM);
        iniciar(painelDificil, placarD);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Fácil", painelFacil);
        tabs.addTab("Médio", painelMedio);
        tabs.addTab("Difícil", painelDificil);

        this.add(tabs);
    }

    private void iniciar(JPanel painel, Placar<?> placar) {
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        int tamanho = Math.min(placar.getTamanho(), 10);

        Pontuacao pontuacao;
        String texto;
        for (int i = 0; i < tamanho; i++) {
            pontuacao = placar.getPontuacoes().get(i);
            texto = pontuacao.getNome() + "\t" + pontuacao.getPontos();

            JLabel l = new JLabel(texto);
            l.setFont(fontePlacar);
            painel.add(l);
        }
    }

    public void adicionarPontuacao(int score, String nome, int tamanho) throws InvalidNameException {
        JLabel l = new JLabel(nome + "\t" + score);
        l.setFont(fontePlacar);

        switch (tamanho) {
            case 5 -> {
                placarF.addPontuacao(new PontuacaoFacil(nome, score));
                painelFacil.add(l);
            }
            case 7 -> {
                placarM.addPontuacao(new PontuacaoMedio(nome, score));
                painelMedio.add(l);
            }
            case 11 -> {
                placarD.addPontuacao(new PontuacaoDificil(nome, score));
                painelDificil.add(l);
            }
        }
    }
        //// TODO: consegui fazer aparecer na GUI! Falta ir pros arquivos

    public void salvar() {
        placarF.printToFile();
        placarM.printToFile();
        placarD.printToFile();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel parent = (MainPanel) this.getParent().getParent();
        CardLayout layout = parent.getLayout();
        JPanel telas = parent.getTelas();

        layout.show(telas, MainPanel.MENU_INICIAL);
    }
}

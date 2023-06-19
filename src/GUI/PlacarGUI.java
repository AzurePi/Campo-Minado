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

    public PlacarGUI() {
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

        painelFacil = new JPanel();
        painelMedio = new JPanel();
        painelDificil = new JPanel();

        atualizar(painelFacil);
        atualizar(painelMedio);
        atualizar(painelDificil);

        painelFacil.setLayout(new BoxLayout(painelFacil, BoxLayout.Y_AXIS));
        painelMedio.setLayout(new BoxLayout(painelMedio, BoxLayout.Y_AXIS));
        painelDificil.setLayout(new BoxLayout(painelDificil, BoxLayout.Y_AXIS));

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Fácil", painelFacil);
        tabs.addTab("Médio", painelMedio);
        tabs.addTab("Difícil", painelDificil);

        this.add(tabs);
    }

    private void atualizar(JPanel painel) {
        while (painel.getComponentCount() > 0)
            painel.remove(0);

        Placar<?> placar;
        if (painel == painelFacil)
            placar = placarF;
        else if (painel == painelMedio)
            placar = placarM;
        else
            placar = placarD;

        int tamanho = Math.min(placar.getTamanho(), 10);

        Pontuacao pontuacao;
        String texto;
        for (int i = 0; i < tamanho; i++) {
            pontuacao = placar.getPontuacoes().get(i);
            texto = pontuacao.getNome() + "\t" + pontuacao.getPontos();

            JLabel l = new JLabel(texto);
            l.setVisible(true);

            painel.add(l);
        }
    }

    public void adicionarPontuacao(int score, String nome, int tamanho) throws InvalidNameException {
        switch (tamanho) {
            case 5 -> {
                placarF.addPontuacao(new PontuacaoFacil(nome, score));
                atualizar(painelFacil);
            }
            case 7 -> {
                placarM.addPontuacao(new PontuacaoMedio(nome, score));
                atualizar(painelMedio);
            }
            case 11 -> {
                placarD.addPontuacao(new PontuacaoDificil(nome, score));
                atualizar(painelDificil);
            }
        }
    }

    public void salvar() {
        placarF.printToFile();
        placarM.printToFile();
        placarD.printToFile();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel parent = (MainPanel) this.getParent().getParent();
        CardLayout layout = parent.getLayout();
        JPanel telas = parent.getTELAS();

        layout.show(telas, parent.getMENUINICIAL());
    }
}

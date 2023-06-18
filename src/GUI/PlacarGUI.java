package src.GUI;

import src.placar.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlacarGUI extends JPanel implements ActionListener {
    private final Placar<PontuacaoFacil> placarF;
    private final Placar<PontuacaoMedio> placarM;
    private final Placar<PontuacaoDificil> placarD;
    private JLabel labelTitulo;
    private final JPanel painelFacil;
    private final JPanel painelMedio;
    private final JPanel painelDificil;
    private final String FACIL;
    private final String MEDIO;
    private final String DIFICIL;

    public PlacarGUI() {
        placarF = new Placar<>();
        placarM = new Placar<>();
        placarD = new Placar<>();

        FACIL = "Fácil";
        MEDIO = "Médio";
        DIFICIL = "Difícil";

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton voltar = new JButton("Menu");
        voltar.addActionListener(this);
        voltar.setVisible(true);
        this.add(voltar);

        labelTitulo = new JLabel("Placar");
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 35));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setVisible(true);
        this.add(labelTitulo);

        painelFacil = new JPanel();
        painelMedio = new JPanel();
        painelDificil = new JPanel();

        inicializar(painelFacil);
        inicializar(painelMedio);
        inicializar(painelDificil);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab(FACIL, painelFacil);
        tabs.addTab(MEDIO, painelMedio);
        tabs.addTab(DIFICIL, painelDificil);

        this.add(tabs);
    }

    private void inicializar(JPanel painel) {
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        Placar<?> placar;
        if (painel == painelFacil)
            placar = placarF;
        else if (painel == painelMedio)
            placar = placarM;
        else
            placar = placarD;

        int tamanho = Math.min(placar.getTamanho(), 10);

        Pontuacao aux;
        String textoAux;
        for (int i = 0; i < tamanho; i++) {
            aux = placar.getPontuacoes().get(i);
            textoAux = aux.getNome() + "\t" + aux.getPontos();
            painel.add(new JLabel(textoAux));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel parent = (MainPanel) this.getParent().getParent();
        CardLayout layout = parent.getLayout();
        JPanel telas = parent.getTELAS();

        layout.show(telas, parent.getMENUINICIAL());
    }
}

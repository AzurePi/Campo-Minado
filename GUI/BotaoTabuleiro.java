package GUI;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.Vector;

//TODO: ainda não tenho certeza se o que fiz aqui funciona; checar sites abaixo
//https://stackoverflow.com/questions/1475543/how-to-add-button-in-a-row-of-jtable-in-swing-java
//https://tips4java.wordpress.com/2009/07/12/table-button-column/

public class BotaoTabuleiro extends JButton implements TableCellRenderer, TableCellEditor {
    private int linha;
    private int coluna;
    Vector<BotaoTabuleiroListener> listener; //Vector é similar a ArrayList, mas é recomendável seu uso em aplicaçaões que não saõ thread-safe, como o swing

    public BotaoTabuleiro(ImageIcon conteudo) {
        super(conteudo);

        listener = new Vector<>();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (BotaoTabuleiroListener l : listener)
                    l.botaoClicado(linha, coluna);
            }
        });
    }

    public void addTableButtonListener(BotaoTabuleiroListener l) {
        listener.add(l);
    }

    public void removeTableButtonListener(BotaoTabuleiroListener l) {
        listener.remove(l);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this; //essa classe é seu próprio renderizador de célua
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return this; //essa classe é seu próprio editor de célula
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

    @Override
    public void cancelCellEditing() {

    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {

    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {

    }
}

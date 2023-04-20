package view;

import view.lisener.TextSizeChange;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.Serializable;

public class Logs implements Serializable {
    private JScrollPane scrollPane;
    private static JTextPane textArea;
    private static StyledDocument temp;

    public Logs(){
        textArea = new JTextPane();
        scrollPane = new JScrollPane(textArea);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setEditable(false);
        textArea.setBackground(new Color(201, 195, 160));
        textArea.setMargin(new Insets(5,5,5,20));
        textArea.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
        textArea.setPreferredSize(new Dimension(100,40));
        textArea.setMinimumSize(new Dimension(100,40));
        textArea.setAutoscrolls(true);
        scrollPane.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                int x = Math.min(scrollPane.getWidth(),scrollPane.getHeight());
                Font f = new Font("Comic Sans MS", Font.BOLD, x/30 + 5);
                textArea.setFont(f);
            }
            public void componentMoved(ComponentEvent e) {}
            public void componentShown(ComponentEvent e) {}
            public void componentHidden(ComponentEvent e) {}
        });
    }

    public static void addLog(String s){
        temp = textArea.getStyledDocument();
        try{
            temp.insertString(temp.getLength(),s,null);
        }catch(Exception e){}
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}

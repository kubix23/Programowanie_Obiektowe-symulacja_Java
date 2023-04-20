package view.lisener;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TextSizeChange extends ComponentAdapter {
    private Component p;
    private String text;
    private double number = 2;
    public TextSizeChange(Component p, String text){
        this.p = p;
        this.text = text;
    }
    public TextSizeChange(Component p, String text, double number){
        this(p,text);
        this.number = number;
    }
    @Override
    public void componentResized(ComponentEvent e) {
        super.componentResized(e);
        int x = Math.min(p.getWidth(),p.getHeight());
        p.setFont(new Font(text, Font.PLAIN, (int)(x/number + 5)));

    }
}

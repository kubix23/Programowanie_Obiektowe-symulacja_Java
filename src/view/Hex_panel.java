package view;

import view.lisener.TextSizeChange;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class Hex_panel extends JPanel{
        private int height_y = 20;
        private int width_x = 20;
        private int number = 4;
        private List<CustomShape> cells = new ArrayList<>(6);
        private CustomShape highlighted;

        public Hex_panel(World world, int width_x, int height_y, int number) {
            final JPanel frame = this;
            this.width_x = width_x;
            this.height_y = height_y;
            this.number = number;
            setBackground(Color.black);
            setForeground(Color.white);
            addComponentListener(new TextSizeChange(this,"Ariel",Math.max(width_x,height_y)*2));
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                    world.updateWorld();
                }
            });
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    highlighted = null;
                    for (CustomShape cell : cells) {
                        if (cell.contains(e.getPoint()) && !cell.hasName()) {
                            highlighted = cell;
                            break;
                        }
                    }
                    repaint();
                }
            });
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    if(!contains(e.getPoint())){
                        if(highlighted != null){
                            super.mouseExited(e);
                            Graphics2D g = (Graphics2D) getGraphics().create();
                            g.setColor(getBackground());
                            g.fill(highlighted);
                            g.setColor(getForeground());
                            highlighted.drawCell(g);
                            highlighted = null;
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    for (CustomShape cell : cells) {
                        if (cell.contains(e.getPoint())) {
                            int x = cell.getX();
                            int y = cell.getY();
                            if(world.getCell(x,y) == null){
                                Select_organism s = new Select_organism(world, x, y);
                                s.show(frame,e.getX(),e.getY());
                                break;
                            }
                        }
                    }
                }
            });
            updateHoneyComb();
        }

        @Override
        public void invalidate() {
            super.invalidate();
            updateHoneyComb();
        }

        private GeneralPath generateShape(double size){
            GeneralPath path = new GeneralPath();

            double centerX = size / 2;
            double centerY = size / 2;
            double angleOfset = number%2 == 0 ? 180/number : 90;
            for (int i = 0; i < number; i++) {
                double angleDegrees = ((360d/number) * i) + angleOfset;
                double angleRad = Math.toRadians(angleDegrees);


                double x = centerX + (centerX * Math.cos(angleRad));
                double y = centerY + (centerY * Math.sin(angleRad));
                if(i == 0){
                    path.moveTo(x, y);
                }else{
                    path.lineTo(x, y);
                }
            }
            path.closePath();
            double sx = size/path.getBounds2D().getWidth();
            double sy = size/path.getBounds2D().getHeight();
            path.transform(AffineTransform.getScaleInstance(sx,sy));
            path.transform(AffineTransform.getTranslateInstance(-path.getBounds2D().getX(),-path.getBounds2D().getY()));
            return path;
        }

        protected void updateHoneyComb() {
            double rowHeight = getHeight() / height_y;
            double colWidth = getWidth() / width_x;
            double size = Math.min(rowHeight, colWidth);
            GeneralPath path = generateShape(size);
            double width = size;
            double height = size;

            cells.clear();
            double yPos = 0;
            for (int row = 0; row < height_y; row++) {
                double offset = 0;
                int colCount = width_x;

                if (row % 2 == 0 && number == 6 ) {
                    offset = (width / 2d);
                }
                double xPos = offset;
                for (int col = 0; col < colCount; col++) {
                    AffineTransform at = AffineTransform.getTranslateInstance(xPos, yPos);
                    double dx = (getWidth())/(width * width_x);
                    if(number == 6)dx -= (width/2)/(width * width_x);
                    double dy = number == 6 ?(getHeight()-0.35*height)/(height * 0.75 * height_y) : getHeight()/(height * height_y);
                    AffineTransform at2 = AffineTransform.getScaleInstance(dx,dy);
                    CustomShape area = new CustomShape(path,col,row);
                    area.transform(at);
                    area.transform(at2);
                    cells.add(area);
                    xPos += width;
                }
                yPos += number == 6 ? height * 0.75 : height;
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            if (highlighted != null) {
                g2d.setColor(Color.BLUE);
                g2d.fill(highlighted);
            }
            g2d.setColor(Color.BLACK);
            for (CustomShape cell : cells) {
                g2d.setColor(getForeground());
                if(!cell.getName().isEmpty()){
                    int a = cell.getName().charAt(0);
                    g2d.setColor(new Color((a * 11)%256,(a * 73)%256,(a * 51)%256));
                    g2d.fill(cell);
                    g2d.setColor(getForeground());
                }
                cell.drawCell(g2d);
            }
            g2d.dispose();
        }

    @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        public CustomShape getShape(int x, int y){
            return cells.get(width_x * y + x);
        }
}

package org.digitalsmile.hexgrid;

import org.digitalsmile.hexgrid.coordinates.OffsetCoordinates;
import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.hexagon.Point;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalShape;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

public class HexagonViewer extends JFrame {

    public HexagonViewer() {
        super("Hexagon viewer");

        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel1 = new DrawingPanel();

        var border1 = new EmptyBorder(10, 10, 10, 10);

        JPanel outter1 = new JPanel(new BorderLayout());
        outter1.setBorder(border1);
        panel1.setBackground(Color.gray);
        outter1.add(panel1);
        add(outter1);

        pack();
    }

    public class DrawingPanel extends JPanel {
        
        private final HexagonGrid<?> hexagonGrid;

        public DrawingPanel() {
            this.setPreferredSize(new Dimension(1200, 800));

            this.hexagonGrid = new HexagonGrid.HexagonGridBuilder<>()
                    .side(75)
                    //.shape(new RectangleShape(5, 7), Orientation.FLAT)
                    .shape(new HexagonalShape(3), Orientation.POINTY)
                    .hexagonDataObjectHook(hexagon -> OffsetCoordinates.fromCube(Orientation.POINTY, hexagon))
                    .offsetX(535)
                    .offsetY(321)
                    .build();

            hexagonGrid.generateHexagons();
            for (Hexagon hexagon : hexagonGrid.getHexagons()) {
                System.out.print(OffsetCoordinates.fromCube(Orientation.POINTY, hexagon));
            }
            System.out.println();
            var hex = OffsetCoordinates.toCube(Orientation.POINTY, new OffsetCoordinates(4,1));
            System.out.println(hexagonGrid.getHexagonDataObject(hex).toString());
//            System.out.println(VM.current().details());
//            System.out.println(ClassLayout.parseInstance(hexagonGrid.getHexagons()).toPrintable());
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    var mouseX = MouseInfo.getPointerInfo().getLocation().x - DrawingPanel.this.getLocationOnScreen().x - hexagonGrid.getHexagonLayout().getOffset().x() - 10;
                    var mouseY = MouseInfo.getPointerInfo().getLocation().y - DrawingPanel.this.getLocationOnScreen().y - hexagonGrid.getHexagonLayout().getOffset().y() - 10;
                    getGraphics().drawString(mouseX + " " + mouseY, 10, 10);
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
            });
        }



        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawLines(g);
        }




        void drawLines(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            for (Hexagon hexagon : hexagonGrid.getHexagons()) {
                var points = hexagonGrid.getHexagonLayout().getHexagonCornerPoints(hexagon);
                for (Point point : points) {
                    var index = points.indexOf(point);
                    if (index + 1 == points.size()) {
                        index = -1;
                    }
                    g2d.draw(new Line2D.Double(point.x(), point.y(), points.get(index + 1).x(), points.get(index + 1).y()));
                }
                g2d.drawString(hexagon.toString(), (float) hexagonGrid.getHexagonLayout().getHexagonCenterPoint(hexagon).x(), (float) hexagonGrid.getHexagonLayout().getHexagonCenterPoint(hexagon).y());
                //g2d.drawString(OffsetCoordinates.fromCube(Orientation.POINTY, hexagon).toString(), (float) hexagonGrid.getHexagonLayout().getHexagonCenterPoint(hexagon).x(), (float) hexagonGrid.getHexagonLayout().getHexagonCenterPoint(hexagon).y());



            }

        }

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HexagonViewer().setVisible(true);
            }
        });
    }
}

package org.digitalsmile.hexgrid.viewer;

import org.digitalsmile.hexgrid.HexagonGrid;
import org.digitalsmile.hexgrid.coordinates.DoubledCoordinates;
import org.digitalsmile.hexgrid.coordinates.OffsetCoordinates;
import org.digitalsmile.hexgrid.hexagon.Hexagon;
import org.digitalsmile.hexgrid.hexagon.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

class DrawingGridPanel extends JPanel {

    private HexagonGrid<?> hexagonGrid;
    private boolean showCoordinates = true;
    private Class<?> coordinatesType = null;

    private Hexagon mouseOverHexagon;

    public DrawingGridPanel() {
        this.setPreferredSize(new Dimension(1400, 1000));

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseOverHexagon = hexagonGrid.getGridLayout().getHexagonLayout().getBoundingHexagon(new Point(e.getX(), e.getY()));
                if (mouseOverHexagon != null) {
                    //System.out.println(e.getX() +  "," + e.getY() + " " + hexagonGrid.getGridLayout().getHexagonLayout().getHexagonCenterPoint(mouseOverHexagon));
                    repaint();
                }
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (hexagonGrid == null) {
                    return;
                }
                var mouseX = MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x - hexagonGrid.getGridLayout().getHexagonLayout().getOffset().x() - 10;
                var mouseY = MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y - hexagonGrid.getGridLayout().getHexagonLayout().getOffset().y() - 10;
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

    public void setGrid(HexagonGrid<?> newGrid) {
        this.hexagonGrid = newGrid;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (hexagonGrid != null) {
            drawLines(g);
        }
    }


    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Hexagon hexagon : hexagonGrid.getHexagons()) {
            var points = hexagonGrid.getGridLayout().getHexagonLayout().getHexagonCornerPoints(hexagon);
            if (hexagon.equals(mouseOverHexagon)) {
                var polygon = new Polygon();
                for (Point point : points) {
                    polygon.addPoint((int) point.x(), (int) point.y());
                }
                g2d.setColor(Color.RED);
                g2d.fill(polygon);
            } else if (hexagon.equals(new Hexagon(0,0,0))) {
                var polygon = new Polygon();
                for (Point point : points) {
                    polygon.addPoint((int) point.x(), (int) point.y());
                }
                g2d.setColor(Color.CYAN);
                g2d.fill(polygon);
            } else {
                for (Point point : points) {
                    var index = points.indexOf(point);
                    if (index + 1 == points.size()) {
                        index = -1;
                    }
                    g2d.setColor(Color.BLACK);
                    g2d.draw(new Line2D.Double(point.x(), point.y(), points.get(index + 1).x(), points.get(index + 1).y()));
                }
            }
            if (showCoordinates) {
                var affinetransform = new AffineTransform();
                var frc = new FontRenderContext(affinetransform,true,true);
                var font = g.getFont();
                int textWidth = (int)(font.getStringBounds(hexagon.toString(), frc).getWidth());
                int textHeight = (int)(font.getStringBounds(hexagon.toString(), frc).getHeight());
                var centerPoint = hexagonGrid.getGridLayout().getHexagonLayout().getHexagonCenterPoint(hexagon);
                g2d.setColor(Color.BLACK);
                if (coordinatesType == OffsetCoordinates.class) {
                    var offsetCoordinates = OffsetCoordinates.fromCube(hexagonGrid.getGridLayout().getHexagonLayout().getOrientation(), hexagon);
                    g2d.drawString(offsetCoordinates.toString(),
                            (float) centerPoint.x() - textWidth / 2f,
                            (float) centerPoint.y() + textHeight / 2f);
                } else if (coordinatesType == DoubledCoordinates.class) {
                    var doubledCoordinates = DoubledCoordinates.rowDoubledFromCube(hexagon);
                    g2d.drawString(doubledCoordinates.toString(),
                            (float) centerPoint.x() - textWidth / 2f,
                            (float) centerPoint.y() + textHeight / 2f);
                } else {
                    g2d.drawString(hexagon.toString(),
                            (float) centerPoint.x() - textWidth / 2f,
                            (float) centerPoint.y() + textHeight / 2f);
                }
            }
        }

    }

    public void toggleCoordinates(boolean value, Class<?> coordinatesType) {
        this.showCoordinates = value;
        this.coordinatesType = coordinatesType;
    }
}

package org.digitalsmile.hexgrid.viewer;

import org.digitalsmile.hexgrid.HexagonGrid;
import org.digitalsmile.hexgrid.coordinates.OffsetCoordinates;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.shapes.Shape;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalShape;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HexagonViewer extends JFrame {

    private final JPanel mainPanel = new JPanel();
    private final DrawingGridPanel gridPanel;
    private final JLabel hexagonPropsLabel;

    public HexagonViewer() {
        super("Hexagon Example viewer");

        setSize(1400, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        hexagonPropsLabel = new JLabel();
        hexagonPropsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        gridPanel = new DrawingGridPanel();
        var gridPanelMargin = new EmptyBorder(10, 10, 10, 10);
        var grid = initGrid();


        var controlPanel = new ControlPanel(grid, this);

        var outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(gridPanelMargin);
        gridPanel.setBackground(Color.gray);
        outerPanel.add(gridPanel);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(controlPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(hexagonPropsLabel);
        mainPanel.add(outerPanel);

        add(mainPanel);

        pack();
    }

    public void toggleCoordinates(boolean value, Class<?> coordinatesType) {
        gridPanel.toggleCoordinates(value, coordinatesType);
        gridPanel.repaint();
    }

    HexagonGrid<?> initGrid(Shape shape, Orientation orientation, double hexagonWidth, double hexagonHeight, double side, double offsetX, double offsetY) {
        var hexagonGridBuilder = new HexagonGrid.HexagonGridBuilder<>();
        if (hexagonWidth != 0) {
            hexagonGridBuilder = hexagonGridBuilder.hexagonWidth(hexagonWidth);
        } else if (hexagonHeight != 0) {
            hexagonGridBuilder = hexagonGridBuilder.hexagonHeight(hexagonHeight);
        } else if (side != 0) {
            hexagonGridBuilder = hexagonGridBuilder.side(side);
        }
        var hexagonGrid = hexagonGridBuilder.shape(shape, orientation)
                .hexagonDataObjectHook(hexagon -> OffsetCoordinates.fromCube(orientation, hexagon))
                .offsetX(offsetX)
                .offsetY(offsetY)
                .build();

        hexagonGrid.generateHexagons();
        var gridLayout = hexagonGrid.getGridLayout();
        hexagonPropsLabel.setText(
                "Hexagon: width " + floor(gridLayout.getHexagonLayout().getHexagonWidth())
                        + "px, height " + floor(gridLayout.getHexagonLayout().getHexagonHeight())
                        + "px, side " + floor(gridLayout.getHexagonLayout().getSide()) + "px. Total count: "
                        + gridLayout.getShape().getGridSize());

        gridPanel.setGrid(hexagonGrid);
        gridPanel.repaint();

        return hexagonGrid;
    }

    HexagonGrid<?> initGrid() {
        return initGrid(new HexagonalShape(3), Orientation.FLAT, 0, 0, 75, getWidth() / 2f - 75, getHeight() / 2f - 75);
    }


    private float floor(double value) {
        return (float) value;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                var viewer = new HexagonViewer();
                viewer.setVisible(true);
                viewer.setExtendedState(viewer.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            }
        });
    }

}

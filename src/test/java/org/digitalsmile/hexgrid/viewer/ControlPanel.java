package org.digitalsmile.hexgrid.viewer;

import org.digitalsmile.hexgrid.HexagonGrid;
import org.digitalsmile.hexgrid.coordinates.DoubledCoordinates;
import org.digitalsmile.hexgrid.coordinates.OffsetCoordinates;
import org.digitalsmile.hexgrid.layout.GridLayout;
import org.digitalsmile.hexgrid.layout.Orientation;
import org.digitalsmile.hexgrid.shapes.hexagonal.HexagonalShape;
import org.digitalsmile.hexgrid.shapes.rectangle.RectangleShape;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.IntStream;

public class ControlPanel extends JPanel {
    private final HexagonViewer hexagonViewer;

    private final JSlider hexagonWidthSlider;


    private final ButtonGroup hexagonOrientation;
    private final JRadioButton hexagonOrientationFlat;
    private final JRadioButton hexagonOrientationPointy;

    private final ButtonGroup gridShape;
    private final JRadioButton gridRectangleShape;
    private final JRadioButton gridHexagonalShape;

    private final JLabel gridParamHelperLabel = new JLabel();
    private final JSpinner mapRadius;
    private final JSpinner rowCount;
    private final JSpinner colCount;

    private final JSlider offsetXSlider;
    private final JSlider offsetYSlider;



    public ControlPanel(HexagonGrid<?> grid, HexagonViewer hexagonViewer) {
        this.hexagonViewer = hexagonViewer;

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        var gridLayout = grid.getGridLayout();

        var hexagonPropsPanel = new JPanel();
        hexagonPropsPanel.setLayout(new BoxLayout(hexagonPropsPanel, BoxLayout.X_AXIS));
        hexagonPropsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Hexagon Width"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        var labels = new Hashtable<Integer, JLabel>();
        IntStream.iterate(50, i -> i <= 200, i -> i + 10)
                .forEach(value -> labels.put(value, new JLabel(String.valueOf(value))));
        var hexagonWidthSliderModel = new DefaultBoundedRangeModel(150, 0, 50, 200);
        hexagonWidthSlider = new JSlider(hexagonWidthSliderModel);
        hexagonWidthSlider.setLabelTable(labels);
        hexagonWidthSlider.setPaintLabels(true);

        var hexagonOrientationPanel = new JPanel();
        hexagonOrientationPanel.setLayout(new BoxLayout(hexagonOrientationPanel, BoxLayout.PAGE_AXIS));
        hexagonOrientationPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Hexagon Orientation"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        hexagonOrientation = new ButtonGroup();
        hexagonOrientationFlat = new JRadioButton(Orientation.FLAT.name());
        hexagonOrientationPointy = new JRadioButton(Orientation.POINTY.name());
        hexagonOrientation.add(hexagonOrientationFlat);
        hexagonOrientation.add(hexagonOrientationPointy);

        var gridShapePanel = new JPanel();
        gridShapePanel.setLayout(new BoxLayout(gridShapePanel, BoxLayout.Y_AXIS));
        gridShapePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Hexagon Shape"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        gridShape = new ButtonGroup();
        gridRectangleShape = new JRadioButton("Rectangle");
        gridHexagonalShape = new JRadioButton("Hexagonal");
        gridShape.add(gridRectangleShape);
        gridShape.add(gridHexagonalShape);
        gridShapePanel.add(gridRectangleShape);
        gridShapePanel.add(gridHexagonalShape);


        var gridShapesParamPanel = new JPanel();
        gridShapesParamPanel.setLayout(new BoxLayout(gridShapesParamPanel, BoxLayout.Y_AXIS));
        gridShapesParamPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Grid Shapes Params"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        mapRadius = new JSpinner(new SpinnerNumberModel(3, 2, 1000, 1));
        rowCount = new JSpinner(new SpinnerNumberModel(3, 2, 1000, 1));
        colCount = new JSpinner(new SpinnerNumberModel(3, 2, 1000, 1));
        enableSpinnerFormatter(List.of(mapRadius, rowCount, colCount));
        gridShapesParamPanel.add(gridParamHelperLabel);
        gridShapesParamPanel.add(mapRadius);
        gridShapesParamPanel.add(rowCount);
        gridShapesParamPanel.add(colCount);

        var labels2 = new Hashtable<Integer, JLabel>();
        IntStream.iterate(0, i -> i <= 2000, i -> i + 200)
                .forEach(value -> labels2.put(value, new JLabel(String.valueOf(value))));
        var offsetParamPanel = new JPanel();
        offsetParamPanel.setLayout(new BoxLayout(offsetParamPanel, BoxLayout.Y_AXIS));
        offsetParamPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Offset X and Y Params"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        var offsetXSliderModel = new DefaultBoundedRangeModel(hexagonViewer.getWidth() / 2, 0, 0, 2000);
        offsetXSlider = new JSlider(offsetXSliderModel);
        offsetXSlider.setLabelTable(labels2);
        offsetXSlider.setPaintLabels(true);
        var offsetYSliderModel = new DefaultBoundedRangeModel(hexagonViewer.getHeight() / 2, 0, 0, 2000);
        offsetYSlider = new JSlider(offsetYSliderModel);
        offsetYSlider.setLabelTable(labels2);
        offsetYSlider.setPaintLabels(true);
        offsetParamPanel.add(offsetXSlider);
        offsetParamPanel.add(offsetYSlider);

        var coordinatesParamPanel = new JPanel();
        coordinatesParamPanel.setLayout(new BoxLayout(coordinatesParamPanel, BoxLayout.Y_AXIS));
        coordinatesParamPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Coordinates"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        var coordinatesGroup = new ButtonGroup();
        var cubeCoordinates = new JRadioButton("Cube");
        var offsetCoordinates = new JRadioButton("Offset");
        var doubledCoordinates = new JRadioButton("Doubled");
        coordinatesGroup.add(cubeCoordinates);
        coordinatesGroup.add(offsetCoordinates);
        coordinatesGroup.add(doubledCoordinates);
        cubeCoordinates.setSelected(true);

        var showCoordinates = new JCheckBox("Show coordinates on hexagons");
        showCoordinates.setSelected(true);
        showCoordinates.addItemListener(event -> {
            var value = ((JCheckBox) event.getSource()).isSelected();
            String selected = "";
            for (Enumeration<AbstractButton> buttons = coordinatesGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();

                if (button.isSelected()) {
                    selected = button.getText();
                    break;
                }
            }

            if (selected.equals("Offset")) {
                hexagonViewer.toggleCoordinates(value, OffsetCoordinates.class);
            } else if (selected.equals("Doubled")) {
                hexagonViewer.toggleCoordinates(value, DoubledCoordinates.class);
            } else {
                hexagonViewer.toggleCoordinates(value, null);
            }
            cubeCoordinates.setEnabled(value);
            offsetCoordinates.setEnabled(value);
            doubledCoordinates.setEnabled(value);
        });
        cubeCoordinates.addActionListener(event -> {
            hexagonViewer.toggleCoordinates(true, null);
        });
        offsetCoordinates.addActionListener(event -> {
            hexagonViewer.toggleCoordinates(true, OffsetCoordinates.class);
        });
        doubledCoordinates.addActionListener(event -> {
            hexagonViewer.toggleCoordinates(true,DoubledCoordinates.class);
        });
        coordinatesParamPanel.add(showCoordinates);
        coordinatesParamPanel.add(cubeCoordinates);
        coordinatesParamPanel.add(offsetCoordinates);
        coordinatesParamPanel.add(doubledCoordinates);


        hexagonPropsPanel.add(hexagonWidthSlider);
        hexagonOrientationPanel.add(hexagonOrientationFlat);
        hexagonOrientationPanel.add(hexagonOrientationPointy);
        gridShapePanel.add(gridHexagonalShape);
        gridShapePanel.add(gridRectangleShape);
        add(hexagonPropsPanel);
        add(hexagonOrientationPanel);
        add(gridShapePanel);
        add(gridShapesParamPanel);
        add(offsetParamPanel);
        add(coordinatesParamPanel);

        reInitValues(grid);
    }

    private void reInitValues(HexagonGrid<?> grid) {
        removeChangeListeners(List.of(hexagonWidthSlider, offsetXSlider, offsetYSlider));
        removeActionListeners(List.of(hexagonOrientationFlat, hexagonOrientationPointy, gridHexagonalShape, gridRectangleShape));
        removeChangeListenersSpinners(List.of(mapRadius, rowCount, colCount));
        var gridLayout = grid.getGridLayout();

        hexagonWidthSlider.setValue((int) gridLayout.getHexagonLayout().getHexagonWidth());
        offsetXSlider.setValue((int) gridLayout.getHexagonLayout().getOffset().x());
        offsetYSlider.setValue((int) gridLayout.getHexagonLayout().getOffset().y());
        if (gridLayout.getHexagonLayout().getOrientation().equals(Orientation.FLAT)) {
            hexagonOrientation.setSelected(hexagonOrientationFlat.getModel(), true);
            hexagonOrientation.setSelected(hexagonOrientationPointy.getModel(), false);
        } else {
            hexagonOrientation.setSelected(hexagonOrientationPointy.getModel(), true);
            hexagonOrientation.setSelected(hexagonOrientationFlat.getModel(), false);
        }
        if (gridLayout.getShape() instanceof HexagonalShape shape) {
            gridShape.setSelected(gridHexagonalShape.getModel(), true);
            gridShape.setSelected(gridRectangleShape.getModel(), false);

            gridParamHelperLabel.setText("Hexagonal Radius (2-1000):");

            mapRadius.setValue(shape.mapRadius());
            mapRadius.setVisible(true);
            rowCount.setVisible(false);
            colCount.setVisible(false);
        } else if (gridLayout.getShape() instanceof RectangleShape shape) {
            gridShape.setSelected(gridRectangleShape.getModel(), true);
            gridShape.setSelected(gridHexagonalShape.getModel(), false);

            gridParamHelperLabel.setText("Rectangle Bounds X and Y (2-1000):");

            mapRadius.setVisible(false);
            rowCount.setVisible(true);
            colCount.setVisible(true);
            rowCount.setValue(shape.getRows());
            colCount.setValue(shape.getCols());
        }

        hexagonWidthSlider.addChangeListener(event -> {
            var value = ((JSlider) event.getSource()).getValue();
            var offset = gridLayout.getHexagonLayout().getOffset();
            var newGrid = hexagonViewer.initGrid(gridLayout.getShape(), gridLayout.getHexagonLayout().getOrientation(),
                    value, 0, 0, offset.x(), offset.y());
            reInitValues(newGrid);
        });
        offsetXSlider.addChangeListener(event -> {
            var value = ((JSlider) event.getSource()).getValue();
            var offset = gridLayout.getHexagonLayout().getOffset();
            var newGrid = hexagonViewer.initGrid(gridLayout.getShape(), gridLayout.getHexagonLayout().getOrientation(),
                    gridLayout.getHexagonLayout().getHexagonWidth(), 0, 0, value, offset.y());
            reInitValues(newGrid);
        });
        offsetYSlider.addChangeListener(event -> {
            var value = ((JSlider) event.getSource()).getValue();
            var offset = gridLayout.getHexagonLayout().getOffset();
            var newGrid = hexagonViewer.initGrid(gridLayout.getShape(), gridLayout.getHexagonLayout().getOrientation(),
                    gridLayout.getHexagonLayout().getHexagonWidth(), 0, 0, offset.x(), value);
            reInitValues(newGrid);
        });
        hexagonOrientationFlat.addActionListener(event -> {
            var value = Orientation.valueOf(event.getActionCommand());
            var offset = gridLayout.getHexagonLayout().getOffset();
            var newGrid = hexagonViewer.initGrid(gridLayout.getShape(), value,
                    gridLayout.getHexagonLayout().getHexagonWidth(), 0, 0, offset.x(), offset.y());
            reInitValues(newGrid);
        });
        hexagonOrientationPointy.addActionListener(event -> {
            var value = Orientation.valueOf(event.getActionCommand());
            var offset = gridLayout.getHexagonLayout().getOffset();
            var newGrid = hexagonViewer.initGrid(gridLayout.getShape(), value,
                    gridLayout.getHexagonLayout().getHexagonWidth(), 0, 0, offset.x(), offset.y());
            reInitValues(newGrid);
        });
        gridRectangleShape.addActionListener(event -> {
            var rows = (int) rowCount.getValue();
            var cols = (int) colCount.getValue();
            var value = new RectangleShape(rows, cols);
            var offset = gridLayout.getHexagonLayout().getOffset();
            var newGrid = hexagonViewer.initGrid(value, gridLayout.getHexagonLayout().getOrientation(),
                    gridLayout.getHexagonLayout().getHexagonWidth(), 0, 0, offset.x(), offset.y());
            reInitValues(newGrid);
        });
        gridHexagonalShape.addActionListener(event -> {
            var radius = (int) mapRadius.getValue();
            var value = new HexagonalShape(radius);
            var offset = gridLayout.getHexagonLayout().getOffset();
            var newGrid = hexagonViewer.initGrid(value, gridLayout.getHexagonLayout().getOrientation(),
                    gridLayout.getHexagonLayout().getHexagonWidth(), 0, 0, offset.x(), offset.y());
            reInitValues(newGrid);
        });
        mapRadius.addChangeListener(new HexagonalSpinnerChangeListener(gridLayout));
        rowCount.addChangeListener(new RectangleSpinnerChangeListener(gridLayout, true));
        colCount.addChangeListener(new RectangleSpinnerChangeListener(gridLayout, false));


    }

    private class RectangleSpinnerChangeListener implements ChangeListener {
        private final GridLayout<?> gridLayout;
        private final boolean isRow;

        private RectangleSpinnerChangeListener(GridLayout<?> gridLayout, boolean isRow) {
            this.gridLayout = gridLayout;
            this.isRow = isRow;
        }

        @Override
        public void stateChanged(ChangeEvent event) {
            int rows;
            int cols;
            if (isRow) {
                rows = (int) ((JSpinner) event.getSource()).getValue();
                cols = (int) colCount.getValue();
            } else {
                rows = (int) rowCount.getValue();
                cols = (int) ((JSpinner) event.getSource()).getValue();
            }
            var value = new RectangleShape(rows, cols);
            var offset = gridLayout.getHexagonLayout().getOffset();
            var newGrid = hexagonViewer.initGrid(value, gridLayout.getHexagonLayout().getOrientation(),
                    gridLayout.getHexagonLayout().getHexagonWidth(), 0, 0, offset.x(), offset.y());
            reInitValues(newGrid);
        }
    }

    private class HexagonalSpinnerChangeListener implements ChangeListener {
        private final GridLayout<?> gridLayout;

        private HexagonalSpinnerChangeListener(GridLayout<?> gridLayout) {
            this.gridLayout = gridLayout;
        }

        @Override
        public void stateChanged(ChangeEvent event) {
            var radius = (int) ((JSpinner) event.getSource()).getValue();
            var value = new HexagonalShape(radius);
            var offset = gridLayout.getHexagonLayout().getOffset();
            var newGrid = hexagonViewer.initGrid(value, gridLayout.getHexagonLayout().getOrientation(),
                    gridLayout.getHexagonLayout().getHexagonWidth(), 0, 0, offset.x(), offset.y());
            reInitValues(newGrid);
        }
    }

    private void removeChangeListeners(List<JSlider> sliders) {
        for (JSlider slider: sliders) {
            for (ChangeListener listener : slider.getChangeListeners()) {
                slider.removeChangeListener(listener);
            }
        }
    }

    private void removeChangeListenersSpinners(List<JSpinner> spinners) {
        for (JSpinner spinner: spinners) {
            for (ChangeListener listener : spinner.getChangeListeners()) {
                if (listener instanceof HexagonalSpinnerChangeListener ||
                    listener instanceof RectangleSpinnerChangeListener) {
                    spinner.removeChangeListener(listener);
                }
            }
        }
    }

    private void removeActionListeners(List<AbstractButton> buttons) {
        for (AbstractButton button: buttons) {
            for (ActionListener listener : button.getActionListeners()) {
                button.removeActionListener(listener);
            }
        }
    }

    private void enableSpinnerFormatter(List<JSpinner> jSpinners) {
        for (JSpinner jSpinner : jSpinners) {
            var comp = jSpinner.getEditor();
            var field = (JFormattedTextField) comp.getComponent(0);
            var formatter = (DefaultFormatter) field.getFormatter();
            formatter.setCommitsOnValidEdit(true);
        }
    }

}

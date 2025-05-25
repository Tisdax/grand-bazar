package userInterface;

import userInterface.TableConstructs.LoyaltyCardTable;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LoyaltyCardPanel extends JPanel {
    private JLabel titleLabel;;
    private LoyaltyCardTable loyaltyCardTable;
    private JSpinner minPoints;
    private JSpinner maxPoints;
    private JLabel minPointsLabel;
    private JLabel maxPointsLabel;

    public LoyaltyCardPanel() {
        setLayout(new BorderLayout(0, 50));
        setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        JPanel pointsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loyaltyCardTable = new LoyaltyCardTable();

        // min points
        minPointsLabel = new JLabel("Minimum de points :");
        minPoints = new JSpinner(new SpinnerNumberModel(0, 0, 100000, 100));
        pointsPanel.add(minPointsLabel);
        pointsPanel.add(minPoints);

        // max points
        maxPointsLabel = new JLabel("Maximum de points :");
        maxPoints = new JSpinner(new SpinnerNumberModel(1000, 100, 100000, 100));
        pointsPanel.add(maxPointsLabel);
        pointsPanel.add(maxPoints);

        minPoints.addChangeListener(e -> { dataUpdate(); });
        maxPoints.addChangeListener(e -> {
            int maxPointsValue = ((Number) maxPoints.getValue()).intValue();
            int minPointsValue = ((Number) minPoints.getValue()).intValue();
            SpinnerModel minPointsModel = new SpinnerNumberModel(
                    Math.min(minPointsValue, maxPointsValue), 0, maxPointsValue, 100);
            minPoints.setModel(minPointsModel);
            dataUpdate();
        });

        titleLabel = new JLabel("Infos sur les clients selon nombre de points de fidélités :");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        dataUpdate();
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(pointsPanel, BorderLayout.CENTER);
        this.add(new JScrollPane(loyaltyCardTable.getTable()), BorderLayout.SOUTH);
    }
    private void dataUpdate() {
        int minPointsValue = ((Number) minPoints.getValue()).intValue();
        int maxPointsValue = ((Number) maxPoints.getValue()).intValue();
        loyaltyCardTable.refreshTable();
        loyaltyCardTable.fillTable(minPointsValue, maxPointsValue);
    }
}

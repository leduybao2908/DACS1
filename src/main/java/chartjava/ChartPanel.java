package chartjava;

import java.awt.Color; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import chartjava.chart.ModelChart;

public class ChartPanel extends JPanel {

    private chartjava.chart.CurveLineChart chart;
    private chartjava.panel.PanelShadow panelShadow1;

    public ChartPanel() {
        initComponents();
        chart.setTitle("Chart Data");
        chart.addLegend("Amount", Color.decode("#ffffff"), Color.decode("#b33232"));
        chart.addLegend("Cost", Color.decode("#e65c00"), Color.decode("#F9D423"));
        chart.addLegend("Profit", Color.decode("#0099F7"), Color.decode("#F11712"));
        setData();
    }

    private void setData() {
        try {
            List<ModelData> lists = new ArrayList<>();
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            dbConnection.connectToDatabase();
            String sql = "SELECT DATE_FORMAT(Date, '%M') AS `Month`, " +
                         "SUM(TotalAmount) AS Amount, " +
                         "SUM(TotalCost) AS Cost, " +
                         "SUM(TotalProfit) AS Profit " +
                         "FROM orders " +
                         "GROUP BY DATE_FORMAT(Date, '%M'), DATE_FORMAT(Date, '%Y') " +
                         "ORDER BY MAX(Date) DESC " +
                         "LIMIT 7";
            PreparedStatement p = dbConnection.getConnection().prepareStatement(sql);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                String month = r.getString("Month");
                double amount = r.getDouble("Amount");
                double cost = r.getDouble("Cost");
                double profit = r.getDouble("Profit");
                lists.add(new ModelData(month, amount, cost, profit));
            }
            r.close();
            p.close();
            // Add Data to chart
            for (int i = lists.size() - 1; i >= 0; i--) {
                ModelData d = lists.get(i);
                chart.addData(new ModelChart(d.getMonth(), new double[]{d.getAmount(), d.getCost(), d.getProfit()}));
            }
            // Start to show data with animation
            chart.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        panelShadow1 = new chartjava.panel.PanelShadow();
        chart = new chartjava.chart.CurveLineChart();

        panelShadow1.setBackground(new java.awt.Color(34, 59, 69));
        panelShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow1.setColorGradient(new java.awt.Color(17, 38, 47));

        chart.setForeground(new java.awt.Color(237, 237, 237));
        chart.setFillColor(true);

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
}

package view.subView;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.mainView.homeWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class employeeDetailInfoFr extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel employee_name_label;
    public JLabel employee_name;
    public JTextField employee_phone;
    public JTable receipts_listing_table;

    public employeeDetailInfoFr(homeWindow hw) {
        this.setTitle("employeeDetailInfoFr");
        this.setSize(500, 450);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        );

        employee_name_label = new JLabel("Tên nhân viên:");
        employee_name = new JLabel("New label");
        JLabel lblNewLabel = new JLabel("Số điện thoại:");
        employee_phone = new JTextField();
        employee_phone.setColumns(10);
        JLabel lblNewLabel_1 = new JLabel("Danh sách hóa đơn:");
        JScrollPane scrollPane = new JScrollPane();

        JButton btnNewButton = new JButton("Lưu thay đổi");
        JButton btnNewButton_1 = new JButton("Thêm mới hóa đơn");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new newReceiptAddingFr(employee_name.getText() + " - " + employee_phone.getText());
            }
        });

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(24)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addComponent(employee_name_label, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                            .addGap(68)
                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addComponent(employee_name)
                                .addComponent(employee_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                            .addGroup(gl_panel.createSequentialGroup()
                                .addComponent(btnNewButton_1)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnNewButton))))
                    .addContainerGap(126, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(employee_name_label)
                        .addComponent(employee_name))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(employee_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblNewLabel_1)
                    .addGap(18)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnNewButton)
                        .addComponent(btnNewButton_1))
                    .addContainerGap(26, Short.MAX_VALUE))
        );

        receipts_listing_table = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;
            Class[] columnTypes = new Class[]{String.class, Float.class};

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[]{false, false};

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        };
        model.setColumnIdentifiers(new Object[]{"Mã hóa đơn", "Tổng doanh thu"});
        receipts_listing_table.setModel(model);
        receipts_listing_table.getColumnModel().getColumn(0).setPreferredWidth(33);
        scrollPane.setViewportView(receipts_listing_table);
        receipts_listing_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String orderId = (String) receipts_listing_table.getValueAt(receipts_listing_table.getSelectedRow(), 0);
                new receiptDetailFr(orderId);
            }
        });
        panel.setLayout(gl_panel);
        getContentPane().setLayout(groupLayout);
        this.setVisible(true);
    }
}

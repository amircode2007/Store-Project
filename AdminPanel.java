package ui;

import data.DataStore;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminPanel extends JPanel {

    private ArrayList<Product> products;
    private DefaultTableModel model;
    private JTable table;

    public AdminPanel() {
        setLayout(new BorderLayout());

        products = DataStore.loadProducts();

        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Category", "Price", "Stock"}, 0);
        table = new JTable(model);

        refreshTable();

        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        addBtn.addActionListener(e -> addProduct());
        editBtn.addActionListener(e -> editProduct());
        deleteBtn.addActionListener(e -> deleteProduct());

        JPanel bottom = new JPanel();
        bottom.add(addBtn);
        bottom.add(editBtn);
        bottom.add(deleteBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private void addProduct() {
        String name = JOptionPane.showInputDialog("Name:");
        String category = JOptionPane.showInputDialog("Category:");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Price:"));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock:"));

        int id = products.size() + 1;
        products.add(new Product(id, name, category, price, stock));

        DataStore.saveProducts(products);
        refreshTable();
    }

    private void editProduct() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        Product p = products.get(row);

        p.setName(JOptionPane.showInputDialog("Name:", p.getName()));
        p.setCategory(JOptionPane.showInputDialog("Category:", p.getCategory()));
        p.setPrice(Double.parseDouble(
                JOptionPane.showInputDialog("Price:", p.getPrice())));
        p.setStock(Integer.parseInt(
                JOptionPane.showInputDialog("Stock:", p.getStock())));

        DataStore.saveProducts(products);
        refreshTable();
    }

    private void deleteProduct() {
        int row = table.getSelectedRow();
        if (row != -1) {
            products.remove(row);
            DataStore.saveProducts(products);
            refreshTable();
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (Product p : products) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getCategory(),
                    p.getPrice(),
                    p.getStock()
            });
        }
    }
}

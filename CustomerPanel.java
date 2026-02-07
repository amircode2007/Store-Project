package ui;

import data.DataStore;
import model.Product;
import model.ShoppingCart;
import util.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CustomerPanel extends JPanel {

    private ShoppingCart cart = new ShoppingCart();
    private ArrayList<Product> products;
    private DefaultTableModel model;
    private JTable table;

    public CustomerPanel(JFrame parentFrame) {
        setLayout(new BorderLayout());

        products = DataStore.loadProducts();

        model = new DefaultTableModel(
                new String[]{"Name", "Category", "Price", "Stock"}, 0);
        table = new JTable(model);

        refreshTable();

        JButton addToCart = new JButton("Add to Cart");
        JButton viewCart = new JButton("View Cart");
        JButton logoutBtn = new JButton("Logout");

        addToCart.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1 && products.get(row).getStock() > 0) {
                cart.addProduct(products.get(row));
            }
        });

        viewCart.addActionListener(e ->
                new CartPanel(parentFrame, cart, products, this::refreshTable));

        logoutBtn.addActionListener(e ->
                SessionManager.logout(parentFrame));

        JPanel bottom = new JPanel();
        bottom.add(addToCart);
        bottom.add(viewCart);
        bottom.add(logoutBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    public void refreshTable() {
        products = DataStore.loadProducts();
        model.setRowCount(0);
        for (Product p : products) {
            model.addRow(new Object[]{
                    p.getName(),
                    p.getCategory(),
                    p.getPrice(),
                    p.getStock()
            });
        }
    }
}

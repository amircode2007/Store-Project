package ui;

import data.DataStore;
import model.CartItem;
import model.Product;
import model.ShoppingCart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CartPanel extends JDialog {

    public CartPanel(JFrame parent,
                     ShoppingCart cart,
                     ArrayList<Product> products,
                     Runnable onBack) {

        super(parent, "Shopping Cart", true);
        setSize(450, 300);
        setLocationRelativeTo(parent);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Name", "Category", "Price", "Quantity", "Total"}, 0);
        JTable table = new JTable(model);

        for (CartItem item : cart.getItems()) {
            model.addRow(new Object[]{
                    item.getProduct().getName(),
                    item.getProduct().getCategory(),
                    item.getProduct().getPrice(),
                    item.getQuantity(),
                    item.getTotalPrice()
            });
        }

        JLabel totalLabel = new JLabel("Total: " + cart.getTotalPrice());

        JButton checkoutBtn = new JButton("Checkout");
        JButton backBtn = new JButton("Back to Products");

        checkoutBtn.addActionListener(e -> {
            // کم کردن موجودی فقط اینجا
            for (CartItem item : cart.getItems()) {
                Product p = item.getProduct();
                p.setStock(p.getStock() - item.getQuantity());
            }

            DataStore.saveProducts(products);
            cart.clear();

            JOptionPane.showMessageDialog(this, "Purchase successful!");
            dispose();
            onBack.run();
        });

        backBtn.addActionListener(e -> dispose());

        JPanel bottom = new JPanel();
        bottom.add(backBtn);
        bottom.add(checkoutBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(totalLabel, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }
}

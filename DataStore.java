package data;

import model.Product;


import java.io.*;
import java.util.ArrayList;


public class DataStore {
    private static final String FILE_NAME = "products.csv";


    public static ArrayList<Product> loadProducts() {
        ArrayList<Product> products = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                products.add(new Product(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        Double.parseDouble(data[3]),
                        Integer.parseInt(data[4])
                ));
            }
        } catch (IOException e) {
// فایل وجود نداشت
        }


        return products;
    }


    public static void saveProducts(ArrayList<Product> products) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Product p : products) {
                pw.println(p.toCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.text.*;

/*make arraylist, scan through the code, and in the while loop after hasnext save the line from before as a value within the arraylist, then say if arraylist.contains(word), -> discount*/
public class CashRegister {
    public String priceFilename;
    public String discountFilename;
    public HashMap<String, Item> productMap = new HashMap<>();
    public HashMap<String, Discount> discountMap = new HashMap<>();
    public HashMap<String, Integer> quantityMap = new HashMap<>();
    public boolean hasDiscount;

    public CashRegister(String priceFilename, String discountFilename) {
        this.priceFilename = priceFilename;
        this.discountFilename = discountFilename;
    }

    //1
    public void readItems() {

        try {
            File file = new File(priceFilename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] list = line.split(",");
                String barcode = list[0];
                String category = list[1];
                String name = list[2];
                int kr = Integer.parseInt(list[3]);
                int øre = Integer.parseInt(list[4]);
                int price = kr * 100 + øre;
                Item item = new Item(barcode, category, name, price);
                productMap.put(barcode, item);
            }
        } catch
        (IOException e) {
            System.out.print("File not found!");
        }
    }

    //2
    public void readDiscounts() {

        try {
            File file = new File(discountFilename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] list = line.split(",");
                String barcode = list[0];
                int limit = Integer.parseInt(list[1]);
                int kr = Integer.parseInt(list[2]);
                int øre = Integer.parseInt(list[3]);
                int price = kr * 100 + øre;
                Discount disc = new Discount(barcode, limit, price);
                discountMap.put(barcode, disc);
            }
        } catch
        (IOException e) {
            System.out.print("File not found!");
        }
    }

    //3
    public void countItems(String barcodeFilename) {
        try {
            File file = new File(barcodeFilename);
            Scanner scanner = new Scanner(file);
            int quantity = 0;
            while (scanner.hasNextLine()) {
                String barcode = scanner.nextLine();
                if (quantityMap.containsKey(barcode)) {
                    int q = quantityMap.get(barcode);
                    quantityMap.put(barcode, q++);
                    hasDiscount = true;
                }
                quantityMap.put(barcode, 1);
            }
        }
        catch
        (IOException e) {
            System.out.print("File not found!");
        }
    }

    //4. ((TOTAL DIVIDED BY 50))
    public void calculatePrice(String barcodeFilename) {
        try {
            File file = new File(barcodeFilename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (quantityMap.containsKey(line)) {
                    int q = quantityMap.get(line);
                    quantityMap.put(line, q++);
                }
                else quantityMap.put(line, 1);

                productMap.get(line);
                discountMap.get(line);
                if (quantityMap.get(line).compareTo(discountMap.get(line).limit) >= 0){
                    hasDiscount = true;
                }
            }
        }
         catch
         (IOException e) {
             System.out.print("File not found!");
         }

     }

                //for loop each category, hasdiscount? if it does, print
    // comparable: "if (category == "BOLIG") System.out.print... then next, and next..."




    private void createReceipt() {
        sortPurchases();
        String currentCategory = "";
        for (String s : listOfPurchases) {
            String[] temp = s.split(",");
            String category = temp[0];
            String name = temp[1];
            double price = Double.parseDouble(temp[2]) + (Double.parseDouble(temp[3]) / 100);
            double itemDiscount = 0;
            String barcode = temp[4];
            int amount = mapOfPurchases.get(barcode);
            boolean hasDiscount = discounts.containsKey(barcode) && amount >= Integer.parseInt(discounts.get(barcode)[1]);
            if (hasDiscount) {
                itemDiscount = price - (Double.parseDouble(discounts.get(barcode)[2]) + (Double.parseDouble(discounts.get(barcode)[3]) / 100));
            }
            if (!category.equals(currentCategory)) {
                System.out.printf("%n%-38s%n", categoryFormat(category));
                currentCategory = category;
            }
            System.out.printf("%-19s",name);
            if (amount == 1) {
                if (name.length() > 19) System.out.printf("%18s%n",format(price));
                else System.out.printf("%19s%n",format(price));
            } else {
                System.out.printf("%n%-19s%19s%n","  " + amount + " x " + format(price), format(amount * price));
            }
            if (hasDiscount) {
                System.out.printf("%-19s%19s-%n", "RABAT", format(amount * itemDiscount));
            }
            subtotal = subtotal + (price * amount);
            totalDiscount = totalDiscount + (itemDiscount * amount);
        }


    public static void main(String[] args) {
        CashRegister cr = new CashRegister("input/prices.txt", "input/discounts.txt");
        cr.readItems();
        cr.readDiscounts();

        System.out.println();
    }
}



import java.io.File;
import java.io.IOException;
import java.util.*;

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
            if (hasDiscount = true){
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
                quantityMap.put(line, 1);
                productMap.get(barcode);
                discountMap.get(barcode);
                if (quantity == limit){
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




    public void printReceipt(String barcodeFilename){
    }

    public static void main(String[] args) {
        CashRegister cr = new CashRegister("input/prices.txt", "input/discounts.txt");
        cr.readItems();
        cr.readDiscounts();

        System.out.println();
    }
}


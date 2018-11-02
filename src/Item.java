public class Item {
    String barcode;
    String category;
    String name;
    int price;
    boolean hasDiscount;
    private Discount discount;
    public Item(String barcode, String category, String name, int price){
        this.barcode = barcode;
        this.category = category;
        this.name = name;
        this.price = price;
    }



}

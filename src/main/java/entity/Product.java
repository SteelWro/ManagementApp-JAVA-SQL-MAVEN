package entity;

public class Product {
    private Long id;
    private String productName;
    private double price;
    private double weight;
    private String color;
    private int productCount;
    private static final String productType = "P";
    public static final String PRODUCT_SEPARATOR = "#";

    public Product(Long id, String productName, double price, double weight, String color, int productCount) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.color = color;
        this.productCount = productCount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public int getProductCount() {
        return productCount;
    }

    protected String getBasicToString(){
        return id + PRODUCT_SEPARATOR + productName + PRODUCT_SEPARATOR + price + PRODUCT_SEPARATOR + weight + PRODUCT_SEPARATOR + color + PRODUCT_SEPARATOR + productCount;
    }

    @Override
    public String toString() {
       return productType + PRODUCT_SEPARATOR + getBasicToString();
    }


}


public class Shoes {

    private String manufacturer;
    private double price;
    private double size;
    private String color;

    public Shoes(String manufacturer, double price, double size, String color) {
        this.manufacturer = manufacturer;
        this.price = price;
        this.size = size;
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
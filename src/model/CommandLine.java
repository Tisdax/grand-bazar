package model;

public class CommandLine {
    private Sale sale;
    private Product product;
    private Integer quantity;

    public CommandLine(Sale sale, Product product, Integer quantity) {
        this.sale = sale;
        this.product = product;
        setQuantity(quantity);
    }

    public void setQuantity(Integer quantity) {
        this.quantity = Math.max(quantity, 0);
    }
}

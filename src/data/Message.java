package data;

import java.io.Serializable;

public class Message implements Serializable {
    private String text;
    private String value;
    private Product product;

    public Message(String text, String value,Product product) {
        this.text = text;
        this.value = value;
        this.product = product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }
    public Product getProduct(){
        return product;
    }
    public String getValue() {
        return value;
    }
}

package org.ultra.domains.Products;

//Product POJO
public class Product {

    //Uses Integer because can be null until assigned by DB
    private Integer productId;
    private String skuNumber;
    private String productName;

    public Product(Integer productId, String skuNumber, String productName) {
        this.productId = productId;
        this.skuNumber = skuNumber;
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", skuNumber=" + skuNumber +
                ", productName=" + productName +
                '}';
    }

    public Integer getProductId() {
        return productId;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductId(int code) {
        this.productId = code;
    }
}

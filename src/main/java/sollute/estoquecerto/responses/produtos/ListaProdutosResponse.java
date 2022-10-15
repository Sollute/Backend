package sollute.estoquecerto.responses.produtos;

public class ListaProdutosResponse {

    private String productName;
    private Double productPrice;
    private Integer productQuantity;

    public ListaProdutosResponse(String productName, Double productPrice, Integer productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

//    public String getProductPhoto() {
//        return productPhoto;
//    }

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

//    public void setProductPhoto(String productPhoto) {
//        this.productPhoto = productPhoto;
//    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}

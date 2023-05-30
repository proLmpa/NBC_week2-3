package Kiosk.entity;

public class Sold {
    private float productPrice;
    private int saledCount;

    public Sold(float productPrice, int saledCount) {
        this.productPrice = productPrice;
        this.saledCount = saledCount;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public int getSaledCount() {
        return saledCount;
    }

    public void updateSaledCount(int saledCount){
        this.saledCount += saledCount;
    }
}

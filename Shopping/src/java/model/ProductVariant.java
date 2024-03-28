package model;

public class ProductVariant {
    private int colorId;
    private int sizeId;

    public ProductVariant(int colorId, int sizeId) {
        this.colorId = colorId;
        this.sizeId = sizeId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    @Override
    public String toString() {
        return "ProductVariant{" +
                "colorId=" + colorId +
                ", sizeId=" + sizeId +
                '}';
    }
}

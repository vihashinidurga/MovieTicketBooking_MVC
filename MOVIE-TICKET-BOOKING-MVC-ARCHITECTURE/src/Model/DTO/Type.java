package Model.DTO;

public class Type {
    private int typeId;
    private String Type;
    private int Price;

    public Type(int typeId, String type, int price) {
        this.typeId = typeId;
        Type = type;
        Price = price;
    }
    public Type(){}

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeId=" + typeId +
                ", Type='" + Type + '\'' +
                ", Price=" + Price +
                '}';
    }
}

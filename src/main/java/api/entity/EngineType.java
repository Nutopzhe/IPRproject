package api.entity;

//перечисление типов двигателя

public enum EngineType {
    DIESEL ("Diesel"),
    CNG ("CNG"),
    HYDROGENIC ("Hydrogenic"),
    ELECTRIC ("Electric"),
    PHEV ("PHEV"),
    GASOLINE ("Gasoline");

    private String nameType;

    EngineType(String nameType) {
        this.nameType = nameType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }
}

package api.utils;

public enum Cars {
    AUDI("Audi", "A3", "CNG"),
    BMW("BMW", "X7", "Diesel"),
    Tesla("Tesla", "Model S", "Electric"),
    Toyota("Toyota", "Land Cruiser", "Gasoline"),
    Nissan("Nissan", "Qashqai", "Diesel");

    private String mark;
    private String model;
    private String engineType;

    Cars(String mark, String model, String engineType) {
        this.mark = mark;
        this.model = model;
        this.engineType = engineType;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
}

package api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "car")
public class Car {
    @Id
    private Integer id;
    @Column(name = "type_name")
    private String engineType;
    private String mark;
    private String model;
    private Double price;

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", engineType='" + engineType + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id)
                && Objects.equals(engineType, car.engineType)
                && Objects.equals(mark, car.mark)
                && Objects.equals(model, car.model)
                && Objects.equals(price, car.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, engineType, mark, model, price);
    }
}

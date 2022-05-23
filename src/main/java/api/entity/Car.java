package api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Proxy(lazy = false)
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

    public Car setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getEngineType() {
        return engineType;
    }

    public Car setEngineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    public String getMark() {
        return mark;
    }

    public Car setMark(String mark) {
        this.mark = mark;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Car setPrice(Double price) {
        this.price = price;
        return this;
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

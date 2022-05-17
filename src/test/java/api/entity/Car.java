package api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "car")
public class Car {
    @Id
    private int id;
    private String engineType;
    private String mark;
    private String model;
    private Double price;
    @Column(name = "engine_type_id")
    private int engineTypeId;
    @Column(name = "person_id")
    private int personId;

    public Car() {
    }

    public Car(int id, String engineType, String mark, String model, Double price, int engineTypeId, int personId) {
        this.id = id;
        this.engineType = engineType;
        this.mark = mark;
        this.model = model;
        this.price = price;
        this.engineTypeId = engineTypeId;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getEngineTypeId() {
        return engineTypeId;
    }

    public void setEngineTypeId(int engineTypeId) {
        this.engineTypeId = engineTypeId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && engineTypeId == car.engineTypeId && personId == car.personId && Objects.equals(engineType, car.engineType) && Objects.equals(mark, car.mark) && Objects.equals(model, car.model) && Objects.equals(price, car.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, engineType, mark, model, price, engineTypeId, personId);
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
}

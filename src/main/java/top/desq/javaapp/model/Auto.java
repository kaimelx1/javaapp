package top.desq.javaapp.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Auto.ALL_SORTED, query = "SELECT a FROM Auto a WHERE a.user.id=:userId ORDER BY a.dateTime DESC"),
        @NamedQuery(name = Auto.DELETE, query = "DELETE FROM Auto a WHERE a.id=:id AND a.user.id=:userId"),
        @NamedQuery(name = Auto.GET_BETWEEN, query = "SELECT a FROM Auto a " +
                "WHERE a.user.id=:userId AND a.dateTime >= :startDateTime AND a.dateTime < :endDateTime ORDER BY a.dateTime DESC"),
})
@Entity
@Table(name = "auto", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "auto_unique_user_datetime_idx")})
public class Auto extends AbstractEntity {

    public static final String ALL_SORTED = "Auto.getAll";
    public static final String DELETE = "Auto.delete";
    public static final String GET_BETWEEN = "Auto.getBetween";

    @Column(name = "brand", nullable = false)
    @Enumerated(EnumType.STRING)
    //@NotBlank
    private Brand brand;

    @Column(name = "model", nullable = false)
    //@NotBlank
    private String model;

    @Column(name = "body", nullable = false)
    @Enumerated(EnumType.STRING)
    //@NotBlank
    private BodyStyle body;

    @Column(name = "color", nullable = false)
    @Enumerated(EnumType.STRING)
    //@NotBlank
    private Color color;

    @Column(name = "power", nullable = false)
    //@Range(min = 10, max = 5000)
    private Integer power;

    @Column(name = "fuel", nullable = false)
    @Enumerated(EnumType.STRING)
    //@NotBlank
    private FuelType fuel;

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    public Auto() {
    }

    public Auto(Integer id, Brand brand, String model, BodyStyle body, Color color, Integer power, FuelType fuel, LocalDateTime dateTime) {
        super(id);
        this.brand = brand;
        this.model = model;
        this.body = body;
        this.color = color;
        this.power = power;
        this.fuel = fuel;
        this.dateTime = dateTime;
    }

    public Auto(Brand brand, String model, BodyStyle body, Color color, Integer power, FuelType fuel, LocalDateTime dateTime) {
        this(null, brand, model, body, color, power, fuel, dateTime);
    }

    public Brand getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public BodyStyle getBody() {
        return body;
    }

    public Color getColor() {
        return color;
    }

    public Integer getPower() {
        return power;
    }

    public FuelType getFuel() {
        return fuel;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBody(BodyStyle body) {
        this.body = body;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public void setFuel(FuelType fuel) {
        this.fuel = fuel;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "brand=" + brand +
                ", model='" + model + '\'' +
                ", body=" + body +
                ", color=" + color +
                ", power=" + power +
                ", fuel=" + fuel +
                ", dateTime=" + dateTime +
                '}';
    }
}

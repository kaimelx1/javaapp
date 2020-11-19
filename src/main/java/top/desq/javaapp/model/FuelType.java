package top.desq.javaapp.model;

public enum FuelType {

    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    NOT_SELECTED("Not selected");

    private String title;

    FuelType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "FuelType{" +
                "title='" + title + '\'' +
                '}';
    }
}

package top.desq.javaapp.model;

public enum Brand {

    MAZDA("Mazda"),
    VW("Volkswagen"),
    HONDA("Honda"),
    TOYOTA("Toyota"),
    NOT_SELECTED("Not selected");

    private String title;

    Brand(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "title='" + title + '\'' +
                '}';
    }
}

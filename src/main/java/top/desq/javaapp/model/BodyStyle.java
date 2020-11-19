package top.desq.javaapp.model;


public enum BodyStyle {

    SEDAN("Saloon"),
    HATCHBACK("Hatchback"),
    COUPE("Coupe"),
    WAGON("Station wagon"),
    NOT_SELECTED("Not selected");

    private String title;

    BodyStyle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "BodyStyle{" +
                "title='" + title + '\'' +
                '}';
    }

}



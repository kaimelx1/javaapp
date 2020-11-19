package top.desq.javaapp.model;

public enum Color {

    RED("Red"),
    BLUE("Blue"),
    BLACK("Black"),
    WHITE("White"),
    SILVER("Silver"),
    NOT_SELECTED("Not selected");

    private String title;

    Color(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Color{" +
                "title='" + title + '\'' +
                '}';
    }
}

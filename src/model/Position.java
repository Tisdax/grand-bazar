package model;

public class Position {
    private Integer id;
    private String name;

    public Position(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Position(Integer id) {
        this(id, null);
    }
}
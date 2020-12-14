package ru.ifmo.se.lab3.pages.elems;

public class News {
    public final String id;
    public final String header;
    public final Integer rating;

    public News(String id, String header, Integer rating) {
        this.id = id;
        this.header = header;
        this.rating = rating;
    }
}

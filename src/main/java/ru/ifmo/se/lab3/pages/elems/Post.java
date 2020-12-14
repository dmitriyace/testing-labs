package ru.ifmo.se.lab3.pages.elems;

import java.util.List;

public class Post {
    public final String title;
    public final String text;
    public final List<String> tags;

    public Post(String title, String text, List<String> tags) {
        this.title = title;
        this.text = text;
        this.tags = tags;
    }
}

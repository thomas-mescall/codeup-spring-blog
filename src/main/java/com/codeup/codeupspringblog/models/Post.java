package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
//    private String title;
//    private String body;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


//    public Post(String title, String body) {
//        this.title = title;
//        this.body = body;
//    }

    public Post() {

    }

    @Column(nullable = false, length = 50)
        private String title;

    @Column(nullable = false)
    private String description;


    public String getTitle() {
        return title;
    }

//    public String getBody() {
//        return body;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public void setBody(String body) {
//        this.body = body;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

package org.example.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "todo_lists")
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public ToDoList() {}

    public ToDoList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

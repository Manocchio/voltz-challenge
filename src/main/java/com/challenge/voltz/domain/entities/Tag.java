package com.challenge.voltz.domain.entities;

import java.util.List;
import javax.persistence.*;

@Entity(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "tags")
    private List<Tool> tools;

    public Tag() {
    }

    public Tag(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Tag(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }
}

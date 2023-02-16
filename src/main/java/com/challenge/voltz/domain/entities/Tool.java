package com.challenge.voltz.domain.entities;

import com.challenge.voltz.domain.entities.enums.ToolStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tool")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;
    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tooltags",
            joinColumns = @JoinColumn(name = "tool_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ToolStatus status;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;


    public Tool() {}

    public Tool(String title, String link, String description, List<Tag> tags) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.tags = tags;
        this.status = ToolStatus.ACTIVE;
        this.created_at = Timestamp.valueOf(LocalDateTime.now());
        this.updated_at = Timestamp.valueOf(LocalDateTime.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public ToolStatus getStatus() {
        return status;
    }

    public void setStatus(ToolStatus status) {
        this.status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}

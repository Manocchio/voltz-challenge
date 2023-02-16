package com.challenge.voltz.application.web.payloads.requests;

import com.challenge.voltz.resources.utils.ResourceMapper;
import com.challenge.voltz.domain.entities.Tool;

import java.util.List;


public class CreateToolRequestV1 {

    private String title;
    private String link;
    private String description;

    private List<String> tags;

    public CreateToolRequestV1(String title, String link, String description, List<String> tags) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.tags = tags;
    }

    public Tool toDomain() {
        return new Tool(
                title = getTitle(),
                description = getDescription(),
                link = getLink(),
                ResourceMapper.toDomain(getTags())
        );
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}

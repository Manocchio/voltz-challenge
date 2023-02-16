package com.challenge.voltz.application.web.payloads.responses;

import com.challenge.voltz.resources.utils.ResourceMapper;
import com.challenge.voltz.domain.entities.Tool;
import com.challenge.voltz.domain.entities.enums.ToolStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class ToolResponseV1 {
    private Integer id;

    private String title;
    private String link;

    private String description;


    private List<String> tags;

    private ToolStatus status;

    private Timestamp created_at;

    private Timestamp updated_at;

    public ToolResponseV1() {
    }

    public ToolResponseV1(Tool tool) {
        this.id = tool.getId();
        this.title = tool.getTitle();
        this.description = tool.getDescription();
        this.link = tool.getLink();
        this.tags = ResourceMapper.toResponseV1(tool.getTags());
        this.status = tool.getStatus();
        this.created_at = tool.getCreated_at();
        this.updated_at = tool.getUpdated_at();
    }

    public static List<ToolResponseV1> tofindAllResponseV1(List<Tool> tools){
        return tools.stream().map(ToolResponseV1::new).collect(Collectors.toList());
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
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

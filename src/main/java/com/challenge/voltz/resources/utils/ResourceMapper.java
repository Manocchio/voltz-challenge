package com.challenge.voltz.resources.utils;

import com.challenge.voltz.domain.entities.Tag;

import java.util.List;
import java.util.stream.Collectors;


public class ResourceMapper {
    public static Tag toolValueOf(String description) {
        return new Tag(description);
    }

    public static List<String> toResponseV1(List<Tag> tagList){
        return tagList.stream().map(Tag::getDescription).collect(Collectors.toList());
    }

    public static List<Tag> toDomain(List<String> tagList){
        return tagList.stream().map(Tag::new).collect(Collectors.toList());
    }


}

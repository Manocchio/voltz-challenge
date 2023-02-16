package com.challenge.voltz.factory;

import com.challenge.voltz.domain.entities.Tag;
import com.challenge.voltz.domain.entities.Tool;

import java.util.List;

public class ToolFactory {

    public static Tool getSample() {
        Tool tool = new Tool(
                "default_title",
                "http://fakeurl.com",
                "cats can fly",
                List.of(new Tag("aa"), new Tag("bb"))
        );
        tool.setId(1);
        return tool;
    }

    public static Tool getInvalidTool() {
        return new Tool(
                "default_title",
                "http://fakeurl.com",
                "cats can fly",
                List.of(new Tag("bb"), new Tag("bb"))
        );
    }

}

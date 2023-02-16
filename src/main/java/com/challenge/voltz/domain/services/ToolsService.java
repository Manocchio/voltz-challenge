package com.challenge.voltz.domain.services;

import com.challenge.voltz.domain.entities.Tag;
import com.challenge.voltz.domain.entities.Tool;
import com.challenge.voltz.domain.entities.enums.ToolStatus;
import com.challenge.voltz.domain.exceptions.ToolNotFoundException;
import com.challenge.voltz.domain.repositories.TagRepository;
import com.challenge.voltz.domain.repositories.ToolRepository;
import com.challenge.voltz.domain.services.operations.ToolsServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToolsService implements ToolsServiceOperations {
    @Autowired
    ToolRepository toolRepository;

    @Autowired
    TagRepository tagRepository;

    @Override
    public Tool createTool(Tool tool) {
        tool.setTags(verifyDuplicatedTags(tool.getTags()));
        return toolRepository.save(tool);
    }

    @Override
    public List<Tool> getTools() {
        return toolRepository.findAll();
    }

    @Override
    public void deleteTool(Integer id) throws ToolNotFoundException {
        Tool tool = toolRepository.findById(id).orElseThrow(()-> new ToolNotFoundException("Tool with id "+id+" not found"));
        tool.setStatus(ToolStatus.UNACTIVE);
        tool.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));
        toolRepository.save(tool);
    }


    private List<Tag> verifyDuplicatedTags(List<Tag> tags) {
        List<Tag> tagsResponse = new ArrayList<>();
        tags.forEach(tag -> {
            Optional<Tag> optionalTag = tagRepository.findFirstByDescription(tag.getDescription());
            if (optionalTag.isEmpty()) {
                tagsResponse.add(tagRepository.save(tag));
            } else {
                tagsResponse.add(optionalTag.get());
            }
        });
        return tagsResponse;
    }
}

package com.challenge.voltz.domain.services;

import com.challenge.voltz.domain.entities.Tag;
import com.challenge.voltz.domain.entities.Tool;
import com.challenge.voltz.domain.entities.enums.ToolStatus;
import com.challenge.voltz.domain.exceptions.InvalidToolException;
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
import java.util.stream.Collectors;

@Service
public class ToolsService implements ToolsServiceOperations {
    @Autowired
    ToolRepository toolRepository;

    @Autowired
    TagRepository tagRepository;

    @Override
    public Tool createTool(Tool tool) throws InvalidToolException {
        validateTool(tool);
        tool.setTags(verifyDuplicatedTags(tool.getTags()));
        return toolRepository.save(tool);
    }

    @Override
    public List<Tool> getTools() {
        return toolRepository.findAllByStatus(ToolStatus.ACTIVE);
    }

    @Override
    public void deleteTool(Integer id) throws ToolNotFoundException {
        Tool tool = toolRepository.findByIdAndStatus(id, ToolStatus.ACTIVE)
                .orElseThrow(() -> new ToolNotFoundException("Tool with id " + id + " not found"));

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

    private void validateTool(Tool tool) throws InvalidToolException {

        tool.getTags().forEach(tag -> {
                long duplicatedCounter = tool.getTags().stream().filter(t -> t.getDescription().equals(tag.getDescription())).count();
                 if(duplicatedCounter > 1){
                     try {
                         throw new InvalidToolException("Duplicated tags");
                     } catch (InvalidToolException e) {
                         throw new RuntimeException(e);
                     }
                 }
            }
        );

        if (tool.getTags().size() > 8) {
            throw new InvalidToolException("Tags limit exceeded, must be between 0 and 8");
        }
        if (tool.getDescription().toCharArray().length > MAX_CHARACTERS) {
            throw new InvalidToolException("Tool description is too long, must be between 0 and 256 characters");
        }
        if (toolRepository.findFirstByTitleIgnoreCase(tool.getTitle()).isPresent()) {
            throw new InvalidToolException("Tool title " + tool.getTitle() + " already exists, proceeding would result in duplicated values");
        }
    }

    final int MAX_CHARACTERS = 256;
}

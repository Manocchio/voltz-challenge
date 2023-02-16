package com.challenge.voltz.domain.services.operations;

import com.challenge.voltz.domain.entities.Tool;
import com.challenge.voltz.domain.exceptions.ToolNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ToolsServiceOperations {
    ;

    public Tool createTool(Tool tool);

    public List<Tool> getTools();

    public void deleteTool(Integer id) throws ToolNotFoundException;


}

package com.challenge.voltz.application.web;

import com.challenge.voltz.application.web.operations.ToolOperations;
import com.challenge.voltz.application.web.payloads.requests.CreateToolRequestV1;
import com.challenge.voltz.application.web.payloads.responses.ToolResponseV1;

import com.challenge.voltz.domain.exceptions.ToolNotFoundException;
import com.challenge.voltz.domain.services.ToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolsController implements ToolOperations {

    @Autowired
    ToolsService toolsService;


    @Override
    public ResponseEntity<List<ToolResponseV1>> getAllTools() {
        return ResponseEntity.ok(
                ToolResponseV1.tofindAllResponseV1(toolsService.getTools())
        );
    }

    @Override
    public ResponseEntity<ToolResponseV1> createTool(CreateToolRequestV1 toolRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ToolResponseV1(
                        toolsService.createTool(toolRequest.toDomain())
                ));

    }

    @Override
    public ResponseEntity<Void> deleteTool(Integer id) throws ToolNotFoundException {
        toolsService.deleteTool(id);
        return ResponseEntity.ok().build();

    }


}

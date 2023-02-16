package com.challenge.voltz.application.web.operations;

import com.challenge.voltz.application.web.payloads.requests.CreateToolRequestV1;
import com.challenge.voltz.application.web.payloads.responses.ToolResponseV1;
import com.challenge.voltz.domain.exceptions.InvalidToolException;
import com.challenge.voltz.domain.exceptions.ToolNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ToolOperations {
    @Operation(security = { @SecurityRequirement(name = "bearer-token") })
    @GetMapping("/")
    public ResponseEntity<List<ToolResponseV1>> getAllTools();
    @PostMapping("/")
    public ResponseEntity<ToolResponseV1> createTool(@RequestBody CreateToolRequestV1 toolRequest) throws InvalidToolException;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTool( @PathVariable Integer id) throws ToolNotFoundException;

}

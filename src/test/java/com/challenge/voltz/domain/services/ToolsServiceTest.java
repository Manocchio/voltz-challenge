package com.challenge.voltz.domain.services;

import com.challenge.voltz.domain.entities.Tool;
import com.challenge.voltz.domain.entities.enums.ToolStatus;
import com.challenge.voltz.domain.exceptions.InvalidToolException;
import com.challenge.voltz.domain.exceptions.ToolNotFoundException;
import com.challenge.voltz.domain.repositories.TagRepository;
import com.challenge.voltz.domain.repositories.ToolRepository;
import com.challenge.voltz.factory.RequestFactory;
import com.challenge.voltz.factory.ToolFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToolsServiceTest {

    @InjectMocks
    ToolsService toolsService;

    @Mock
    private ToolRepository toolRepository;


    @Test
    public void given_a_valid_create_tool_request_should_create_tool() throws InvalidToolException {
        final Tool expectedTool = ToolFactory.getSample();
        when(toolRepository.save(any(Tool.class))).thenReturn(expectedTool);
        final Tool request = RequestFactory.createToolRequestSample().toDomain();

        final Tool response = toolsService.createTool(request);

        assertThat(response.getId()).isEqualTo(expectedTool.getId());
    }

    @Test
    public void given_a_create_tool_request_with_duplicated_tags_should_throw_InvalidToolException() {
        final Tool request = RequestFactory.duplicatedTagsCreateToolRequestSample().toDomain();
        final String exMessage = "Duplicated tags";
        InvalidToolException ex = assertThrows(
                InvalidToolException.class, () -> {
                    toolsService.createTool(request);
                }
        );

        assertThat(ex.getMessage()).isEqualTo(exMessage);
    }

    @Test
    public void given_a_create_tool_request_with_over_tags_limit_should_throw_InvalidToolException() {
        final Tool request = RequestFactory.overTagLimitCreateToolSample().toDomain();
        final String exMessage = "Tags limit exceeded, must be between 0 and 8";
        InvalidToolException ex = assertThrows(
                InvalidToolException.class, () -> {
                    toolsService.createTool(request);
                }
        );

        assertThat(ex.getMessage()).isEqualTo(exMessage);
    }


    @Test
    public void given_a_create_tool_request_with_over_description_limit_should_throw_InvalidToolException() {
        final Tool request = RequestFactory.overDescriptionLimitCreateToolSample().toDomain();
        final String exMessage = "Tool description is too long, must be between 0 and 256 characters";
        InvalidToolException ex = assertThrows(
                InvalidToolException.class, () -> {
                    toolsService.createTool(request);
                }
        );

        assertThat(ex.getMessage()).isEqualTo(exMessage);
    }

    @Test
    public void given_a_create_tool_request_with_existent_tool_title_should_throw_InvalidToolException() {
        final Tool request = RequestFactory.createToolRequestSample().toDomain();
        when(toolRepository.findFirstByTitleIgnoreCase(any(String.class))).thenReturn(Optional.of(request));
        final String exMessage = "Tool title " + request.getTitle() + " already exists, proceeding would result in duplicated values";
        InvalidToolException ex = assertThrows(
                InvalidToolException.class, () -> {
                    toolsService.createTool(request);
                }
        );

        assertThat(ex.getMessage()).isEqualTo(exMessage);
    }

    @Test
    public void given_a_delete_tool_request_with_nonexistent_tool_should_throw_ToolNotFoundException() {
        final Tool request = RequestFactory.createToolRequestSample().toDomain();
        lenient().when(toolRepository.findByIdAndStatus(any(Integer.class), any(ToolStatus.class))).thenReturn(Optional.empty());
        final String exMessage = "Tool with id " + request.getId() + " not found";
        ToolNotFoundException ex = assertThrows(
                ToolNotFoundException.class, () -> {
                    toolsService.deleteTool(request.getId());
                }
        );

        assertThat(ex.getMessage()).isEqualTo(exMessage);
    }






}
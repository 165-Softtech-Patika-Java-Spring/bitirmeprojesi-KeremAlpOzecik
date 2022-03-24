package com.example.demo.mgr.controller;

import com.example.demo.mgr.dto.ManagerDto;
import com.example.demo.mgr.dto.ManagerSaveRequestDto;
import com.example.demo.gen.dto.RestResponse;
import com.example.demo.mgr.dto.ManagerUpdateRequestDto;
import com.example.demo.mgr.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/managers")
@RequiredArgsConstructor
public class ManagerController {
private final ManagerService managerService;
  /*  @Operation(tags = "Manager Controller", description = "Post Manager.")
    @PostMapping
    public ResponseEntity save(@RequestBody ManagerSaveRequestDto managerSaveRequestDto){

        ManagerDto managerDto = managerService.save(managerSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(managerDto));
    }*/

    @Operation(
            tags = "Manager Controller",
            description = "Update manager",
            summary = "Update manager",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Managers",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ManagerUpdateRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new foreign manager update",
                                                    summary = "New Foreign Manager Update Example",
                                                    description = "Update Manager",
                                                    value ="{\n" +
                                                            "        \"id\": 1,\n" +
                                                            "            \"name\": \"update\",\n" +
                                                            "            \"surname\": \"update\",\n" +
                                                            "            \"username\": \"update\",\n" +
                                                            "            \"email\": \"update\",\n" +
                                                            "            \"phone\": \"update\",\n" +
                                                            "            \"password\": \"update\"\n" +
                                                            "    }"
                                            ),
                                            @ExampleObject(
                                                    name = "new Manager Update Example",
                                                    summary = "New Manager Update Example",
                                                    description = "Update request with all available fields",
                                                    value = "{\n" +
                                                            "        \"id\": 0,\n" +
                                                            "            \"name\": \"string\",\n" +
                                                            "            \"surname\": \"string\",\n" +
                                                            "            \"username\": \"string\",\n" +
                                                            "            \"email\": \"string\",\n" +
                                                            "            \"phone\": \"string\",\n" +
                                                            "            \"password\": \"string\"\n" +
                                                            "    }"
                                            )
                                    }
                            ),
                    }
            )
    )

    @PutMapping
    public ResponseEntity update(@RequestBody ManagerUpdateRequestDto managerUpdateRequestDto) {

        ManagerDto managerDto = managerService.update(managerUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(managerDto));
    }

    @Operation(tags = "Manager Controller", description = "Gets all Managers.", summary = "All Managers")
    @GetMapping
    public ResponseEntity findAll(){

        List<ManagerDto> managerDtoList = managerService.findAll();

        return ResponseEntity.ok(RestResponse.of(managerDtoList));
    }

    @Operation(tags = "Manager Controller", description = "Gets Manager by ID.", summary = "Gets Manager by ID")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        ManagerDto managerDto = managerService.findById(id);

        return ResponseEntity.ok(RestResponse.of(managerDto));
    }

    @Operation(tags = "Manager Controller", description = "Delete Manager by ID.", summary = "Delete Manager by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        managerService.deleteManager(id);

        return ResponseEntity.ok(RestResponse.empty());
    }
}

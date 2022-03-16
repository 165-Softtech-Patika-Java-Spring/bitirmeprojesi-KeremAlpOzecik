package com.example.demo.mgr.controller;

import com.example.demo.mgr.dto.ManagerDto;
import com.example.demo.mgr.dto.ManagerSaveRequestDto;
import com.example.demo.gen.dto.RestResponse;
import com.example.demo.mgr.dto.ManagerUpdateRequestDto;
import com.example.demo.mgr.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(tags = "Manager Controller", description = "Update Manager.")
    @PutMapping
    public ResponseEntity update(@RequestBody ManagerUpdateRequestDto managerUpdateRequestDto) {

        ManagerDto managerDto = managerService.update(managerUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(managerDto));
    }

    @Operation(tags = "Manager Controller", description = "Gets all Managers.", summary = "All Manager")
    @GetMapping
    public ResponseEntity findAll(){

        List<ManagerDto> managerDtoList = managerService.findAll();

        return ResponseEntity.ok(RestResponse.of(managerDtoList));
    }

    @Operation(tags = "Manager Controller")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        ManagerDto managerDto = managerService.findById(id);

        return ResponseEntity.ok(RestResponse.of(managerDto));
    }

    @Operation(tags = "Manager Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        managerService.deleteManager(id);

        return ResponseEntity.ok(RestResponse.empty());
    }
}

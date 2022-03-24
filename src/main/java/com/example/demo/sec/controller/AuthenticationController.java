package com.example.demo.sec.controller;


import com.example.demo.gen.dto.RestResponse;
import com.example.demo.mgr.dto.ManagerDto;
import com.example.demo.mgr.dto.ManagerSaveRequestDto;
import com.example.demo.sec.dto.LoginRequestDto;
import com.example.demo.sec.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

@Operation(
        tags = "Authentication Controller",
        description = "Login",
        summary = "Login",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Managers",
                content = {
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(
                                        implementation = ManagerSaveRequestDto.class
                                ),
                                examples = {
                                        @ExampleObject(
                                                name = "Login",
                                                summary = "Login",
                                                description = "Login",
                                                value ="{\n" +
                                                        "  \"username\": \"kalpozecik\",\n" +
                                                        "  \"password\": \"123456\"\n" +
                                                        "}"
                                        )
                                }
                        ),
                }
        )
)
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){

        String token = authenticationService.login(loginRequestDto);

        return ResponseEntity.ok(RestResponse.of(token));
    }

    @Operation(
            tags = "Authentication Controller",
            description = "Creates new manager",
            summary = "creates new manager",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Managers",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ManagerSaveRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new foreign manager",
                                                    summary = "New Foreign Manager Example",
                                                    description = "Complete request with all available fields for foreign manager",
                                                    value ="{\n" +
                                                            "  \"name\": \"Softtech\",\n" +
                                                            "  \"surname\": \"İşbankası\",\n" +
                                                            "  \"username\": \"Softtech\",\n" +
                                                            "  \"email\": \"işbankası@softtech.com\",\n" +
                                                            "  \"phone\": \"0262333333\",\n" +
                                                            "  \"password\": \"işbankası\"\n" +
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "new Manager Example",
                                                    summary = "New Manager Example2",
                                                    description = "Complete request with all available fields",
                                                    value = "{\n" +
                                                            "  \"name\": \"Kerem Alp\",\n" +
                                                            "  \"surname\": \"Ozecik\",\n" +
                                                            "  \"username\": \"kalpozecik\",\n" +
                                                            "  \"email\": \"kerem-alp@hotmail.com\",\n" +
                                                            "  \"phone\": \"05073296546\",\n" +
                                                            "  \"password\": \"123456\"\n" +
                                                            "}"
                                            ),
                                            @ExampleObject(
                                                    name = "new Manager",
                                                    summary = "New Manager",
                                                    description = "Complete request with all available fields",
                                                    value = "{\n" +
                                                            "  \"name\": \"string\",\n" +
                                                            "  \"surname\": \"string\",\n" +
                                                            "  \"username\": \"string\",\n" +
                                                            "  \"email\": \"string\",\n" +
                                                            "  \"phone\": \"string\",\n" +
                                                            "  \"password\": \"string   \"\n" +
                                                            "}"
                                            )
                                    }
                            ),
                    }
            )
    )
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ManagerSaveRequestDto managerSaveRequestDto){

        ManagerDto managerDto =authenticationService.register(managerSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(managerDto));
    }
}

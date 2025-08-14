package com.project.testApp.controller;

import com.project.testApp.model.dto.response.generalResponse.GeneralResponseWithData;
import com.project.testApp.model.dto.response.generalResponse.SuccessStatus;
import com.project.testApp.model.dto.response.testCase.GetAllModuleType;
import com.project.testApp.service.ModuleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/module-types")
public class ModuleTypeController {

    private final ModuleTypeService ModuleTypeService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GeneralResponseWithData<List<GetAllModuleType>>> getAll() {
        List<GetAllModuleType> data = ModuleTypeService.getAll();
        return ResponseEntity.ok(new GeneralResponseWithData<>(SuccessStatus.getSuccessStatus(), data));
    }
}

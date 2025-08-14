package com.project.testApp.controller;

import com.project.testApp.model.dto.response.generalResponse.GeneralResponseWithData;
import com.project.testApp.model.dto.response.generalResponse.SuccessStatus;
import com.project.testApp.model.dto.response.testCase.GetAllElement;
import com.project.testApp.service.ElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/elements")
public class ElementController {

    private final ElementService elementService;

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GeneralResponseWithData<List<GetAllElement>>> getAllElementNames(@RequestParam Integer platformId) {
        List<GetAllElement> data = elementService.getElementsByPlatformId(platformId);
        return ResponseEntity.ok(new GeneralResponseWithData<>(SuccessStatus.getSuccessStatus(), data));
    }
}

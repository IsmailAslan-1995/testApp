package com.project.testApp.controller;

import com.project.testApp.model.dto.response.generalResponse.GeneralResponseWithData;
import com.project.testApp.model.dto.response.generalResponse.SuccessStatus;
import com.project.testApp.model.dto.response.testCase.GetAllTag;
import com.project.testApp.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GeneralResponseWithData<List<GetAllTag>>> getAllTags() {
        List<GetAllTag> data = tagService.getAll();
        return ResponseEntity.ok(new GeneralResponseWithData<>(SuccessStatus.getSuccessStatus(), data));
    }
}

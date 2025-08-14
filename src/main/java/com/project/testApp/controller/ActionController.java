package com.project.testApp.controller;

import com.project.testApp.model.dto.response.testCase.GetAllAction;
import com.project.testApp.model.dto.response.generalResponse.GeneralResponseWithData;
import com.project.testApp.model.dto.response.generalResponse.SuccessStatus;
import com.project.testApp.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/actions")
public class ActionController {

    private final ActionService actionService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GeneralResponseWithData<List<GetAllAction>>> getAllActions(@RequestParam Integer platformId) {

        List<GetAllAction> data = actionService.getAllByPlatform(platformId);
        return ResponseEntity.ok(new GeneralResponseWithData<>(SuccessStatus.getSuccessStatus(), data));
    }
}

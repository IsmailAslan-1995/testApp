package com.project.testApp.service;

import com.project.testApp.model.dto.response.testCase.GetAllAction;
import com.project.testApp.model.repository.ActionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.project.testApp.model.mapper.ActionMapper.toDtoList;
import static com.project.testApp.service.ServiceText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository actionRepository;

    @Transactional(readOnly = true)
    public List<GetAllAction> getAllByPlatform(Integer platformId) {
        try {
            log.info(ACTION_SERVICE_LOG, platformId);
            List<GetAllAction> actions = toDtoList(actionRepository.findByPlatform_PlatformId(platformId));
            log.info(ACTION_FETCH_SUCCESS_LOG, platformId);
            return actions;
        } catch (Exception e) {
            log.error(ACTION_SERVICE_ERROR_LOG, e.getMessage());
            throw new RuntimeException(ACTION_SERVICE_ERROR_LOG, e);
        }
    }
}

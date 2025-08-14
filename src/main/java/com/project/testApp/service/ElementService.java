package com.project.testApp.service;

import com.project.testApp.model.dto.response.testCase.GetAllElement;
import com.project.testApp.model.entity.ElementDefinition;
import com.project.testApp.model.repository.ElementDefinitionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.testApp.model.mapper.ElementMapper.toDtoList;
import static com.project.testApp.service.ServiceText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElementService {
    private final ElementDefinitionRepository elementDefinitionRepository;

    public List<GetAllElement> getElementsByPlatformId(Integer platformId) {
        try {
            log.info(ELEMENT_SERVICE_LOG, platformId);
            List<ElementDefinition> entities = elementDefinitionRepository.findByPlatform_PlatformId(platformId);
            log.info(ELEMENT_FETCH_SUCCESS_LOG, platformId);
            return toDtoList(entities);
        } catch (Exception e) {
            log.error(ELEMENT_SERVICE_ERROR_LOG, e.getMessage());
            throw new RuntimeException(ELEMENT_SERVICE_ERROR_LOG, e);
        }
    }
}

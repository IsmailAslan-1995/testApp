package com.project.testApp.service;

import com.project.testApp.model.dto.response.testCase.GetAllModuleType;
import com.project.testApp.model.entity.ModuleType;
import com.project.testApp.model.repository.ModuleTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.project.testApp.model.mapper.ModuleTypeMapper.toDtoList;
import static com.project.testApp.service.ServiceText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModuleTypeService {

    private final ModuleTypeRepository ModuleTypeRepository;

    @Transactional(readOnly = true)
    public List<GetAllModuleType> getAll() {
        try {
            log.info(MODULE_TYPE_SERVICE_LOG);
            List<ModuleType> entities = ModuleTypeRepository.findAllByOrderByModuleNameAsc();
            log.info(MODULE_TYPE_FETCH_SUCCESS_LOG);
            return toDtoList(entities);
        } catch (Exception e) {
            log.error(MODULE_TYPE_SERVICE_ERROR_LOG, e.getMessage());
            throw new RuntimeException(MODULE_TYPE_SERVICE_ERROR_LOG, e);
        }
    }
}

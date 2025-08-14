package com.project.testApp.service;

import com.project.testApp.model.dto.response.testCase.GetAllPlatform;
import com.project.testApp.model.entity.Platform;
import com.project.testApp.model.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.project.testApp.model.mapper.PlatformMapper.toDtoList;
import static com.project.testApp.service.ServiceText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;

    @Transactional(readOnly = true)
    public List<GetAllPlatform> getAll() {
        try {
            log.info(PLATFORM_SERVICE_LOG);
            List<Platform> entities = platformRepository.findAllByOrderByPlatformNameAsc();
            log.info(PLATFORM_FETCH_SUCCESS_LOG);
            return toDtoList(entities);
        } catch (Exception e) {
            log.error(PLATFORM_SERVICE_ERROR_LOG, e.getMessage());
            throw new RuntimeException(PLATFORM_SERVICE_ERROR_LOG, e);
        }
    }
}

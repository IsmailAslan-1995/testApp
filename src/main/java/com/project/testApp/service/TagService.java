package com.project.testApp.service;

import com.project.testApp.model.dto.response.testCase.GetAllTag;
import com.project.testApp.model.entity.Tag;
import com.project.testApp.model.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.project.testApp.model.mapper.TagMapper.toDtoList;
import static com.project.testApp.service.ServiceText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<GetAllTag> getAll() {
        try {
            log.info(TAG_SERVICE_LOG);
            List<Tag> entities = tagRepository.findAllByOrderByTagNameAsc();
            log.info(TAG_FETCH_SUCCESS_LOG);
            return toDtoList(entities);
        } catch (Exception e) {
            log.error(TAG_SERVICE_ERROR_LOG, e.getMessage());
            throw new RuntimeException(TAG_SERVICE_ERROR_LOG, e);
        }
    }
}

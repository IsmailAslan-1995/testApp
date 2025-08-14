package com.project.testApp.model.mapper;

import com.project.testApp.model.dto.response.testCase.GetAllTag;
import com.project.testApp.model.entity.Tag;

import java.util.List;
import java.util.function.Function;

import static com.project.testApp.model.mapper.MapperGeneric.toDtoListGeneric;

public final class TagMapper {
    private TagMapper() {}

    private static final Function<Tag, GetAllTag> tagToDtoMapper = t -> GetAllTag.builder()
            .tagId(t.getTagId())
            .tagName(t.getTagName())
            .build();

    public static List<GetAllTag> toDtoList(List<Tag> list) {
        return toDtoListGeneric(list, tagToDtoMapper);
    }
}

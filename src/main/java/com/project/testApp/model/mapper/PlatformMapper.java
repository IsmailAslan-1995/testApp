package com.project.testApp.model.mapper;

import com.project.testApp.model.dto.response.testCase.GetAllPlatform;
import com.project.testApp.model.entity.Platform;

import java.util.List;
import java.util.function.Function;

import static com.project.testApp.model.mapper.MapperGeneric.toDtoListGeneric;

public final class PlatformMapper {
    private PlatformMapper() {}

    private static final Function<Platform, GetAllPlatform> platformToDtoMapper = p -> GetAllPlatform.builder()
            .platformId(p.getPlatformId())
            .platformName(p.getPlatformName())
            .build();

    public static List<GetAllPlatform> toDtoList(List<Platform> list) {
        return toDtoListGeneric(list, platformToDtoMapper);
    }
}

package com.project.testApp.model.mapper;

import com.project.testApp.model.dto.response.testCase.GetAllModuleType;
import com.project.testApp.model.entity.ModuleType;

import java.util.List;
import java.util.function.Function;

import static com.project.testApp.model.mapper.MapperGeneric.toDtoListGeneric;

public final class ModuleTypeMapper {
    private ModuleTypeMapper() {}

    private static final Function<ModuleType, GetAllModuleType> ModuleTypeToDtoMapper = m -> GetAllModuleType.builder()
            .ModuleId(m.getModuleId())
            .ModuleName(m.getModuleName())
            .build();

    public static List<GetAllModuleType> toDtoList(List<ModuleType> list) {
        return toDtoListGeneric(list, ModuleTypeToDtoMapper);
    }
}

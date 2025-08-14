package com.project.testApp.model.mapper;

import com.project.testApp.model.dto.response.testCase.GetAllElement;
import com.project.testApp.model.entity.ElementDefinition;

import java.util.List;
import java.util.function.Function;

import static com.project.testApp.model.mapper.MapperGeneric.toDtoListGeneric;

public final class ElementMapper {
    private ElementMapper() {}

    private static final Function<ElementDefinition, GetAllElement> elementToDtoMapper = e -> GetAllElement.builder()
            .elementId(e.getElementDefinitionId())
            .key(e.getElementName())
            .value(e.getValue())
            .locatorType(e.getLocator() != null ? e.getLocator().getLocatorName() : null)
            .build();

    public static List<GetAllElement> toDtoList(List<ElementDefinition> list) {
        return toDtoListGeneric(list, elementToDtoMapper);
    }
}

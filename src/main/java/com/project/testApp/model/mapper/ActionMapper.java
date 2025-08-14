package com.project.testApp.model.mapper;

import com.project.testApp.model.dto.response.testCase.GetAllAction;
import com.project.testApp.model.entity.Action;

import java.util.List;
import java.util.function.Function;

import static com.project.testApp.model.mapper.MapperGeneric.toDtoListGeneric;

public final class ActionMapper {
    private ActionMapper() {}

    private static final Function<Action, GetAllAction> actionToDtoMapper = a -> GetAllAction.builder()
            .actionId(a.getActionId())
            .actionName(a.getActionName())
            .build();

    public static List<GetAllAction> toDtoList(List<Action> list) {
        return toDtoListGeneric(list, actionToDtoMapper);
    }
}

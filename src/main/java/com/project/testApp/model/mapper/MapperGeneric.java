package com.project.testApp.model.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapperGeneric {

    public static <T, R> List<R> toDtoListGeneric(List<T> list, Function<T, R> mapper) {
        return list == null ? List.of() : list.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

}

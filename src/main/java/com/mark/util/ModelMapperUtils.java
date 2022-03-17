package com.mark.util;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public final class ModelMapperUtils {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private ModelMapperUtils() {}

    public static <T> T map(Object source, Class<T> targetClazz) {
        return MODEL_MAPPER.map(source, targetClazz);
    }

    public static <T> List<T> mapList(List<?> source, Class<T> targetClazz) {
        List<T> targetList = new ArrayList<>();
        for (Object o : source) {
            targetList.add(map(o, targetClazz));
        }
        return targetList;
    }
}
package com.food.recipe.api;

import java.util.function.BiFunction;

public interface Mappers<T, R, S> extends BiFunction<T, R, S> {
}

package com.github.pedroluis02.springsecuritysample.apirestful.mapper

import java.util.function.Function
import java.util.stream.Collectors

fun <In, Out> List<In>.mapToList(mapper: Function<In, Out>): List<Out> {
    return stream().map(mapper).collect(Collectors.toList())
}
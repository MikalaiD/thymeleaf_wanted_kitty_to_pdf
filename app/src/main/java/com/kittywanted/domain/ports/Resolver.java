package com.kittywanted.domain.ports;

import java.util.Map;

public interface Resolver<T> {

    T resolve (String htmlWithThymeleafTags,  Map<String, Object> poster);

}

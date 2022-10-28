package com.kittywanted.domain.ports;

import com.kittywanted.domain.model.Poster;

public interface Resolver<T> {

    T resolve (String htmlWithThymeleafTags,  Poster poster);

}

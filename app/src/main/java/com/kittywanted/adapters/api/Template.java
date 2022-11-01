package com.kittywanted.adapters.api;

import com.kittywanted.adapters.api.exceptions.TemplateException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Template {
  CAT_WANTED("index");

  private final String path;

  public String toString(){
    var path = new StringBuilder("./src/test/resources/templates/" + this.path + "html");
    try (Stream<String> lines = Files.lines(Path.of(path.toString()))) {
      return lines.collect(Collectors.joining(" "));
    } catch (IOException e){
      throw new TemplateException("<> Could not read the template");
    }
  }
}

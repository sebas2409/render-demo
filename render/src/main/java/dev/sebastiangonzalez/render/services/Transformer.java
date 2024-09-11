package dev.sebastiangonzalez.render.services;

import java.util.Map;

public interface Transformer {
    String generate(String template, Map<String, Object> model);
}

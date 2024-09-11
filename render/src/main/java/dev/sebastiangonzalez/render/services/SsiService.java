package dev.sebastiangonzalez.render.services;

import dev.sebastiangonzalez.render.dto.SsiTemplate;
import dev.sebastiangonzalez.render.dto.SsiType;

public interface SsiService {
    SsiTemplate find(SsiType type);

    void save(SsiTemplate template);
}

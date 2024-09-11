package dev.sebastiangonzalez.render.services.impl;

import dev.sebastiangonzalez.render.dto.SsiTemplate;
import dev.sebastiangonzalez.render.dto.SsiType;
import dev.sebastiangonzalez.render.repository.ElasticRepository;
import dev.sebastiangonzalez.render.services.SsiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SsiServiceImpl implements SsiService {

    private final ElasticRepository elasticRepository;

    @Override
    public SsiTemplate find(SsiType type) {
        return elasticRepository.find(type.name());
    }

    @Override
    public void save(SsiTemplate template) {
        elasticRepository.saveSsi(template);
    }
}

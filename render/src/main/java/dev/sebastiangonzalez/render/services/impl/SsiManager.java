package dev.sebastiangonzalez.render.services.impl;

import dev.sebastiangonzalez.render.dto.SsiDynamicInfo;
import dev.sebastiangonzalez.render.dto.SsiTemplate;
import dev.sebastiangonzalez.render.dto.SsiType;
import dev.sebastiangonzalez.render.renders.SsiDynamicRender;
import dev.sebastiangonzalez.render.services.SsiService;
import dev.sebastiangonzalez.render.services.Transformer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SsiManager {

    private final List<SsiDynamicRender> ssiDynamicRenders;
    private final Transformer transformer;
    private final SsiService ssiService;

    private final Map<SsiType, SsiDynamicRender> mapSsiDynamicRender = new HashMap<>();


    @PostConstruct
    public void init() {
        ssiDynamicRenders.forEach(render -> mapSsiDynamicRender.put(render.getType(), render));
    }

    public String renderSsi(SsiDynamicInfo info) {
        var ssiType = info.getSsiType();
        var ssi = ssiService.find(ssiType);
        var render = mapSsiDynamicRender.get(ssiType);
        var file = ssi.getFilePath();
        if (Objects.isNull(render)) return "";
        var model = render.getRenderModel(info);
        return transformer.generate(file, model);
    }

    public void saveSsi(SsiTemplate template) {
        ssiService.save(template);
    }
}

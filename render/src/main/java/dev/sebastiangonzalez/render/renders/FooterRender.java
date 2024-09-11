package dev.sebastiangonzalez.render.renders;

import dev.sebastiangonzalez.render.dto.SsiDynamicInfo;
import dev.sebastiangonzalez.render.dto.SsiType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FooterRender implements SsiDynamicRender {

    @Override
    public SsiType getType() {
        return SsiType.FOOTER;
    }

    @Override
    public Map<String, Object> getRenderModel(SsiDynamicInfo ssiDynamicInfo) {
        // aquí se puede hacer una llamada a una API, a una base de datos, etc.
        return Map.of("content", "Contenido del footer desde ESI");


    }
}

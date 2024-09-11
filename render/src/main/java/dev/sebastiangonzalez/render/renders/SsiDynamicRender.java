package dev.sebastiangonzalez.render.renders;

import dev.sebastiangonzalez.render.dto.SsiDynamicInfo;
import dev.sebastiangonzalez.render.dto.SsiType;

import java.util.Map;

public interface SsiDynamicRender {

    SsiType getType();

    Map<String, Object> getRenderModel(SsiDynamicInfo ssiDynamicInfo);
}

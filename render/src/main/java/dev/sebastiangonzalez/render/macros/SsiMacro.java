package dev.sebastiangonzalez.render.macros;

import dev.sebastiangonzalez.render.annotations.Macro;
import dev.sebastiangonzalez.render.dto.SsiDynamicInfo;
import dev.sebastiangonzalez.render.dto.SsiType;
import dev.sebastiangonzalez.render.utils.EsiUtils;
import dev.sebastiangonzalez.render.utils.StringUtils;
import freemarker.core.Environment;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Macro(name = "ssiMacro")
@Service("ssiMacro")
@Slf4j
public class SsiMacro implements TemplateHashModel, TemplateDirectiveModel {
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        SsiType ssi = SsiType.valueOf(map.get("name").toString());
        SsiDynamicInfo info = new SsiDynamicInfo();
        info.setSsiType(ssi);
        String esiSrc = EsiUtils.createUrl(info);

        environment.getOut().write(esiSrc);
    }

    @Override
    public TemplateModel get(String s) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}

package dev.sebastiangonzalez.render.services.impl;

import dev.sebastiangonzalez.render.services.RenderService;
import dev.sebastiangonzalez.render.services.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class RenderServiceImpl implements RenderService {

    private final Transformer transformer;
    @Value("${path.static}")
    private String staticPath;

    @Override
    public void renderArticle(String title) {
        var model = new HashMap<String, Object>();
        model.put("title", title);
        var html = transformer.generate("index.ftl", model);
        try {
            FileUtils.writeStringToFile(new File(staticPath + "/index.html"), html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

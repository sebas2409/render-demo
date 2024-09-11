package dev.sebastiangonzalez.render.services.impl;

import dev.sebastiangonzalez.render.services.CustomTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Configuration
public class TemplateLoaderImpl implements CustomTemplateLoader {

    @Value("${path.ftl}")
    private String ftlPath;

    @Bean
    @Override
    public TemplateLoader getTemplateLoader() {
        try {
            return new FileTemplateLoader(new File(ftlPath));
        } catch (Exception e) {
            log.error("Error loading template loader", e);
        }
        return null;
    }
}

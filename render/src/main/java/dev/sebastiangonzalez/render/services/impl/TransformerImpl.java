package dev.sebastiangonzalez.render.services.impl;

import dev.sebastiangonzalez.render.annotations.Macro;
import dev.sebastiangonzalez.render.services.Transformer;
import freemarker.cache.MruCacheStorage;
import freemarker.cache.TemplateLoader;
import freemarker.template.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransformerImpl implements Transformer {

    private final TemplateLoader templateConfig;
    private final ApplicationContext context;
    private Configuration config;


    @PostConstruct
    public void init() {
        config = new Configuration(Configuration.VERSION_2_3_31);
        config.setTemplateLoader(templateConfig);
        config.setObjectWrapper(new DefaultObjectWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS).build());
        config.setLocale(Locale.of("es", "ES"));
        config.setDefaultEncoding("UTF-8");
        config.setOutputEncoding("UTF-8");
        config.setNumberFormat("0.######");
        config.setCacheStorage(new MruCacheStorage(50, 500));
        loadMacros(config);
    }

    private void loadMacros(Configuration config) {
        AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();
        var reflections = new Reflections("dev.sebastiangonzalez.render.macros");
        Set<Class<?>> macros = reflections.getTypesAnnotatedWith(Macro.class);

        for (Class<?> clazz : macros) {
            Object macroInstance = null;
            try {
                macroInstance = clazz.getDeclaredConstructor().newInstance();
                if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = clazz.getDeclaredAnnotation(Service.class);
                    factory.autowireBean(macroInstance);
                    factory.initializeBean(macroInstance, service.value());
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                log.error("Error creating macro instance", e);
            }

            Macro macro = clazz.getDeclaredAnnotation(Macro.class);
            if (macro.name().isEmpty()) {
                String className = clazz.getSimpleName();
                className = Character.toLowerCase(className.charAt(0)) + className.substring(1);
                try {
                    config.setSharedVariable(className, macroInstance);
                } catch (TemplateModelException e) {
                    log.error("Error setting macro", e);
                }
            } else {
                try {
                    config.setSharedVariable(macro.name(), macroInstance);
                } catch (TemplateModelException e) {
                    log.error("Error setting macro", e);
                }
            }
        }
    }

    @Override
    public String generate(String template, Map<String, Object> model) {
        Template index;
        ByteArrayOutputStream out;
        try {
            index = config.getTemplate(template);
            out = new ByteArrayOutputStream();
            index.process(model, new OutputStreamWriter(out));
        } catch (IOException | TemplateException e) {
            log.error("Error rendering template", e);
            out = new ByteArrayOutputStream();
        }
        return out.toString();
    }
}

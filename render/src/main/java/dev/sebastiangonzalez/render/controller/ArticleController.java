package dev.sebastiangonzalez.render.controller;

import dev.sebastiangonzalez.render.dto.BaseContent;
import dev.sebastiangonzalez.render.services.RenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final RenderService renderService;

    @PostMapping("/publish")
    public void publishArticle(@RequestBody BaseContent content) {
        renderService.renderArticle(content.getTitle());
    }
}

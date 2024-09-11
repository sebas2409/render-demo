package dev.sebastiangonzalez.render.controller;

import dev.sebastiangonzalez.render.dto.SsiDynamicInfo;
import dev.sebastiangonzalez.render.dto.SsiTemplate;
import dev.sebastiangonzalez.render.dto.SsiType;
import dev.sebastiangonzalez.render.services.impl.SsiManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/esi")
@RequiredArgsConstructor
public class EsiController {

    private final SsiManager ssiManager;

    @GetMapping("/{type}")
    public ResponseEntity<String> renderEsi(@PathVariable SsiType type) {
        return ResponseEntity.ok(ssiManager.renderSsi(new SsiDynamicInfo(type)));
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveEsi(@RequestBody SsiTemplate template) {
        ssiManager.saveSsi(template);
        return ResponseEntity.ok().build();
    }
}

package dev.sebastiangonzalez.render.utils;

import dev.sebastiangonzalez.render.dto.SsiDynamicInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

public class EsiUtils {

    private static String esiUrl;

    @Value("${esi.url}")
    public void setEsiUrl(String esiUrl) {
        EsiUtils.esiUrl = esiUrl;
    }

    public static final String ESI_INCLUDE = "<esi:include src=\"%s\" />";

    public static String createUrl(SsiDynamicInfo info) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(esiUrl)
                .pathSegment("api", "esi")
                .pathSegment(info.getSsiType().name());

        String uri = builder.toUriString();
        return String.format(ESI_INCLUDE, uri);
    }
}

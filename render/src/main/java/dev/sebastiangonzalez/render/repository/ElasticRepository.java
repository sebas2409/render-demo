package dev.sebastiangonzalez.render.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import dev.sebastiangonzalez.render.dto.SsiTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ElasticRepository {


    private final ElasticsearchClient client;


    public void saveSsi(SsiTemplate ssiTemplate) {
        try {
            client.index(r -> r
                    .index("ssi")
                    .id(ssiTemplate.getName())
                    .document(ssiTemplate));
        } catch (IOException e) {
            log.error("Error saving ssi", e);
        }
    }

    public SsiTemplate find(String name) {
        Query query = MatchQuery.of(q -> q.field("name").query(name))._toQuery();
        SearchResponse<SsiTemplate> response;
        try {
            response = client.search(r -> r
                    .from(0)
                    .size(10000)
                    .index("ssi")
                    .query(query), SsiTemplate.class);
        } catch (IOException e) {
            log.error("Error executing query", e);
            return null;
        }
        return response.hits().hits().stream().findFirst().map(Hit::source).orElse(null);
    }
}

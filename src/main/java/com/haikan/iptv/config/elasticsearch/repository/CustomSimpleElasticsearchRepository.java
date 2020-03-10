package com.haikan.iptv.config.elasticsearch.repository;

import com.haikan.iptv.config.elasticsearch.util.ElasticsearchHelper;
import org.springframework.stereotype.Component;

@Component
public class CustomSimpleElasticsearchRepository<T, ID> extends AbstractCustomElasticsearchRepository<T, ID> {

  public CustomSimpleElasticsearchRepository(
      ElasticsearchHelper elasticsearchHelper) {

    super(elasticsearchHelper);
  }
}

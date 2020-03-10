package com.haikan.iptv.elasticsearch.repository;

import com.haikan.iptv.config.elasticsearch.repository.CustomElasticsearchRepository;
import com.haikan.iptv.config.elasticsearch.repository.anntation.ElasticsearchRepository;
import com.haikan.iptv.elasticsearch.model.ShortUrlDTO;

@ElasticsearchRepository
public interface CustomShortUrlEsRepository extends CustomElasticsearchRepository<ShortUrlDTO,Long> {
}

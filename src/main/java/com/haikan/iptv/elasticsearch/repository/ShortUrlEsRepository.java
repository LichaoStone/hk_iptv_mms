package com.haikan.iptv.elasticsearch.repository;

import com.haikan.iptv.elasticsearch.model.ShortUrlVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ShortUrlEsRepository extends ElasticsearchRepository<ShortUrlVO,String> {
}

package com.haikan.iptv.config.elasticsearch.repository;

import com.haikan.iptv.config.elasticsearch.model.PageResult;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Collection;
import java.util.List;

public interface CustomElasticsearchRepository<T, ID> {
  T index(T entity);

  List<T> search(SearchSourceBuilder builder);

  PageResult<T> page(SearchSourceBuilder builder);

  boolean deleteIndex(String indexName);

  boolean isIndexExist(String indexName);

  boolean deleteByQuery(QueryBuilder builder);

  boolean deleteBatch(Collection<ID> idList);

  boolean deleteById(ID id);

  boolean insertBatch(List<T> entitys);

  boolean save(T entity);

  T getEntityById(ID id);

}

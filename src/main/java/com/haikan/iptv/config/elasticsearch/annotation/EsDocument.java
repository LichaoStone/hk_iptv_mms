package com.haikan.iptv.config.elasticsearch.annotation;

import org.elasticsearch.index.VersionType;
import org.springframework.data.annotation.Persistent;

import java.lang.annotation.*;

@Persistent
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EsDocument {
  String indexName();

  String type() default "_doc";

  boolean useServerConfiguration() default false;

  short shards() default 5;

  short replicas() default 1;

  String refreshInterval() default "1s";

  String indexStoreType() default "fs";

  boolean createIndex() default true;

  VersionType versionType() default VersionType.EXTERNAL;
}

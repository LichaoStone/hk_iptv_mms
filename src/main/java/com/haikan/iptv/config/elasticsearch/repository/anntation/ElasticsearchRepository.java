package com.haikan.iptv.config.elasticsearch.repository.anntation;

import java.lang.annotation.*;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ElasticsearchRepository {

}

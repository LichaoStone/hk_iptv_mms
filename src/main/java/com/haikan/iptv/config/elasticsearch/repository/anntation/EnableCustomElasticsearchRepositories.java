package com.haikan.iptv.config.elasticsearch.repository.anntation;

import com.haikan.iptv.config.elasticsearch.repository.registrar.ElasticsearchRepositoryRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value= ElasticsearchRepositoryRegistrar.class)
public @interface EnableCustomElasticsearchRepositories {
    String[] basePackages() default {};
    Class<?>[] basePackageClasses() default {};
}

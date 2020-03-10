package com.haikan.iptv.config.elasticsearch.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Inherited
public @interface EsField {

  @AliasFor("name")
  String value() default "";

  String type() default "keyword";

  @AliasFor("value")
  String name() default "";

  boolean index() default true;

  boolean store() default false;

  String search_analyzer() default "";

  String analyzer() default "";


}
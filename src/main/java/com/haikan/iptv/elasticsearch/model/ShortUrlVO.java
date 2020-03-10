package com.haikan.iptv.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;

@Document(indexName = "short_url")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortUrlVO implements Serializable{

  @Id
  private String id;

  private String longUrl;

  @Field(type = FieldType.Text, analyzer = "ik_smart",
      searchAnalyzer = "ik_smart", fielddata=true)
  private String urlName;

  @Field(type = FieldType.Text, analyzer = "ik_max_word",
      searchAnalyzer = "ik_max_word", fielddata=true)
  private String remark;



}

package com.haikan.iptv.config.elasticsearch.command;

import com.haikan.iptv.config.elasticsearch.util.ElasticsearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchCommandLineRunner implements CommandLineRunner {

  @Autowired
  private ElasticsearchHelper elasticsearchHelper;

  @Override
  public void run(String... args) throws Exception {
    initCreateIndex();
  }


  private void initCreateIndex(){
    elasticsearchHelper.createIndexs("com.github.lybgeek.shorturl.dto");
  }
}

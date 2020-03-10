package com.haikan.iptv.elasticsearch.service;

import com.haikan.iptv.config.elasticsearch.model.PageQuery;
import com.haikan.iptv.config.elasticsearch.model.PageResult;
import com.haikan.iptv.elasticsearch.model.ShortUrlDTO;

import java.util.List;

public interface CustomShortUrlEsService {

  String save(ShortUrlDTO shortUrlDTO);

  PageResult<ShortUrlDTO> pageShortUrl(PageQuery<ShortUrlDTO> pageQuery);

  boolean saveShortUrl(ShortUrlDTO shortUrlDTO);

  boolean deleteShortUrlById(Long id);

  List<ShortUrlDTO> listShortUrls(ShortUrlDTO shortUrlDTO);

  ShortUrlDTO getShortUrlById(Long id);

}

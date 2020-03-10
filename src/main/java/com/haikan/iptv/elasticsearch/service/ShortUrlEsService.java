package com.haikan.iptv.elasticsearch.service;

import com.haikan.iptv.config.elasticsearch.model.PageQuery;
import com.haikan.iptv.config.elasticsearch.model.PageResult;
import com.haikan.iptv.elasticsearch.model.ShortUrlDTO;
import com.haikan.iptv.elasticsearch.model.ShortUrlVO;

public interface ShortUrlEsService {

  ShortUrlVO save(ShortUrlDTO shortUrlDTO);

  PageResult<ShortUrlVO> pageShortUrl(PageQuery<ShortUrlDTO> pageQuery);

}

package com.haikan.iptv;

import com.haikan.iptv.common.util.constant.ElasticsearchConstant;
import com.haikan.iptv.config.elasticsearch.model.PageQuery;
import com.haikan.iptv.config.elasticsearch.model.PageResult;
import com.haikan.iptv.config.elasticsearch.util.ElasticsearchHelper;
import com.haikan.iptv.elasticsearch.model.ShortUrlDTO;
import com.haikan.iptv.elasticsearch.service.CustomShortUrlEsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class ElasticsearchApplicationTest {

   @Autowired
   private ElasticsearchHelper elasticsearchHelper;

   @Autowired
   private CustomShortUrlEsService customShortUrlEsService;


   @Test
   public void testCustomSaveShortUrlEsAndReturnId(){
      ShortUrlDTO shortUrlDTO = ShortUrlDTO.builder().id(20L).longUrl("https://music.wangyiyun.com/").urlName("网易云").remark("武汉加油，中国人牛，中国足球加油").build();
      String id = customShortUrlEsService.save(shortUrlDTO);
      Assert.assertNotNull(id);

   }

   @Test
   public void testCustomPageShortUrl(){
      PageQuery pageQuery = new PageQuery<>().setPageNo(0).setPageSize(5);
      ShortUrlDTO dto = new ShortUrlDTO();
//     dto.setUrlName("优酷");
      dto.setRemark("网");
      pageQuery.setQueryParams(dto);

      PageResult<ShortUrlDTO> pageResult = customShortUrlEsService.pageShortUrl(pageQuery);

      System.out.println(pageResult);
   }



   @Test
   public void testCustomSaveShortUrlEs(){
      ShortUrlDTO shortUrlDTO = ShortUrlDTO.builder().longUrl("https://www.meituan.com").urlName("美团外卖").remark("美团外卖APP").build();
      boolean isSuccess = customShortUrlEsService.saveShortUrl(shortUrlDTO);
      Assert.assertTrue(isSuccess);
   }


   @Test
   public void testListCustomShortUrls(){
      ShortUrlDTO dto = new ShortUrlDTO();
      dto.setUrlName("美团");
      //  dto.setRemark("门户");
      List<ShortUrlDTO> shortUrlDTOS = customShortUrlEsService.listShortUrls(dto);

      System.out.println(shortUrlDTOS);
   }


   @Test
   public void testCustomGetShortUrl(){
      ShortUrlDTO shortUrlDTO = customShortUrlEsService.getShortUrlById(2L);
      Assert.assertNotNull(shortUrlDTO);
      System.out.println(shortUrlDTO);
   }


   @Test
   public void testDeleteCustomShortUrl(){
      boolean isSuccess = customShortUrlEsService.deleteShortUrlById(2L);
      Assert.assertTrue(isSuccess);
   }



   @Test
   public void testDeleteIndex(){
      boolean flag = elasticsearchHelper.deleteIndex(ElasticsearchConstant.SHORT_URL_INDEX);
      Assert.assertTrue(flag);
   }

    @Test
    public void testDeleteIndex2(){
        boolean flag = elasticsearchHelper.deleteIndex("test1");
        Assert.assertTrue(flag);
    }


}

package com.haikan.iptv.elasticsearch.model;

import com.haikan.iptv.config.elasticsearch.annotation.EsDocument;
import com.haikan.iptv.config.elasticsearch.annotation.EsField;
import com.haikan.iptv.config.elasticsearch.annotation.EsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EsDocument(indexName = "test1",type="test1")
public class ShortUrlDTO {

    @EsId
    @EsField
    private Long id;

    @NotNull(message = "链接不能为空")
    @Pattern(regexp = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ,message = "输入的链接不合法")
    @EsField
    private String longUrl;

    @EsField(type = "text", analyzer = "ik_smart",search_analyzer="ik_smart")
    private String urlName;

    @EsField(type = "text", analyzer = "ik_max_word",search_analyzer="ik_max_word")
    private String remark;
}

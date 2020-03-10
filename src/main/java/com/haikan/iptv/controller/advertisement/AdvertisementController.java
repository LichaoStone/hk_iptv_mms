package com.haikan.iptv.controller.advertisement;

import com.haikan.iptv.bean.advertisement.Advertisement;
import com.haikan.iptv.bean.advertisement.query.AdvertisementVoQuery;
import com.haikan.iptv.bean.advertisement.vo.AdvertisementVo;
import com.haikan.iptv.common.util.*;
import com.haikan.iptv.common.util.bean.*;
import com.haikan.iptv.config.redis.RedisService;
import com.haikan.iptv.controller.WebBaseController;
import com.haikan.iptv.service.advertisement.AdvertisementServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController extends WebBaseController {
	Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

	@Resource
	private AdvertisementServiceApi advertisementServiceImpl ;
	@Resource
	private RedisService redisService;
	/**
	 * 获取列表数据
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/getAppAdvList",method= RequestMethod.GET)
	@ResponseBody
	public Result getAppAdvList(AdvertisementVoQuery query) {
		logger.info("getAppAdvList================开始执行");
		//查询条件
		logger.info("getAppAdvList===========参数：beginTime="+query.getBeginTimeStr()+",endTime="+query.getEndTimeStr()+",status="+query.getStatus()+",advName="+query.getAdvName()+",advType="+query.getAdvType());
		if(StringUtils.isNotBlank(query.getBeginTimeStr())){
			try {
				query.setBeginTime(DateTimeUtils.toUtilDateFromStrDateByFormat(query.getBeginTimeStr(),"yyyy-MM-dd hh:mm:ss"));
			} catch (ParseException e) {
				logger.error("错误信息");
			}
		}
		if(StringUtils.isNotBlank(query.getEndTimeStr())){
			try {
				query.setEndTime(DateTimeUtils.toUtilDateFromStrDateByFormat(query.getEndTimeStr(),"yyyy-MM-dd hh:mm:ss"));
			} catch (ParseException e) {
				logger.error("错误信息");
			}
		}
		/*logger.info("advBelong========="+query.getAdvBelong());
		List<Integer> belongList = new ArrayList<Integer>();
		if (query.getAdvBelong()!=null){
			belongList=getBelongList(query.getAdvBelong());
		}
		query.setAdvBelongList(belongList);*/

		Result result = advertisementServiceImpl.getAdvertisementPage(query);
		return result ;
	}






}
package com.haikan.iptv.service.advertisement.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haikan.iptv.bean.advertisement.query.AdvertisementVoQuery;
import com.haikan.iptv.bean.advertisement.vo.AdvertisementVo;
import com.haikan.iptv.bean.platformUser.vo.PlatformUserVo;
import com.haikan.iptv.bean.role.vo.RoleVo;
import com.haikan.iptv.common.util.DateTimeUtils;
import com.haikan.iptv.common.util.PkCreat;
import com.haikan.iptv.common.util.bean.PageVo;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.common.util.bean.ServiceResult;
import com.haikan.iptv.common.util.constant.HttpConstant;
import com.haikan.iptv.config.redis.RedisService;
import com.haikan.iptv.mapper.advertisement.AdvertisementMapper;
import com.haikan.iptv.service.advertisement.AdvertisementServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdvertisementServiceImpl implements AdvertisementServiceApi {

	Logger logger = LoggerFactory.getLogger(AdvertisementServiceImpl.class);

	//protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());


	@Resource
	private AdvertisementMapper advertisementMapper ;
	/*@Resource
	private GoodsCategoryMapper goodsCategoryMapper ;
	@Resource
	private GoodsMapper goodsMapper ;*/
	@Resource
	private RedisService redisService;

	/**
	 * 根据查询条件获取广告列表
	 */
	@Override
	public Result getAdvertisementPage(AdvertisementVoQuery query)  {
		PageHelper.startPage(query.getOffset(), query.getPageSize());
		List<AdvertisementVo> list = advertisementMapper.getAdvertisementListByQuery(query);
		PageInfo<AdvertisementVo> pageInfo = new PageInfo<>(list);

		if (list!=null){
			logger.info("impl---getAdvertisementPage==list size:"+list.size()+",pageInfo size="+pageInfo.getList().size()+",total="+pageInfo.getTotal());
		}
		List<AdvertisementVo> dataList = new ArrayList<>();
		for (AdvertisementVo vo : pageInfo.getList()) {
			//Map<Object, Object> map1 = redisService.hmget("integral:statistics:show");
			Map<Object, Object> map1 = new HashMap<>();
			//位置，广告‘ad’ 商品推荐位：‘goodsRecommend’
			if(map1!=null && !map1.isEmpty()){
				vo.setPv(map1.get("ad:"+vo.getAdvKey()));
			}
			//Map<Object, Object> map2 = redisService.hmget("integral:statistics:click");
			Map<Object, Object> map2 = new HashMap<>();
			if(map2!=null && !map2.isEmpty()){
				vo.setUv(map2.get("ad:"+vo.getAdvKey()));
			}
			//起止时间
			vo.setBeginAndEnd(DateTimeUtils.format(vo.getBeginTime())+"~"+DateTimeUtils.format(vo.getEndTime()));
			//状态
			if(vo.getStatus()!=null){
				if(vo.getStatus()==0){
					vo.setStatusName("启用");
				}else if(vo.getStatus()==1){
					vo.setStatusName("禁用");
				}else if(vo.getStatus()==2){
					vo.setStatusName("已过期");
				}
			}
			//指向 1站内;2站外;3自定义类型;4纯图片
			if(vo.getTargetType()!=null){
				if(vo.getTargetType()==1){
					vo.setTargetName("站内内容");
				}else if(vo.getTargetType()==2){
					vo.setTargetName("站外");
				}else if(vo.getTargetType()==3){
					vo.setTargetName("自定义");
				}else if(vo.getTargetType()==4){
					vo.setTargetName("纯图片");
				}
			}
			dataList.add(vo);
		}
		Result result = new Result(pageInfo,list) ;
		return result;
	}
	/**
	 * 获得分类列表
	 * @param query
	 * @return
	 */
	public List<AdvertisementVo> getVideoTypeListByQuery(AdvertisementVoQuery  query){
		List<AdvertisementVo> list = advertisementMapper.getVideoTypeListByQuery(query);
		if (list == null) {
			list = new ArrayList<AdvertisementVo>();
		}
		return list;
	}


	/**
	 * 根据ID查询广告信息
	 */
	@Override
	public AdvertisementVo getAdvertisementById(AdvertisementVoQuery query)  {
		//ServiceResult<AdvertisementVo> serviceResult = new ServiceResult<AdvertisementVo>();
		AdvertisementVo advertisement = advertisementMapper.getAdvertisementVoById(query.getAdvKey());

		//serviceResult.setData(advertisement);
		//serviceResult.setOk(true);
		return advertisement;
	}

	/**
	 * 保存广告
	 * @param advertisementVo
	 * @return
	 */
	@Transactional
	public  Result saveAdvertisementVo(AdvertisementVo advertisementVo) {
		int count = 0 ;
		if (advertisementVo.getAdvKey() == null) {//新增广告
			advertisementVo.setAdvKey(PkCreat.getTablePk());
			count = advertisementMapper.insertAdvertisement(advertisementVo);
		}else {
			count = advertisementMapper.updateAdvertisementBySelective(advertisementVo);
		}
		if (count > 0) {
			Result result = new Result(
					HttpConstant.CODE.SUCCESS,
					HttpConstant.RET.SUCCESS,
					"保存成功",
					advertisementVo);
			return result;
		}
		Result result = new Result(
				HttpConstant.CODE.SUCCESS,
				HttpConstant.RET.FAIL,
				"保存失败",
				advertisementVo);
		return result;
	}
	/**
	 * 修改广告状态
	 * @param advertisementVo
	 * @return
	 */
	@Transactional
	public ServiceResult<AdvertisementVo> updateStatus(AdvertisementVo advertisementVo){
		ServiceResult<AdvertisementVo> serviceResult = new ServiceResult<AdvertisementVo>(false);
		if(advertisementVo.getTargetType()!=null&&advertisementVo.getTargetType()==1&&advertisementVo.getStatus()==0){
			//广告类型商品详情的：将广告1发布时，将商品1更新为已上线状态
			int count1 = advertisementMapper.updateAdvertisementBySelective(advertisementVo);
			/*Goods goods = new Goods();
			goods.setGoodsKey(advertisementVo.getTargetUrl());
			goods.setStatus(new Byte("0"));
			int count2 = goodsMapper.updateGoodsBySelective(goods);*/
			int count2 = 0;
			if (count1 > 0 && count2>0) {
				serviceResult.setData(advertisementVo);
				serviceResult.setOk(true);
				serviceResult.setComment("修改成功");
				return serviceResult ;
			}
			serviceResult.setComment("修改失败");

		}else{
			int count = advertisementMapper.updateAdvertisementBySelective(advertisementVo);
			if (count > 0) {
				serviceResult.setData(advertisementVo);
				serviceResult.setOk(true);
				serviceResult.setComment("修改成功");
				return serviceResult ;
			}
			serviceResult.setComment("修改失败");
		}

		return serviceResult ;
	}



	/**
	 * 修改排序
	 *
	 * @param list
	 * @return
	 */
	@Transactional
	public boolean modifyOrderNum(List<AdvertisementVo> list) {
		boolean flag = true;
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				AdvertisementVo bean = list.get(i);
				int count = advertisementMapper.updateAdvertisementBySelective(bean);
				if (1 != count) {
					flag = false;
					break;
				}
			}
		}

		return flag;
	}
	/**
	 * 查询商品分类
	 * @param categoryVo
	 * @return
	 */
	/*public List<GoodsCategoryVo> getGoodsCategoryList(GoodsCategoryVoQuery categoryVo){
		List<GoodsCategoryVo> list =goodsCategoryMapper.getGoodsCategoryListByQuery(categoryVo);
		return list;
	}*/
	/**
	 * 根据查询条件获取商品列表
	 */
	@Override
	/*public ServiceResult<PageVo<GoodsVo>> getGoodsPage(GoodsVoQuery query)  {
		ServiceResult<PageVo<GoodsVo>> serviceResult = new ServiceResult<PageVo<GoodsVo>>();
		PageHelper.offsetPage(query.getOffset(), query.getPageSize());
		List<GoodsVo> list = goodsMapper.getAdvGoodsListByQuery(query);

		PageVo<GoodsVo> pageVo = new PageVo<GoodsVo>((Page<GoodsVo>) list);
		serviceResult.setData(pageVo);
		return serviceResult;
	}*/
	/**
	 * 检查广告发布数量
	 *
	 * @return
	 */
	public boolean checkReleaseCount() {
		boolean flag = true;
		Integer count = advertisementMapper.getAdvReleaseCount();
		if(count !=null ){
			if(count.intValue()>4){
				flag = false;
			}
		}

		return flag;
	}

	/**
	 * 商品是否存在
	 * @param advertisementVo
	 * @return
	 */
	public boolean checkGoodsExist(AdvertisementVo advertisementVo) {
		boolean flag = false;
		/*if(StringUtils.isNotBlank(advertisementVo.getAdvKey())){//更新发布操作、发布操作
			AdvertisementVo vo = advertisementMapper.getAdvertisementVoById(advertisementVo.getAdvKey());
			if(vo !=null ){
				*//*if(StringUtils.isNotBlank(vo.getTargetUrl())){
					GoodsVoQuery query = new GoodsVoQuery();
					query.setGoodsKey(vo.getTargetUrl());
					List<GoodsVo> list = goodsMapper.getAdvGoodsListByQuery(query);
					if(list !=null && list.size()>0){
						flag = true;
					}
				}*//*
				if(StringUtils.isNotBlank(advertisementVo.getTargetUrl())){
					GoodsVoQuery query = new GoodsVoQuery();
					query.setGoodsKey(advertisementVo.getTargetUrl());
					List<GoodsVo> list = goodsMapper.getAdvGoodsListByQuery(query);
					if(list !=null && list.size()>0){
						flag = true;
					}
				}
			}
		}else{//新增发布操作
			GoodsVoQuery query = new GoodsVoQuery();
			query.setGoodsKey(advertisementVo.getTargetUrlGoods());
			List<GoodsVo> list = goodsMapper.getAdvGoodsListByQuery(query);
			if(list !=null && list.size()>0){
				flag = true;
			}
		}*/


		return flag;
	}


}

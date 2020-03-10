/**
* @Title: SettingServiceApi.java
* @Package com.haikan.iptv.service.Setting
* @Description: TODO
* @author mayi
* @date 2020年2月18日
* @version V1.0
*/
package com.haikan.iptv.service.Setting;

import com.haikan.iptv.bean.setting.vo.SettingVo;
import com.haikan.iptv.common.util.bean.ServiceResult;

/**
* @ClassName: SettingServiceApi
* @Description: TODO
* @author mayi
* @date 2020年2月18日
*
*/
public interface SettingServiceApi {

	/**
	 * 
	* @Title: saveSetting
	* @Description: 保存配置信息
	* @param @param vo
	* @param @return    参数
	* @return ServiceResult<SettingVo>    返回类型
	* @throws
	* @author mayi
	* @date 2020年2月18日
	 */
	public ServiceResult<SettingVo> saveSetting(SettingVo vo);
	/**
	 * 
	* @Title: getSettingByName
	* @Description: 根据名称获得设置信息
	* @param @param name
	* @param @return    参数
	* @return ServiceResult<SettingVo>    返回类型
	* @throws
	* @author mayi
	* @date 2020年2月18日
	 */
	public ServiceResult<SettingVo> getSettingByName(String name);
}

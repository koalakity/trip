package com.wx.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foxinmy.weixin4j.mp.WeixinProxy;
import com.foxinmy.weixin4j.mp.api.OauthApi;
import com.foxinmy.weixin4j.payment.WeixinPayProxy;
import com.foxinmy.weixin4j.util.Weixin4jSettings;

@Configuration
public class WeixinConfig {
	
	/**微信设置
	 * @return
	 */
	@Bean
	public Weixin4jSettings Weixin4jSettings(){
		return new Weixin4jSettings();
	}
	
	/**微信支付代理
	 * @param settings
	 * @return
	 */
	@Bean
	public WeixinPayProxy weixinPayProxy(Weixin4jSettings settings){
		return new WeixinPayProxy(settings);
	}
	
	/**微信公众品台接口代理
	 * @param settings
	 * @return
	 */
	@Bean
	public WeixinProxy WeixinProxy(Weixin4jSettings settings){
		return new WeixinProxy(settings);
	}
	
	/**微信授权
	 * @return
	 */
	@Bean
	public OauthApi OauthApi(){
		return new OauthApi();
	}

}

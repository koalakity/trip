package com.wx.trip.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import com.foxinmy.weixin4j.mp.api.OauthApi;
import com.foxinmy.weixin4j.mp.model.OauthToken;
import com.foxinmy.weixin4j.type.TicketType;
import com.foxinmy.weixin4j.util.DigestUtil;
import com.foxinmy.weixin4j.util.MapUtil;
import com.foxinmy.weixin4j.util.MessageUtil;
import com.wx.trip.service.vo.Result;


@Controller
public class WeixinController {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OauthApi oauthApi;
	
	@Autowired
	private WeixinProxy weixinProxy;

	@Value("${weixin.token}")
	private String token;
	
	@RequestMapping("/weixin/notify")
	@ResponseBody
	public String weixPayNotify(@RequestBody String msg, HttpServletRequest req) {
		// TODO 缺少签名校验
		LOGGER.info("支付消息通知.......");
		Document document = null;
		try {
			document = DocumentHelper.parseText(msg);
		} catch (DocumentException e) {
			LOGGER.error("", e);
		}
		Element root = document.getRootElement();
		LOGGER.info(root.asXML());
		String returnCode = root.elementText("return_code");
		if ("FAIL".equals(returnCode)) {
			return null;// 消息通讯失败
		}
		//TODO 支付成功
		return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
	}	
	
	

	/**微信授权回调
	 * @param code
	 * @param state
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authorizeCallback")
	public String authorizeCallback(String code, String state, HttpServletRequest request) throws Exception {
		OauthToken oauthToken = oauthApi.getOauthToken(code);
		return "redirect:" + state + "?openId=" + oauthToken.getOpenId();
	}

	/**微信jsconfig签名
	 * @param url
	 * @return
	 */
	@RequestMapping("/weixin/getWeixinJSConfig")
	@ResponseBody
	public Result<JSONObject> getWeixinJSConfig(String url) {
		Result<JSONObject> result = new Result<JSONObject>(Result.Type.SUCCESS);
		JSONObject conf = new JSONObject();
		try {
			conf.put("jsapi_ticket", weixinProxy.getTicketHolder(TicketType.jsapi).getAccessToken());
		} catch (WeixinException e) {
			result.setType(Result.Type.FAILURE);
			result.addMessage("获取sapi_ticket失败");
			LOGGER.error("获取sapi_ticket失败", e);
			return result;
		}
		conf.put("noncestr", "123456");
		conf.put("timestamp", new Date().getTime() / 1000 + "");
		conf.put("url", url);
		StringBuilder sb = new StringBuilder();
		// a--->string1
		sb.append(MapUtil.toJoinString(conf, false, false, null));
		String signature = DigestUtil.SHA1(sb.toString());
		conf.put("signature", signature);
		conf.put("appId", weixinProxy.getWeixinAccount().getId());
		result.setData(conf);
		return result;

	}
	
	/**
	 * 微信签名 加密/校验流程如下： 1. 将token、timestamp、nonce三个参数进行字典序排序 2.
	 * 将三个参数字符串拼接成一个字符串进行sha1加密 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * 
	 * @param signature
	 *            微信加密签名
	 * @param timestamp
	 *            生成签名的时间戳
	 * @param nonce
	 *            随机数
	 * @param echostr
	 *            生成签名的随机串
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/weixin", method = RequestMethod.GET)
	public String verify(String signature, String timestamp, String nonce, String echostr,HttpServletRequest req) {
		LOGGER.info("微信校验参数:{}",JSONObject.toJSON(req.getParameterMap()));
		if (MessageUtil.signature(token, timestamp, nonce).equals(signature)) {
			return echostr;
		} else {
			return "";
		}
	}

	/**
	 * 微信事件推送
	 * 
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/weixin", method = RequestMethod.POST)
	public String index(@RequestBody String msg) {
		try {
			Document document = DocumentHelper.parseText(msg);
			Element root = document.getRootElement();
			LOGGER.info(root.asXML());
			String openId = root.elementText("FromUserName");
			LOGGER.info("微信用户：" + openId);
		} catch (Exception e) {
			LOGGER.error("推送消息异常", e);
		}
		return "";
	}
}

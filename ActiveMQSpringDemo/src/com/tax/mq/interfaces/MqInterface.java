package com.tax.mq.interfaces;

/**
 * 服务请求地址
 * @author zxt
 *
 */
public interface MqInterface {
	
	/**
	 * 请求地址
	 * @param xmlData 请求xml
	 * @return 响应xml字符
	 */
	public String sendData(String xmlData);
}

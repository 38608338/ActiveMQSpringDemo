package com.tax.mq.service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.BindingType;

import com.tax.mq.interfaces.MqInterface;
import com.tgb.SpringActivemq.mq.producer.queue.QueueSendAndReceive;

@WebService(targetNamespace = "http://interfaces.mq.tax.com")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class MqSend implements MqInterface {
	@Resource 
	QueueSendAndReceive queueSendAndReceive;

	public String sendData(String xmlData){
		String opt="";
		try {
			opt = queueSendAndReceive.send("test.queue2", xmlData);
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}	
	
}

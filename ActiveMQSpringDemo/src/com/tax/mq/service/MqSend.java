package com.tax.mq.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.BindingType;

import com.tax.mq.interfaces.MqInterface;

@WebService(targetNamespace = "http://interfaces.mq.tax.com")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
//@BindingType(value="http://www.w3.org/2003/05/soap/bindings/HTTP/")
public class MqSend implements MqInterface {

	@WebMethod
	public String sendData(@WebParam(targetNamespace = "http://interfaces.mq.tax.com") String xmlData){
		System.out.println(xmlData);
		return "done::"+xmlData;
	}	
	
}

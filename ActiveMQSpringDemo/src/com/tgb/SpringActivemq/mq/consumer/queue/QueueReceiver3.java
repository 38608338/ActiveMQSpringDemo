
package com.tgb.SpringActivemq.mq.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 
 * @author liang
 * @description  队列消息监听器
 * 
 */
@Component
public class QueueReceiver3 implements MessageListener {
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;//通过@Qualifier修饰符来注入对应的bean

	//@Override
	public void onMessage(Message message) {
		try {
			final String text = ((TextMessage)message).getText();
			final String jmsCorrelationID = message.getJMSCorrelationID();
			System.out.println("QueueReceiver3接收到消息:"+text);
			
			jmsTemplate.send(message.getJMSReplyTo(),new MessageCreator() {
			//@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage tmsg = session.createTextMessage("done::"+text);
				tmsg.setJMSCorrelationID(jmsCorrelationID);
				return tmsg;
			}
		});
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

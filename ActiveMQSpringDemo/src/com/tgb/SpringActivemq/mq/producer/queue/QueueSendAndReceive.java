package com.tgb.SpringActivemq.mq.producer.queue;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
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
 * @description  队列消息生产者，发送消息到队列
 * 
 */
@Component("QueueSendAndReceive")
public class QueueSendAndReceive {
	
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;//通过@Qualifier修饰符来注入对应的bean
	
	/**
	 * 发送一条消息到指定的队列（目标）
	 * @param queueName 队列名称
	 * @param message 消息内容
	 * @return 返回消息
	 */
	public String send(String queueName,final String message){
			Message replyMsg = jmsTemplate.sendAndReceive(queueName, new MessageCreator() {
			//@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage tmsg = session.createTextMessage(message);
				tmsg.setJMSCorrelationID(UUID.randomUUID().toString());
				return tmsg;
			}
		});
		try {  
            if (replyMsg instanceof TextMessage) {  
                TextMessage textMessage = (TextMessage) replyMsg;  
                return textMessage.getText();  
            }  
        } catch (JMSException e) {  
            //Handle the exception appropriately  
        }
		return "failed";	
	}
	
}

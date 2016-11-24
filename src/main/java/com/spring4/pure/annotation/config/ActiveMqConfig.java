package com.spring4.pure.annotation.config;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
public class ActiveMqConfig {
	
	@Value("${activemq.url}")
	private String url;
	
	@Value("${activemq.queue.name}")
	private String listenerQueueName;

	@Bean(name = "amqConnectionFactory")
	public ActiveMQConnectionFactory getActiveMQConnectionFactory(){
		ActiveMQConnectionFactory  amqConnectionFactory= new ActiveMQConnectionFactory();
		amqConnectionFactory.setBrokerURL(this.url);
		amqConnectionFactory.setUseAsyncSend(Boolean.TRUE);
		return amqConnectionFactory;
	}
	
	@Bean(name = "connectionFactory")
	public CachingConnectionFactory getCachingConnectionFactory(){
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setTargetConnectionFactory(getActiveMQConnectionFactory());
		connectionFactory.setSessionCacheSize(20);
		return connectionFactory;
	}
	
	/**队列*/
	@Bean(name = "jmsQueueTemplate")
	public JmsTemplate getJmsQueueTemplate(){
		JmsTemplate jmsQueueTemplate = new JmsTemplate();
		jmsQueueTemplate.setConnectionFactory(getCachingConnectionFactory());
		jmsQueueTemplate.setPubSubDomain(Boolean.FALSE);
		return jmsQueueTemplate;
	}
	
	/**发布/订阅*/
	@Bean(name = "jmsTopicTemplate")
	public JmsTemplate getJmsTopicTemplate(){
		JmsTemplate jmsTopicTemplate = new JmsTemplate();
		jmsTopicTemplate.setConnectionFactory(getCachingConnectionFactory());
		jmsTopicTemplate.setPubSubDomain(Boolean.TRUE);
		return jmsTopicTemplate;
	}
	
	@Bean
	public DefaultMessageListenerContainer getDefaultMessageListenerContainer(){
		DefaultMessageListenerContainer listener = new DefaultMessageListenerContainer();
		listener.setConnectionFactory(getCachingConnectionFactory());
		listener.setDestinationName(this.listenerQueueName);
		listener.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("============"+textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		return listener;
	}
}

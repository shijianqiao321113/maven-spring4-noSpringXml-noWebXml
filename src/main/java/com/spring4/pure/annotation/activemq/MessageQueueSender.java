package com.spring4.pure.annotation.activemq;

import java.io.Serializable;
import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.StreamMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueSender {

	@Autowired
	private JmsTemplate jmsQueueTemplate;

	public void sendTextMessage(final String queueName, final String txtMessage) {
		jmsQueueTemplate.send(queueName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(txtMessage);
			}
		});
	}

	public void sendObjectMessage(final String queueName, final Object objectMessage) {
		jmsQueueTemplate.send(queueName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage((Serializable) objectMessage);
			}
		});
	}

	public void sendMapMessage(final String queueName, final Map<String, Object> mapMessage) {
		jmsQueueTemplate.send(queueName, new MessageCreator() {
			@SuppressWarnings("unchecked")
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				for (Map.Entry<String, Object> entry : ((Map<String, Object>) mapMessage).entrySet()) {
					mapMessage.setObject(entry.getKey(), entry.getValue());
				}
				return mapMessage;
			}
		});
	}

	public void sendByteMessage(final String queueName, final byte[] message) {
		jmsQueueTemplate.send(queueName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				BytesMessage bytesMessage = session.createBytesMessage();
				bytesMessage.writeBytes(message);
				return bytesMessage;
			}
		});
	}

	public void sendStreamMessage(final String queueName, final Object message) {
		jmsQueueTemplate.send(queueName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				StreamMessage streamMessage = session.createStreamMessage();
				streamMessage.writeObject(message);
				return streamMessage;
			}
		});
	}
}

package ob.assignments.interproccommunication.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


/**
 * RabbitMQ configurations
 */
@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.reqqueue}")
    private String reqqueue;
    @Value("${rabbitmq.respqueue}")
    private String respqueue;
    @Value("${rabbitmq.reqexchange}")
    private String reqexchange;
    @Value("${rabbitmq.respexchange}")
    private String respexchange;
    @Value("${rabbitmq.routingkey}")
    private String routingkey;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;
    @Value("${rabbitmq.host}")
    private String host;

    @Bean
    Queue requestQueue() {
        return new Queue(reqqueue);
    }

    @Bean
    Queue responseQueue() {
        return new Queue(respqueue);
    }

    @Bean
    DirectExchange reqexchange() {
        return new DirectExchange(reqexchange);
    }

    @Bean
    DirectExchange respexchange() {
        return new DirectExchange(respexchange);
    }

    @Bean
    Binding requestBinding() {
        return BindingBuilder
                .bind(requestQueue())
                .to(reqexchange())
                .with(reqqueue);
    }

    @Bean
    Binding responseBinding() {
        return BindingBuilder
                .bind(responseQueue())
                .to(respexchange())
                .with(respqueue);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(reqexchange);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        rabbitTemplate.setReplyAddress(respexchange+"/"+respqueue);
        rabbitTemplate.setReplyTimeout(TimeUnit.SECONDS.toMillis(2));
        return rabbitTemplate;
    }

    @Bean
    public SimpleMessageListenerContainer responseContainer(ConnectionFactory connectionFactory, RabbitTemplate rabbitTemplate) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(responseQueue());
        container.setMessageListener(rabbitTemplate);
        return container;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

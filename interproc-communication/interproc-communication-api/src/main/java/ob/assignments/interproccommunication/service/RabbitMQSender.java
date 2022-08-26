package ob.assignments.interproccommunication.service;

import ob.assignments.interproccommunication.model.Client;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RabbitMQ producer to send corresponding message
 * depending on the Client controller requests
 */
@Service
public class RabbitMQSender {

    private RabbitTemplate rabbitTemplate;
    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${rabbitmq.reqexchange}")
    private String reqexchange;
    @Value("${rabbitmq.reqqueue}")
    private String reqqueue;

    public List<Client> sendGetClients() {
        return (List<Client>) rabbitTemplate.convertSendAndReceive(reqexchange, reqqueue, "getClients");
    }

    public String sendCreateClient(Client client) {
        System.out.println("Sending: " + client);
        return (String) rabbitTemplate.convertSendAndReceive(reqexchange, reqqueue, client);
    }
}

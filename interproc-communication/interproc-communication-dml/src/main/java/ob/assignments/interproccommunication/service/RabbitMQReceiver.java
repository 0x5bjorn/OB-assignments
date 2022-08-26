package ob.assignments.interproccommunication.service;

import ob.assignments.interproccommunication.model.Client;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * RabbitMQ multi-method listener for receiving messages
 * and handling it depending on input data type
 */
@RabbitListener(queues = "${rabbitmq.reqqueue}")
@Component
public class RabbitMQReceiver {

    private ClientService clientService;
    @Autowired
    public RabbitMQReceiver(ClientService clientService) {
        this.clientService = clientService;
    }

    @RabbitHandler
    public String receive(Client client) {
        System.out.println("DML received: " + client);
        Client createdClient = clientService.createClient(client);

        return createdClient+" was successfully created";
    }

    @RabbitHandler
    public List<Client> receive(String request) {
        System.out.println("Querying client list: " + request);

        return clientService.getClientList();
    }
}

package ob.assignments.interproccommunication.controller;

import ob.assignments.interproccommunication.model.Client;
import ob.assignments.interproccommunication.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Client controller
 */
@RestController
public class ClientController {

    private RabbitMQSender rabbitMQSender;
    @Autowired
    public ClientController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @GetMapping(value = "/getClients")
    public List<Client> getClientList() {
        return rabbitMQSender.sendGetClients();
    }

    @PostMapping(value = "/addClient")
    public String createClient(@RequestBody Client client) {
        return rabbitMQSender.sendCreateClient(client);
    }
}

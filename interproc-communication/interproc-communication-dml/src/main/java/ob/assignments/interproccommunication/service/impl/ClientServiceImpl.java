package ob.assignments.interproccommunication.service.impl;

import ob.assignments.interproccommunication.model.Client;
import ob.assignments.interproccommunication.repo.ClientRepository;
import ob.assignments.interproccommunication.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Client service implementation for handling and processing Client controller requests
 * and eventually interacting with db or collection
 */
@Service
public class ClientServiceImpl implements ClientService {

    /**
     * Environmental variable editable in application.properties file
     * to set db or collection storage type (db/coll)
     */
    @Value("${ipc.dml.storage}")
    private String storage;

    private List<Client> clientsColletion = new ArrayList<Client>();
    private final AtomicLong counter = new AtomicLong();

    private ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Get all clients in List
     * @return List of clients
     */
    @Override
    public List<Client> getClientList() {
        List<Client> result = new ArrayList<Client>();
        if (storage.equals("db")) {
            result = clientRepository.findAll();
        } else if (storage.equals("coll")) {
            result = clientsColletion;
        }

        return result;
    }

    /**
     * Create new client
     * @param client New client to be created
     * @return Created client
     */
    @Override
    public Client createClient(Client client) {
        Client newClient = new Client();
        if (storage.equals("db")) {
            newClient = clientRepository.save(client);
        } else if (storage.equals("coll")) {
            newClient = client;
            newClient.setId(counter.incrementAndGet());
            clientsColletion.add(newClient);
        }

        return newClient;
    }
}

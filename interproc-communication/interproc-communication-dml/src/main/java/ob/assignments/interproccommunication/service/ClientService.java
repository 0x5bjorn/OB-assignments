package ob.assignments.interproccommunication.service;

import ob.assignments.interproccommunication.model.Client;

import java.util.List;

/**
 * Client service interface for handling and processing Client controller requests
 */
public interface ClientService {

    List<Client> getClientList();
    Client createClient(Client client);
}

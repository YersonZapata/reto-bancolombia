package co.com.reto.model.client.gateways;

import co.com.reto.model.client.Client;

public interface ClientRepository {
    Client getClientInfo(String identification);
}

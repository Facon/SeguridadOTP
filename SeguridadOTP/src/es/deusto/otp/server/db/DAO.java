package es.deusto.otp.server.db;

import es.deusto.otp.data.Client;

public interface DAO {
	public void addPeer(Client peer);
}

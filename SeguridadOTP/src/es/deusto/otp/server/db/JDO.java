package es.deusto.otp.server.db;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.otp.data.Client;

public class JDO implements DAO {
	private static JDO instance = null;
	private PersistenceManagerFactory pmf;
	
	private JDO() {
		pmf = JDOHelper.getPersistenceManagerFactory("SP2PJDO");
	}
	
	public static JDO getInstance() {
		if (instance == null) {
			instance = new JDO();
		}
			
		return instance;
	}

	@Override
	public void addPeer(Client peer) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try
		{
		    tx.begin();
		    
		    pm.makePersistent(peer);
		    
		    tx.commit();
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		       
		    }
		    pm.close();
		}
	}
}

package es.deusto.otp.server.db;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.otp.data.User;

public class JDO implements DAO {
	private static JDO instance = null;
	private PersistenceManagerFactory pmf;

	private JDO() {
		pmf = JDOHelper.getPersistenceManagerFactory("JDO");
	}

	public static JDO getInstance() {
		if (instance == null) {
			instance = new JDO();
		}

		return instance;
	}

	@Override
	public void addUser(User peer) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			pm.makePersistent(peer);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();

			}
			pm.close();
		}
	}

	@Override
	public User getUser(String nick) {
		// Perform some query operations
		User user;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<User> e = pm.getExtent(User.class);
			Query q = pm.newQuery(e, "nick == " + "\"" + nick + "\"");
			q.setUnique(true);
			user = (User) q.execute();

			tx.commit();
		} catch (NullPointerException e) {
			user = null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return user;
	}
}

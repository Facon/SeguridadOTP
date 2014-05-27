package es.deusto.otp.server.db;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.otp.data.OTPCode;
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
	public void addUser(User user) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			pm.makePersistent(user);

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
			Query q = pm.newQuery(User.class);
			q.setFilter("nick == " + nick);
			q.setUnique(true);
			user = (User) q.execute();
			System.out.println(q.toString());

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

	@Override
	public void addOTPCode(OTPCode code) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			pm.makePersistent(code);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				System.out.println(tx);
				tx.rollback();
			}
			pm.close();
		}		
	}

	@Override
	public OTPCode getOTPCode(User user) {
		// Perform some query operations
		OTPCode otpcode;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<OTPCode> e = pm.getExtent(OTPCode.class);
			Query q = pm.newQuery(e);
			q.setFilter("user.nick == " + user.getNick());
			q.setUnique(true);
			otpcode = (OTPCode) q.execute();

			tx.commit();
		} catch (NullPointerException e) {
			otpcode = null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return otpcode;
	}
}

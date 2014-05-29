package es.deusto.otp.server.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import es.deusto.otp.data.OTPCode;
import es.deusto.otp.data.User;

public class ORMLite implements DAO {
	private static ORMLite instance = null;
	private ConnectionSource cs;

	private ORMLite() {
        try {
        	// this uses h2 by default but change to match your database
            String databaseUrl = "jdbc:sqlite:bd.sqlite";
            // create a connection source to our database
			cs = new JdbcConnectionSource(databaseUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ORMLite getInstance() {
		if (instance == null) {
			instance = new ORMLite();
		}

		return instance;
	}
	
	@Override
	public void createTables() {
		try {
			TableUtils.createTableIfNotExists(cs, User.class);
			TableUtils.createTableIfNotExists(cs, OTPCode.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void addUser(User user) {
		try {
			Dao<User, String> userDao = DaoManager.createDao(cs, User.class);
			userDao.createIfNotExists(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(String nick) {
		try {
			Dao<User, String> userDao = DaoManager.createDao(cs, User.class);
			return userDao.queryForId(nick);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void addOTPCode(OTPCode code) {
		try {
			Dao<OTPCode, String> otpcodeDao = DaoManager.createDao(cs, OTPCode.class);
			otpcodeDao.createIfNotExists(code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public OTPCode getOTPCode(User user) {
		try {
			Dao<OTPCode, String> otpcodeDao = DaoManager.createDao(cs, OTPCode.class);
			List<OTPCode> list = otpcodeDao.query(otpcodeDao.queryBuilder().where().eq("user_id", user.getNick()).prepare());
			
			Date date = new Date(10000);
			OTPCode otp, otp2;
			otp = null;
			
			Iterator<OTPCode> iter = list.iterator();
			
			while (iter.hasNext()) {
				otp2 = iter.next();
				
				if (otp2.getDate().after(date)) {
					date = otp2.getDate();
					otp = otp2;
				}
			}
			
			return otp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

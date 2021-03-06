package ra.jademy.persistance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeSet;

import ra.jademy.domain.User;

public class UserDAO {

	private Properties importFile;
	private static UserDAO soleInstance = new UserDAO();

	private UserDAO() {
		importFile = new Properties() {
		    public synchronized Enumeration<Object> keys() {
		        return Collections.enumeration(new TreeSet<Object>(keySet()));
		    }
		};
		try {
			FileInputStream stream = new FileInputStream("user.properties");
			importFile.load(stream);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open user.properties", e);
		}
	}

	public static UserDAO getInstance() {
		return soleInstance;
	}

	public User getUserByUsername(String username) {
		int i = 0;
		while (true) {
			i++;
			String dbUsername = importFile.getProperty("user[" + i + "].username");
			if (dbUsername == null) {
				return null;
			}
			if (dbUsername.equals(username)) {
				String dbPassword = importFile.getProperty("user[" + i + "].password");
				String dbEmailAddress = importFile.getProperty("user[" + i + "].emailAddress");
				return new User(dbUsername, dbPassword, dbEmailAddress);
			}
		}

	}


	public int getNextUserIndex() {
		int i = 0;
		while (true) {
			i++;
			String dbUsername = importFile.getProperty("user[" + i + "].username");
			if (dbUsername == null) {
				return i;
			}
		}
	}

	public void createUser(User user) {
		int index = getNextUserIndex();
		importFile.setProperty("user["+ index +"].username", user.getUsername());
		importFile.setProperty("user["+ index +"].password", user.getPassword());
		importFile.setProperty("user["+ index +"].emailAddress", user.getEmailAddress());
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("user.properties");
			importFile.store(fos, new Date().toString());
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException("Cannot save user.properties", e);

		}

	}
	
	public void updateUser(User user){
		int i = 0;
		while (true) {
			i++;
			String dbUsername = importFile.getProperty("user[" + i + "].username");
			if (dbUsername == null) {
				return;
			}
			if (dbUsername.equals(user.getUsername())) {
//				System.out.println(user.getPassword());
//				System.out.println(user.getEmailAddress());
//				System.out.println(user.getUUID());
//				System.out.println(i);
				importFile.setProperty("user["+ i +"].password", user.getPassword());
				importFile.setProperty("user["+ i +"].emailAddress", user.getEmailAddress());
				importFile.setProperty("user["+ i +"].uuid", user.getUUID());
				FileOutputStream fos;
				try {
					fos = new FileOutputStream("user.properties");
					importFile.store(fos, new Date().toString());
					fos.close();
				} catch (IOException e) {
					throw new RuntimeException("Cannot save user.properties", e);

				}
			}
		
	}

}
	
	
public User getUserByUuid (String uuid){
	int i = 0;
	while (true) {
		i++;
		String dbUuid = importFile.getProperty("user[" + i + "].uuid");
		if (dbUuid == null) {
			return null;
		}
		if (dbUuid.equals(uuid)) {
			String dbUsername = importFile.getProperty("user[" + i + "].username");
			String dbPassword = importFile.getProperty("user[" + i + "].password");
			String dbEmailAddress = importFile.getProperty("user[" + i + "].emailAddress");
			User user = new User(dbUsername, dbPassword, dbEmailAddress, uuid);
			return user;
		}
	}
}
	
	
}

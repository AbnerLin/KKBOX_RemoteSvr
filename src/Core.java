import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

public class Core {
	private Properties prop;
	private static Core _instance;
	public static User32 user32;
	public static Socket socketListener;
	public static RemoteController controller;
	public static String appClassName = "KKBOX_CLASS";

	public static void setUpInstance() {
		_instance = new Core();
		user32 = User32.INSTANCE;
		controller = new RemoteController();
		(new SocketServer()).start();
	}

	public Core() {
		try {
			Properties tmp = new Properties();
			tmp.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			this.prop = tmp;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String _getProperty(String key) {
		return prop.getProperty(key);
	}

	public static Core getInstance() {
		return _instance;
	}

//	public void setSocket(Socket socketListener) {
//		Core.socketListener = socketListener;
//	};

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;

public class SocketServer extends java.lang.Thread {

	private ServerSocket server;

	public SocketServer() {
		try {
			server = new ServerSocket(Integer.parseInt(Core.getInstance()._getProperty("SERVER_LISTEN_PORT")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			synchronized (server) {
				try {
					Core.socketListener = server.accept();
					
//					System.out.println("SourceIP : " + Core.socketListener.getInetAddress());
					
	                InputStream is = Core.socketListener.getInputStream();
	                InputStreamReader isr = new InputStreamReader(is);
	                BufferedReader br = new BufferedReader(isr);
	                String sourceData = br.readLine();
	                System.out.println(sourceData);
	                
	                /* send the responce to client */
	                String returnMessage = Core.controller.listener(sourceData) + "\n";
	                
	                OutputStream os = Core.socketListener.getOutputStream();
	                OutputStreamWriter osw = new OutputStreamWriter(os);
	                BufferedWriter bw = new BufferedWriter(osw);
	                bw.write(returnMessage);
	                bw.flush();
	                
					Core.socketListener.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class Main {

	/**
	 * @Username ShaoYang, Lin
	 * @date 2013/3/15
	 */

	public static void main(String[] args) throws IOException {
		Core.setUpInstance();
		
		JFrame frame = new JFrame("Remote KKBOX");
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout());
		frame.setResizable(false);

		ImageIcon img = new ImageIcon("icon/kkboxRemote.png");
		frame.setIconImage(img.getImage());

		JLabel labelIP = new JLabel(convertIP().toString(), JLabel.CENTER);
		labelIP.setFont(new Font("Arial", 1, 35));
		labelIP.setForeground(Color.red);
		labelIP.setPreferredSize(new Dimension(200, 80));
		Border border = BorderFactory.createLineBorder(Color.BLUE, 0);
		labelIP.setBorder(border);

		JLabel note = new JLabel("Input the number to mobileApp.",
				JLabel.CENTER);
		note.setFont(new Font("Arial", 1, 12));
		note.setPreferredSize(new Dimension(200, 25));
		note.setBorder(border);

		JLabel coderName = new JLabel("By Shao-Yang, Lin", JLabel.SOUTH_EAST);
		coderName.setPreferredSize(new Dimension(285, 80));
		coderName.setFont(new Font("Arial", 0, 11));
		coderName.setBorder(border);

		Container content = frame.getContentPane();
		content.setLayout(new FlowLayout());
		content.setBackground(Color.white);
		content.add(labelIP);
		content.add(note);
		content.add(coderName);

		frame.setVisible(true);
	}

	public static String convertIP() {
		// /* get IP and set to number */
		Socket s;
		BigInteger big = null;
		try {
			s = new Socket("www.google.com", 80);
			InetAddress ip = s.getLocalAddress();
			s.close();
			
			String thisIp = ip.toString().replaceAll("/", "");
			String arr[] = thisIp.split("\\.");
			big = new BigInteger(arr[0]);
			big = big.multiply(new BigInteger("16777216"));
			System.out.println(big);
			big = big.add((new BigInteger(arr[1]).multiply(new BigInteger("65536"))));
			System.out.println(big);
			big = big.add((new BigInteger(arr[2]).multiply(new BigInteger("256"))));
			System.out.println(big);
			big = big.add(new BigInteger(arr[3]));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please check you network.");
			return "ERROR";
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please check you network.");
			return "ERROR";
		}
		return big.toString();
	}

}

package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Weatherapp;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class FrmWeatherApp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmWeatherApp frame = new FrmWeatherApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmWeatherApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(173, 44, 149, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Unesite grad:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(42, 47, 121, 13);
		contentPane.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(173, 73, 149, 19);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(173, 102, 149, 19);
		contentPane.add(textField_2);

		JLabel lblUnesiteGDuzinu = new JLabel("Unesite g. duzinu:");
		lblUnesiteGDuzinu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnesiteGDuzinu.setBounds(42, 76, 121, 13);
		contentPane.add(lblUnesiteGDuzinu);

		JLabel lblUnesiteGsirinu = new JLabel("Unesite g.sirinu:");
		lblUnesiteGsirinu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnesiteGsirinu.setBounds(42, 105, 121, 13);
		contentPane.add(lblUnesiteGsirinu);

		JButton btnNewButton = new JButton("PRIKAZI TEMPERATURU");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*	String naziv = textField.getName().trim();
				double geoDuzina = Double.parseDouble(textField_2.getName().trim());
				double geoSirina = Double.parseDouble(textField_1.getName().trim());*/

				try {
					API();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(173, 143, 149, 65);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Temperatura", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(325, 10, 312, 204);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 15, 300, 198);
		panel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

	public static void API () throws IOException {
		String k="";
		StringBuffer responseCont = new StringBuffer();
		//konekcija
		URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=London&appid=f558d63b1653f05ea832002d166020f9");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setReadTimeout(10000);
		con.connect();


		int response = con.getResponseCode();
		if (response!=200) {
			System.err.println("Connection not succesful");
		}
		else {		
			System.out.println("Connection succesful");		
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((k=reader.readLine())!=null) {
				responseCont.append(k);
			}
			reader.close();
			System.out.println(responseCont.append(k));
			
			//JSONObject json = new JSONObject();
			//fali upis 
			//ObjectInputSteram i = new ObjectInputStream(json);

			//System.out.println(JSONObject.toString(k, json));

		}


	}


}

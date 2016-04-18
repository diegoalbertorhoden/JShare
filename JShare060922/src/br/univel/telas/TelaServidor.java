package br.univel.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.awt.event.ActionEvent;

public class TelaServidor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private int intPorta = 5050;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaServidor frame = new TelaServidor();
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
	public TelaServidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTelaInicial = new JLabel("Servidor");
		lblTelaInicial.setBounds(0, 0, 424, 26);
		lblTelaInicial.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTelaInicial.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTelaInicial);
		
		JLabel lblNewLabel = new JLabel("I P");
		lblNewLabel.setBounds(15, 43, 46, 43);
		contentPane.add(lblNewLabel);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				iniciarServico();
				
			}
		});
		btnConectar.setBounds(316, 53, 89, 23);
		contentPane.add(btnConectar);
		
		JButton btnPararOServio = new JButton("Parar o servi\u00E7o");
		btnPararOServio.setBounds(415, 53, 109, 23);
		contentPane.add(btnPararOServio);
		
		JLabel lblPorta = new JLabel("Porta");
		lblPorta.setBounds(168, 57, 46, 14);
		contentPane.add(lblPorta);
		
		textField = new JTextField();
		textField.setText("127.0.0.1");
		textField.setBounds(66, 54, 92, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("5050");
		textField_1.setBounds(217, 54, 89, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 419, 544, -318);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(25, 87, 534, 338);
		contentPane.add(textArea);
	}

	protected void iniciarServico() {
		
		
		
		try{
			servidor = (Servidor) UnicastRemoteObject.exportObject(this, 0);
			registry = LocateRegistry.createRegistry(intPorta);
			registry.rebind(Servidor.NOME, servidor);
			
			mostrar("Servico iniciado");
			
			btnConectar
		}
		
		
		
	}
}

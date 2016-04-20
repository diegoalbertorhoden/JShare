package br.univel.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.univel.comum.Servidor;

public class TelaCliente extends JFrame{

	private static final long serialVersionUID = -1981548995134350542L;
	private Object registry;
	private Servidor servidor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextField txtMeuNome;
	private JTextField textField_1;
	private JTextField textField_2;
	private Object nome;


	public TelaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 11, 58, 25);
		getContentPane().add(lblNome);

		txtMeuNome = new JTextField();
		txtMeuNome.setBounds(54, 15, 246, 20);
		getContentPane().add(txtMeuNome);
		txtMeuNome.setColumns(10);

		JLabel lblIpDeConexo = new JLabel("Ip de conex\u00E3o");
		lblIpDeConexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIpDeConexo.setBounds(10, 47, 102, 25);
		getContentPane().add(lblIpDeConexo);

		JLabel lblPorta = new JLabel("Porta");
		lblPorta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPorta.setBounds(310, 13, 48, 20);
		getContentPane().add(lblPorta);

		textField_1 = new JTextField();
		textField_1.setText("127.0.0.1");
		textField_1.setBounds(105, 51, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setText("5050");
		textField_2.setBounds(368, 15, 58, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				conectar();

			}
		});
		btnConectar.setBounds(232, 50, 89, 23);
		getContentPane().add(btnConectar);

		JButton btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(341, 50, 93, 23);
		getContentPane().add(btnDesconectar);
	}
	protected void conectar() {
		nome = txtMeuNome.getText().trim();
		if (((String) nome).length() == 0) {
			JOptionPane.showMessageDialog(this, "Você precisa digitar um nome!");
			return;
		}
		
		try {
			registry = LocateRegistry.getRegistry(host, intPorta);
	
			servidor = (Servidor) registry.lookup(Servidor.NOME);
			cliente = (Cliente) UnicastRemoteObject.exportObject(this, 0);
	
			// Avisando o servidor que está entrando no Chat.
			servidor.entrarNoChat(meunome, cliente);
	
			buttonDesconectar.setEnabled(true);
	
			buttonConectar.setEnabled(false);
			txtMeuNome.setEnabled(false);
			txfIp.setEnabled(false);
			txfPorta.setEnabled(false);
	
			buttonConectar.setEnabled(false);
			buttonEnviar.setEnabled(true);
			txfMensagem.setEnabled(true);
	
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e
) {e.printStackTrace();

	}
	}

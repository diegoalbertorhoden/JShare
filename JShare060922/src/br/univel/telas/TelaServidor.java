package br.univel.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.univel.arquivos.Arquivo;
import br.univel.comum.Cliente;
import br.univel.comum.Servidor;

public class TelaServidor extends JFrame implements Servidor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private int intPorta = 5050;
	private JTextArea textArea;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");
	private Servidor servidor;
	private Registry registry;
	private JButton btnConectar;
	private JButton btnParar;

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

		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				iniciarServico();

			}
		});
		btnConectar.setBounds(316, 53, 89, 23);
		contentPane.add(btnConectar);

		btnParar = new JButton("Parar o servi\u00E7o");
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				pararServico();
			}
		});
		btnParar.setBounds(415, 53, 109, 23);
		contentPane.add(btnParar);

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

		textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setBounds(25, 87, 534, 338);
		contentPane.add(textArea);
	}

	protected void pararServico() {
		mostrar("SERVIDOR PARANDO O SERVICO.");

		fecharTodosClientes();

		try {
			UnicastRemoteObject.unexportObject(this, true);
			UnicastRemoteObject.unexportObject(registry, true);

			btnConectar.setEnabled(true);

			btnParar.setEnabled(false);

			mostrar("Servico encerrado.");

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void fecharTodosClientes() {

		mostrar("DESCONECTANDO TODOS OS CLIENTES.");

	}

	private Map<String, Cliente> mapaClientes = new HashMap<>();

	private void mostrar(String string) {
		textArea.append(sdf.format(new Date()));
		textArea.append(" -> ");
		textArea.append(string);
		textArea.append("\n");
	}

	protected void iniciarServico() {

		try{
			servidor = (Servidor) UnicastRemoteObject.exportObject(this, 0);
			registry = LocateRegistry.createRegistry(intPorta);
			registry.rebind(Servidor.NOME, servidor);

			mostrar("Servico iniciado");

			btnConectar.setEnabled(false);
			btnParar.setEnabled(true);
		}catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro criando registro, verifique se a porta já não está sendo usada.");
			e.printStackTrace();
		}


	}

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub

	}
}

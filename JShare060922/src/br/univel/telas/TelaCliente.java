package br.univel.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;

public class TelaCliente extends JFrame implements IServer{

	private static final long serialVersionUID = -1981548995134350542L;
	private Object registry;
	private IServer servidor;

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
	private JTextField txtIp;
	private JTextField txtPorta;
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

		txtIp = new JTextField();
		txtIp.setText("127.0.0.1");
		txtIp.setBounds(105, 51, 86, 20);
		getContentPane().add(txtIp);
		txtIp.setColumns(10);

		txtPorta = new JTextField();
		txtPorta.setText("5050");
		txtPorta.setBounds(368, 15, 58, 20);
		getContentPane().add(txtPorta);
		txtPorta.setColumns(10);

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

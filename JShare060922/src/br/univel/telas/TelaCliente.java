package br.univel.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;
import br.univel.diversos.TabelaArquivos;

public class TelaCliente extends JFrame implements IServer{

	private static final long serialVersionUID = -1981548995134350542L;
	private Object registry;
	private IServer servidor;
	private JButton btnConectar;
	private JButton btnDesconectar;
	private Cliente cliente;
	private JButton btnBuscar;
	private JTextField txtNomeArquivo;
	private TabelaArquivos novo;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
					frame.setVisible(true);
					frame.configurar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void configurar() {

		btnConectar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				conectar();
			}
		});

		btnDesconectar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				desconectar();
			}
		});

		btnBuscar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				buscarArquivos();
			}
		});

	}

	//vai até a tabela e realiza a extração de informações da tabela
	protected void buscarArquivos() {

		try{
			Map<Cliente, List<Arquivo>> arquivosdalista = servidor.procurarArquivo(txtNomeArquivo.getText());
			novo = new TabelaArquivos(arquivosdalista);

			table.setModel(novo);
		}catch(RemoteException e){
			e.printStackTrace();
		}

	}

	protected void desconectar() {
		//testar se está esvaziando a instancia*************
		
		try{		
			if(servidor !=null){
				servidor.desconectar(cliente);
				servidor = null;
			}
			btnDesconectar.setEnabled(false);
			btnConectar.setEnabled(true);
			registry = null;
			servidor = null;
		}catch(RemoteException e){
			e.printStackTrace();
		}

	}

	private JTextField txtNome;
	private JTextField txtIp;
	private JTextField txtPorta;
	private Object nome;
	private JTextField txtProcurado;
	private JTable tblArquivos;


	public TelaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome Cliente");
		lblNome.setBounds(0, 0, 93, 29);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(117, 6, 223, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblPorta = new JLabel("Porta");
		lblPorta.setBounds(350, 6, 93, 17);
		lblPorta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblPorta);

		txtPorta = new JTextField();
		txtPorta.setBounds(384, 6, 55, 20);
		txtPorta.setHorizontalAlignment(SwingConstants.CENTER);
		txtPorta.setText("5050");
		getContentPane().add(txtPorta);
		txtPorta.setColumns(10);

		JLabel lblIpDeConexo = new JLabel("Ip de conex\u00E3o");
		lblIpDeConexo.setBounds(0, 34, 93, 23);
		lblIpDeConexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblIpDeConexo);

		txtIp = new JTextField();
		txtIp.setBounds(117, 37, 88, 20);
		txtIp.setHorizontalAlignment(SwingConstants.CENTER);
		txtIp.setText("127.0.0.1");
		getContentPane().add(txtIp);
		txtIp.setColumns(10);

		JLabel lblIpLocal = new JLabel("Ip local");
		lblIpLocal.setBounds(215, 34, 42, 23);
		lblIpLocal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblIpLocal);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(267, 37, 151, 20);
		getContentPane().add(comboBox);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(423, 34, 104, 23);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				conectar();

			}
		});
		getContentPane().add(btnConectar);

		JButton btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(532, 34, 93, 23);
		getContentPane().add(btnDesconectar);

		JLabel lblArquivoProcurado = new JLabel("Arquivo Procurado");
		lblArquivoProcurado.setBounds(0, 62, 182, 23);
		lblArquivoProcurado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblArquivoProcurado);

		txtProcurado = new JTextField();
		txtProcurado.setBounds(187, 62, 291, 20);
		getContentPane().add(txtProcurado);
		txtProcurado.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(513, 62, 65, 23);
		getContentPane().add(btnBuscar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 90, 620, 346);
		getContentPane().add(scrollPane);

		tblArquivos = new JTable();
		scrollPane.setViewportView(tblArquivos);
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

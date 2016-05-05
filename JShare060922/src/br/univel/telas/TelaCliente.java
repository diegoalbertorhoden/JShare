package br.univel.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private Registry registry;
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
	private JTextField txtProcurado;
	private JTable tblArquivos;
	private String nome;
	private Cliente guerreiro;


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
		tblArquivos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
				if(evt.getClickCount()==2){
					Object IP = tblArquivos.getValueAt(tblArquivos.getSelectedRow(), 1);
					Object porta = tblArquivos.getValueAt(tblArquivos.getSelectedRow(), 2);
					Object nomeArquivo = tblArquivos.getValueAt(tblArquivos.getSelectedRow(), 3);
					
					try{
						registry = LocateRegistry.getRegistry((String) IP, (int) porta);
						IServer clienteservidor = (IServer) registry.lookup(IServer.NOME);
						clienteservidor.registrarCliente(cliente);
						Arquivo arquivo = new Arquivo();
						arquivo.setNome((String) nomeArquivo);
						
						byte[] baixarArquivo = clienteservidor.baixarArquivo(arquivo);
						writeFile(new File("C:\\JShare\\Downloads\\"+ arquivo.getNome()),baixarArquivo);
								
					}catch (RemoteException e){
						e.printStackTrace();
					}catch (NotBoundException e){
						e.printStackTrace();
					}
				}
			}
		});
	}
	//http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java
	
	protected void writeFile(File arq, byte[] dados) {
		try{
			Files.write(Paths.get(arq.getPath()), dados, StandardOpenOption.CREATE);
		}catch (IOException e){
			throw new RuntimeException(e);
		}
		
	}

	protected void conectar() {
		//primeiro captura o texto existente no field da pessoa pra identificar
		nome = txtNome.getText().trim();
		//se nao tiver nada, apresenta erro
		if(nome.length()==0){
			JOptionPane.showMessageDialog(this, "digite um nome");
			return;
		}
		//captura o endereço do ip e verifica se está coerente (ex 127.0.0.1)
		String endereco = txtIp.getText().trim();
		if(!endereco.matches("[0-9{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")){
			JOptionPane.showMessageDialog(this, "ip incorreto, corrija");
		}

		String portaPadrao = txtPorta.getText().trim();
		//verificação se a porta é válida
		if(!portaPadrao.matches("[0-9]+")|| portaPadrao.length()>5){
			JOptionPane.showMessageDialog(this, "digitar uma porta válida");
			return;
		}
		int portavalorinteiro = Integer.parseInt(portaPadrao);

		try{
			registry = LocateRegistry.getRegistry(endereco, portavalorinteiro);
			servidor = (IServer) registry.lookup(IServer.NOME);

			cliente = new Cliente();
			cliente.setIp(txtIp.getText());
			cliente.setPorta(Integer.parseInt(txtPorta.getText()));
			cliente.setNome(txtNome.getText());

			//aqui começa a maracutaia legal da parada... rsrsr
			//registrando cliente
			servidor.registrarCliente(cliente);

			//publicando a lista de arquivos...
			List<Arquivo> lista = minhaListaDeArquivos();
			servidor.publicarListaArquivos(cliente, lista);

			//com o esquema de cliente-servidor, deixamos em standby
			iniciaServico();
		}catch (RemoteException e){
			e.printStackTrace();
		}catch (NotBoundException e){
			e.printStackTrace();
		}

	}
	private void iniciaServico() {
		String portaPadrao = txtIp.getText().trim();
		if(!portaPadrao.matches("[0-9]+")|| portaPadrao.length()>5){
			JOptionPane.showMessageDialog(this, "porta incorreta, verifique");
			return;
		}
		int portaNumerica = Integer.parseInt(portaPadrao);
		if (portaNumerica<1024 || portaNumerica>65535){
			JOptionPane.showMessageDialog(this, "faixa de porta errado");
			return;
		}
		try{
			IServer connection = (IServer) UnicastRemoteObject.exportObject(this,0);
			registry = LocateRegistry.createRegistry(portaNumerica);
			registry.rebind(IServer.NOME, connection);

		}catch(RemoteException e){
			JOptionPane.showMessageDialog(this, "nao foi possivel criar registro");
			e.printStackTrace();
		}
	}

	private List<Arquivo> minhaListaDeArquivos() {
		File varrerDiretorio = new File("C:\\JShare\\Uploads\\");
		List<Arquivo> listFiles = new ArrayList<>();
		for(File file : varrerDiretorio.listFiles()){
			if(file.isFile()){
				Arquivo arquivo = new Arquivo();
				arquivo.setNome(file.getName());
				arquivo.setTamanho(file.length());
				listFiles.add(arquivo);
			}
		}

		return listFiles;
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
		List<Arquivo> relacaoArquivos = minhaListaDeArquivos();
		for(Arquivo arquivo : relacaoArquivos){
			if(arquivo.getNome().contains(arq.getNome())){
				//criação do arquivo...
				byte[] readFile = readFile(new File("C:\\JShare\\Uploads\\"+ arq.getNome()));
				return readFile;
			}
		}
		return null;
	}
	protected byte[] readFile(File arq) {
		Path path = Paths.get(arq.getPath());
		try{
			byte[] dados = Files.readAllBytes(path);
			return dados;
		}catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	@Override
	public void desconectar(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub

	}
}

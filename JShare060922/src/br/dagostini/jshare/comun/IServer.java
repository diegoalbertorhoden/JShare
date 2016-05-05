package br.dagostini.jshare.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import br.dagostini.jshare.comum.pojos.Arquivo;


public interface IServer extends Remote {

	public static final String NOME = "ChatRMI";
	
	public void registrarCliente(Cliente c) throws RemoteException;
	
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista)
			throws RemoteException;
	
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome)
			throws RemoteException;
	
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException;
	
	public void desconectar(Cliente c) throws RemoteException;

}
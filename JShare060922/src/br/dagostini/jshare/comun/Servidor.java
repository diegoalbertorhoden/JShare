package br.dagostini.jshare.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import br.dagostini.jshare.comum.pojos.Arquivo;

/**
 * Interface implementada pelo servidor. Esses métodos são chamados nos servidor
 * e executados no cliente.
 * 
 * AVISO: NÃO PREOCUPAÇÃO ALGUMA COM A SEGURANÇA AQUI. ESSE É SÓ UM EXEMPLO DE
 * RMI.
 * 
 * @author Fernando D'Agostini
 *
 */
public interface Servidor extends Remote {

	public static final String NOME = "ChatRMI";

	/**
	 * Chamado quando um novo cliente notifica sua entrada no chat do servidor.
	 * 
	 * @param nome
	 *            Nome do cliente, apelido.
	 * 
	 * @param cliente
	 *            Objeto remoto do cliente para que o servidor chame os métodos
	 *            do cliente. Ver {@link Cliente}
	 * 
	 * @throws RemoteException
	 */
	public void registrarCliente(Cliente c) throws RemoteException;

	/**
	 * Recebe a lista de arquivos disponíveis no cliente.
	 * 
	 * @param c
	 * @param lista
	 * @throws RemoteException
	 */
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista)
			throws RemoteException;

	/**
	 * Usado quando um cliente deseja procurar um arquivo pelo nome, o
	 * servidor lê todos os arquivos publicados e retorna uma mapa contendo
	 * os resultados em cada cliente.
	 * 
	 * @param nome
	 * @return
	 * @throws RemoteException
	 */
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome)
			throws RemoteException;

	/**
	 * Recebe informações do arquivo e retorna o arquivo em formato
	 * de array de bytes. 
	 * 
	 * @param arq
	 * @return
	 * @throws RemoteException
	 */
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException;

	/**
	 * Desconecta o cliente, tornando também indisponível seus arquivos
	 * para as buscas. 
	 * 
	 * @param c
	 * @throws RemoteException
	 */
	public void desconectar(Cliente c) throws RemoteException;

}

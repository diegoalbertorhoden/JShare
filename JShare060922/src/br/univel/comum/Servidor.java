package br.univel.comum;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
	public void entrarNoChat(String nome, Cliente cliente) throws RemoteException;

	/**
	 * Enviar mensagem para outro participante. Esse envio é intermediado pelo
	 * servidor, ou seja, o cliente1 envia para o servidor com destino cliente2,
	 * então o servidor envia para o cliente2.
	 * 
	 * @param remetente
	 *            Nome do remetente.
	 * 
	 * @param destinatario
	 *            Nome do destinatário.
	 * 
	 * @param mensagem
	 *            Texto da mensagem.
	 * 
	 * @throws RemoteException
	 */
	public void enviarMensagem(String remetente, String destinatario, String mensagem) throws RemoteException;

	/**
	 * Enviar mensagem para todos os outros participantes. Esse envio é
	 * intermediado pelo servidor, ou seja, o cliente1 envia para o servidor
	 * então o servidor envia para todos os outros clientes.
	 * 
	 * @param remetente
	 *            Nome do remetente.
	 * 
	 * @param mensagem
	 *            Texto da mensagem.
	 * 
	 * @throws RemoteException
	 */
	public void enviarMensagemPublica(String remetente, String mensagem) throws RemoteException;

	/**
	 * Chamado pelo cliente quando ele deseja deixar o chat. O servidor nesse
	 * momento notifica todos os outros participantes da saída desse cliente.
	 * 
	 * @param nome
	 *            Nome do cliente.
	 * 
	 * @throws RemoteException
	 */
	public void sair(String nome) throws RemoteException;

}

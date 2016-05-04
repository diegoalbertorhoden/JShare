package br.univel.diversos;
//link que encontrei sobre o assunto:
//http://respostas.guj.com.br/15450-como-capturar-ips-da-rede-e-obter-cominicacao-com-outros-computadores
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class LerIp {

	public List<String> LerIp() {
		//basicamente cria uma lista para armazenamento
		List<String> adicionaLista = new ArrayList<String>();
		try{
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			//enquanto tiver mais elementos manda ver...
			while(interfaces.hasMoreElements()){
				NetworkInterface ifc = interfaces.nextElement();
				if(ifc.isUp()){
					Enumeration<InetAddress> enderecos = ifc.getInetAddresses();
					while(enderecos.hasMoreElements()){
						InetAddress adicionar = enderecos.nextElement();
						//get host, pegue o host...
						String ip = adicionar.getHostAddress();
						//verificação de padrão
						if(ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")){
							//por fim adiciona o ip na lista
							adicionaLista.add(ip);
						}
					}
				}
			}
		}catch(SocketException e){
			e.printStackTrace();
		}
		return adicionaLista;
	}
}

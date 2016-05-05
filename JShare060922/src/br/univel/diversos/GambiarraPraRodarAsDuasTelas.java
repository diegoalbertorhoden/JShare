package br.univel.diversos;

import java.awt.EventQueue;

import br.univel.telas.TelaCliente;
import br.univel.telas.TelaServidor;

public class GambiarraPraRodarAsDuasTelas {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					TelaServidor ts = new TelaServidor();
					ts.setVisible(true);
					ts.configurar();

					TelaCliente tc = new TelaCliente();
					tc.setVisible(true);
					tc.configurar();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}
}
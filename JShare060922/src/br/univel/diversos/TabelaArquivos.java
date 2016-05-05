package br.univel.diversos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;

public class TabelaArquivos extends AbstractTableModel {

	private static final long serialVersionUID = 2220593048149495985L;

	//aqui seguimos mais ou menos o exemplo realizado em sala...
	Map<Cliente,List<Arquivo>> listandoNaTabela = new HashMap<>();

	private Object [][] matriz;
	private int linhas;

	public TabelaArquivos(Map<Cliente, List<Arquivo>> lista){
		this.listandoNaTabela = lista;

		linhas = 0;

		for(Entry<Cliente, List<Arquivo>> e: listandoNaTabela.entrySet()){

			linhas+= e.getValue().size();

		}
		matriz = new Object[linhas][5];

		int linha = 0;
		for(Entry<Cliente, List<Arquivo>> e : listandoNaTabela.entrySet()){
			for(Arquivo arq : e.getValue()){

				matriz[linha][0] = e.getKey().getNome();
				matriz[linha][1] = e.getKey().getIp();
				matriz[linha][2] = e.getKey().getPorta();
				matriz[linha][3] = arq.getNome();
				matriz[linha][4] = arq.getTamanho();


			}
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return linhas;
	}



	@Override
	public String getColumnName(int column) {

		switch(column){
		case 0:
			return "Nome do cliente";
		case 1:
			return "Ip do cliente";
		case 2:
			return "Porta do cliente";
		case 3:
			return "Nome do Arquivo";
		case 4:
			return "tamanho do arquivo";
		default:
			return "";

		}
	}

	@Override
	public Object getValueAt(int row, int col) {

		switch(col){
		case 0: 
			return matriz[row][0];
		case 1: 
			return matriz[row][1];
		case 2: 
			return matriz[row][2];
		case 3: 
			return matriz[row][3];
		case 4: 
			return matriz[row][4];
		default:
			return "";
		}

	}

}

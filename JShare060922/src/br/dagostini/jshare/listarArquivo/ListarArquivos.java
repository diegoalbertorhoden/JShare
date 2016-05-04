package br.dagostini.jshare.listarArquivo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.dagostini.jshare.comum.pojos.Arquivo;

//conforme passado em sala, criando lista de arquivos(modo mais simples)
public class ListarArquivos {

	//metodo para listar
	public List<Arquivo> listarArquivo(){
		File inicioDiretorio = new File(".\\Upados");
		List<Arquivo> listaArquivos = new ArrayList<>();
		
		for(File file : inicioDiretorio.listFiles()){
			
// verificaçãozinha básica se o arquivo é mesmo um arquivo
			//(pois ele pode ser um diretório)
			
			if(file.isFile()){
				Arquivo arq= new Arquivo();
				arq.setNome(file.getName());
				arq.setTamanho(file.length());
				listaArquivos.add(arq);
			}
		}
		return listaArquivos;
	}
	
}

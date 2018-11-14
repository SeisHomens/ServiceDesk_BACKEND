package br.senai.sp.info.service.core;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.senai.sp.info.service.models.Usuario;

@Component
public class LocalStorage {
	
	@Autowired
	private ServletContext context;
	

	public String getCaminhoAbsoluto() {
		return getCaminhoAbsoluto("");
	}
	
	public File getArquivo(String caminho) {
		return new File(getCaminhoAbsoluto(caminho));
	}
	
	public String getCaminhoRelativo(String caminho) {
		return context.getContextPath() + caminho;
	}
	
	public String getCaminhoAbsoluto(String caminho) {
		return context.getRealPath(caminho);
	}
	
	public void armazenar(String caminhoRelativoPasta, String nomeDoArquivo, byte[] bytes) throws IOException {
		//Armazena os caminhos
		String caminhoAbsolutoPasta = getCaminhoAbsoluto(caminhoRelativoPasta);
		String caminhoAbsolutoArquivo = caminhoAbsolutoPasta + "/" + nomeDoArquivo;
		
		System.out.println(caminhoAbsolutoArquivo);
		
		//Cria o objeto pasta, caso a pasta não exista, cria uma nova
		File pasta = new File(caminhoAbsolutoPasta);
		if(!pasta.exists()) {
			pasta.mkdirs();
		}
		
		//Cria o objeto arquivo, caso o arquivo não exista, cria um novo
		File arquivo = new File(caminhoAbsolutoArquivo);
		if(!arquivo.exists()) {
			arquivo.delete();
		}else {
			arquivo.createNewFile();
		}
			
		//Escreve o conteúdo do stream no arquivo
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(arquivo));
		os.write(bytes);
		os.close();
	}
	
	public void aplicarCaminhoFotoEmUsuarios(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			aplicarCaminhoFotoEmUsuario(usuario);
		}
	}
	
	public void aplicarCaminhoFotoEmUsuario(Usuario usuario) {
		//Verifica se existe uma foto no servidor com o id do usuário
		File arquivoFoto = this.getArquivo("/resources/fotos/" + usuario.getId());
		System.out.println(arquivoFoto.getAbsolutePath());
		if(arquivoFoto.exists()) {
			usuario.setCaminhoFoto(this.getCaminhoRelativo("/resources/fotos/" + usuario.getId()));
		}else {
			usuario.setCaminhoFoto(this.getCaminhoRelativo("/assets/images/user.png"));
		}
	}

}

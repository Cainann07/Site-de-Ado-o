package model;

public class JavaBeans {
	private String id_pessoa;
	private String email;
	private String senha;
	private String nome_pessoa;
	private String telefone;
	
	//private String id_endereco;
	
	public JavaBeans() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JavaBeans(String id_pessoa, String email, String senha, String nome_pessoa, String telefone) {
		super();
		this.id_pessoa = id_pessoa;
		this.email = email;
		this.senha = senha;
		this.nome_pessoa = nome_pessoa;
		this.telefone = telefone;
	}

	public String getId_pessoa() {
		return id_pessoa;
	}
	public void setId_pessoa(String id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome_pessoa() {
		return nome_pessoa;
	}
	public void setNome_pessoa(String nome_pessoa) {
		this.nome_pessoa = nome_pessoa;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {

	/** Módulo de conexão **/
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/pet2?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "1234";

	// Método de conexão
	public Connection conectar() {
		Connection con = null; // Esse objeto será usado para estabelecer uma sessão com o banco de dados.
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password); // Para estabelecer a conexão com o banco de dados.
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void inserirUsuario(JavaBeans usuario) {
		String create = "insert into cadastro_pessoa(email, senha, nome_pessoa, telefone) values (?, ?, ?, ?)";
		try {
			// abrir a conexão com o banco.
			Connection con = conectar();
			// Preparar a query para execução no banco de dados.
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, usuario.getEmail());
			pst.setString(2, usuario.getSenha());
			pst.setString(3, usuario.getNome_pessoa());
			pst.setString(4, usuario.getTelefone());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexão com o banco.
			con.close();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}

	/** CRUD READ **/

	public ArrayList<JavaBeans> validacaoLogin() {
		// Criando objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> dados_usuario = new ArrayList<>();
		String read = "select * from cadastro_pessoa order by id_pessoa";
		try {
			// abrir a conexão com o banco.
			Connection con = conectar();
			// Preparar a query para execução no banco de dados.
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// o laço abaixo será executado enquando houve contatos
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String id_pessoa = rs.getString(1);
				String email = rs.getString(2);
				String senha = rs.getString(3);
				String nome_pessoa = rs.getString(4);
				String telefone = rs.getString(5);
				// populando o ArrayList
				dados_usuario.add(new JavaBeans(id_pessoa, email, senha, nome_pessoa, telefone));
				
			}
			// Encerrar a conexão com o banco.
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return dados_usuario;
	}

}

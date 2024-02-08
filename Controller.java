package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/login", "/main", "/insert" })
public class Controller extends HttpServlet { // A classe principal do servlet.
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans usuario = new JavaBeans();

	public Controller() {
		super();

	}

	//Metodo de decisão e direcionamento para prosseguimento seguinte
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath(); // Armazena o caminho da requisição.
		System.out.println(action);
		if (action.equals("/login")) {
			System.out.println("Entrou");
			validacaoLogin(request, response);
		} else if (action.equals("/insert")) {
			novoUsuario(request, response);
		}
	}

	//Metodo para validar login do Usuario
	protected void validacaoLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Varioaveis utilizadas para validar login
		String emailDigitado = request.getParameter("email");
		String senhaDigitada = request.getParameter("senha");
		String emailValidado = null;
		String senhaValidada = null;
		// Criando um objeto para receber os dados javaBeans
		ArrayList<JavaBeans> lista = dao.validacaoLogin(); // Array que irá armazenar os cadastros.
		// Retirada de itens da lista para comparação
		for (int i = 0; i < lista.size(); i++) { // Estrutura de repetição que irá percorrer todo o banco de dados até encontrar os dados que o usuário digitou(email e senha).
			// Teste de recebimento de dados
//					System.out.println(lista.get(i).getId_usuario());
//					System.out.println(lista.get(i).getEmail_usuario());
//					System.out.println(lista.get(i).getSenha_usuario());
			if (emailDigitado.equals(lista.get(i).getEmail())
					&& senhaDigitada.equals(lista.get(i).getSenha())) {
				emailValidado = lista.get(i).getEmail();
				senhaValidada = lista.get(i).getSenha();
			}

		}
		// Redirecionamento para pagina devida
		if (emailDigitado.equals(emailValidado) && senhaDigitada.equals(senhaValidada)) {
			response.sendRedirect("pagina_pos_login.html");
		} else {
			response.sendRedirect("falha_login.html");
		}
	}

	protected void novoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento dos dados do formulário.
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("senha"));
		// Setar as variáveis JavaBeans.
		usuario.setNome_pessoa(request.getParameter("nome"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setTelefone(request.getParameter("telefone"));
		// Invocar o método inserirUsuario passando o objeto 'usuario'.
		dao.inserirUsuario(usuario);
		// Redirecionar para o documento login.html
		response.sendRedirect("login.html");
	}
}
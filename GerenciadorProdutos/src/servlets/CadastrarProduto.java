package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Produto;
import service.ProdutoService;

/**
 * Servlet implementation class CadastrarProduto
 */
@WebServlet(name = "CadastrarProduto.do", urlPatterns = { "/CadastrarProduto.do" })
public class CadastrarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter saida = response.getWriter();

		String nome = request.getParameter("nome_pro");
		String descricao = request.getParameter("descricao_pro");
		String parameterCodigo = request.getParameter("codigo_pro");
		String parameterValor = request.getParameter("valor_pro");
		String parameterEstoque = request.getParameter("estoque_pro");
		int codigo;
		double valor;
		int estoque;

		if (nome == "" || nome == null || nome.length() <= 0 || nome.isEmpty()) {
			saida.println("<h1>Volte e informe o nome do produto!</h1>");
			saida.println("<br>");
			saida.println("<a href='alteracao.html'>Voltar</a>");
			return;
		}

		if (descricao == "" || descricao == null || descricao.length() <= 0 || descricao.isEmpty()) {
			saida.println("<h1>Volte e informe a descrição do produto!</h1>");
			saida.println("<br>");
			saida.println("<a href='alteracao.html'>Voltar</a>");
			return;
		}

		if (parameterCodigo == "" || parameterCodigo == null || parameterCodigo.length() <= 0
				|| parameterCodigo.isEmpty()) {
			saida.println("<h1>Volte e informe o código do produto!</h1>");
			saida.println("<br>");
			saida.println("<a href='alteracao.html'>Voltar</a>");
			return;
		} else {
			codigo = Integer.parseInt(parameterCodigo);
		}

		if (parameterValor == "" || parameterValor == null || parameterValor.length() <= 0
				|| parameterValor.isEmpty()) {
			saida.println("<h1>Volte e informe o valor do produto!</h1>");
			saida.println("<br>");
			saida.println("<a href='alteracao.html'>Voltar</a>");
			return;
		} else {
			valor = Double.parseDouble(parameterValor.replace(',', '.'));
		}

		if (parameterEstoque == "" || parameterEstoque == null || parameterEstoque.length() <= 0
				|| parameterEstoque.isEmpty()) {
			saida.println("<h1>Volte e informe o estoque do produto!</h1>");
			saida.println("<br>");
			saida.println("<a href='alteracao.html'>Voltar</a>");
			return;
		} else {
			estoque = Integer.parseInt(parameterEstoque);
		}

		Produto produto = new Produto();
		produto.setCodigo(codigo);
		produto.setDescricao(descricao);
		produto.setEstoque(estoque);
		produto.setNome(nome);
		produto.setValor(valor);

		ProdutoService produtoService = new ProdutoService();
		Produto existe = produtoService.consultar(codigo);

		if (existe.isValid()) {
			saida.println("<h1>Este código de produto já está em uso!</h1>");
		} else {
			produtoService.cadastrar(produto);
			saida.println("<h1>Produto cadastrado com sucesso!</h1>");
		}

		saida.println("<a href=\"cadastro.html\">Cadastrar produto</a>");
		saida.println("<br>");
		saida.println("<br>");
		saida.println("<a href=\"alteracao.html\">Alterar produto</a>");
		saida.println("<br>");
		saida.println("<br>");
		saida.println("<a href=\"excluir.html\">Excluir produto</a>");
		saida.println("<br>");
		saida.println("<br>");
		saida.println("<a href=\"consultar.html\">Consultar produto</a>");
		saida.println("<br>");
		saida.println("<br>");
		saida.println("<a href=\"ListarProdutos.do\">Listar produtos</a>");
	}

}

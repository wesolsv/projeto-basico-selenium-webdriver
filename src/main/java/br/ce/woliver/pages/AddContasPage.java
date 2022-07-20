package br.ce.woliver.pages;

import br.ce.woliver.core.BasePage;

public class AddContasPage extends BasePage {
	
	private MenuPage menuPage = new MenuPage();
	
	public void inserirConta(String nomeConta) {
		menuPage.acessarPaginaAdicionar();
		escrever("nome", nomeConta);
		clicarComXpath("//button[.='Salvar']");
	}
	
	public void alterarConta(String nomeAntigo, String novoNome) {
		menuPage.acessarPaginaListar();
		clicarComXpath("//tr[td='"+nomeAntigo+"']//span[@class='glyphicon glyphicon-edit']");
		escrever("nome", novoNome);
		clicarComXpath("//button[.='Salvar']");
	}
	
	public void removerConta(String nomeConta) {
		menuPage.acessarPaginaListar();
		System.out.println("//tr[td='"+nomeConta+"']//span[@class='glyphicon glyphicon-remove-circle']");
		clicarComXpath("//tr[td='"+nomeConta+"']//span[@class='glyphicon glyphicon-remove-circle']");
	}
}	

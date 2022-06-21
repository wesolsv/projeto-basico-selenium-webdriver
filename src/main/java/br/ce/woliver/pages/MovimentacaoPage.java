package br.ce.woliver.pages;

import br.ce.woliver.core.BasePage;

public class MovimentacaoPage extends BasePage {
	
	public void acessarPaginaMovimentacao() {
		clicarComXpath("//a[.='Criar Movimentação']");
	}
	
	public void acessarPaginaListar() {
		clicarComXpath("//a[@data-toggle='dropdown' and @role='button']");
		clicarComXpath("//a[.='Listar']");
	}
	
	public void inserirConta(String nomeConta) {
		escrever("nome", nomeConta);
		clicarComXpath("//button[.='Salvar']");
	}
	
	public void alterarConta(String novoNome) {
		acessarPaginaListar();
		clicarComXpath("//tr[td='Conta Inserida']//a[1]");
		inserirConta(novoNome);
	}
	
	public void removerConta(String nomeConta) {
		acessarPaginaListar();
		clicarComXpath("//tr[td='" + nomeConta + "']//a[2]");
	}
}	

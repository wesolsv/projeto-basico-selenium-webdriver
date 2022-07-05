package br.ce.woliver.pages;

import br.ce.woliver.core.BasePage;

public class MenuPage extends BasePage {

	public void acessarPaginaAdicionar() {
		clicarLink("Contas ");
		clicarLink("Adicionar");
	}
	
	public void acessarPaginaListar() {
		clicarLink("Contas ");
		clicarLink("Listar");
	}
	
	public void acessarPaginaMovimentacao() {
		clicarLink("Criar Movimentação");
	}
	
	public void acessarPaginaResumo() {
		clicarLink("Resumo Mensal");
	}
}

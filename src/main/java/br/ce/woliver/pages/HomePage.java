package br.ce.woliver.pages;

import br.ce.woliver.core.BasePage;

public class HomePage extends BasePage {
	private MovimentacaoPage movPage = new MovimentacaoPage();
	private AddContasPage pageConta = new AddContasPage();
	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();

	public void inserirConta() {
		menuPage.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Movimenta��o");
	}

	public void inserirMovimento() {
		int valor = 1000;

		for (int i = 0; i <= 2; i++) {
			menuPage.acessarPaginaMovimentacao();
			movPage.criarMovimento("REC", obterData(), obterData(), "desc al", "abcde",
					Integer.toString(valor + i), "Conta Movimenta��o", "status_pago");
		}
	}
	
	public void apagarContaEMovimento() {
		for (int i = 0; i <= 2; i++) {
			menuPage.acessarPaginaResumo();
			resumoPage.excluirMovimentacao("desc al", "Conta Movimenta��o");
		}
		pageConta.removerConta("Conta Movimenta��o");
	}

}

package br.ce.woliver.tests;

import org.junit.Assert;
import org.junit.Test;

import br.ce.woliver.core.BaseTest;
import br.ce.woliver.core.DriverFactory;
import br.ce.woliver.pages.AddContasPage;
import br.ce.woliver.pages.MenuPage;
import br.ce.woliver.pages.MovimentacaoPage;
import br.ce.woliver.pages.ResumoPage;

public class ResumoTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();
	private AddContasPage pageConta = new AddContasPage();
	private MovimentacaoPage movPage = new MovimentacaoPage();

	@Test
	public void remover_conta_com_movimentacao() {
		menuPage.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Movimentação");
		menuPage.acessarPaginaMovimentacao();
		movPage.criarMovimento("DESP", resumoPage.obterData(),  resumoPage.obterData(), 
				"aleatoria", "Pessoa", "1000", "Conta Movimentação", "status_pago");
		pageConta.removerConta("Conta Movimentação");
		Assert.assertEquals(movPage.mensagemSucesso(), "Conta em uso na movimentações");
		menuPage.acessarPaginaResumo();
		resumoPage.excluirMovimentacao("aleatoria", "Conta Movimentação");
		pageConta.removerConta("Conta Movimentação");
	}

	@Test
	public void remover_movimentacao() {
		menuPage.acessarPaginaAdicionar();
		pageConta.inserirConta("account alexa");
		menuPage.acessarPaginaMovimentacao();
		movPage.criarMovimento("DESP", resumoPage.obterData(),  resumoPage.obterData(), 
				"alexa", "Pessoa", "1000", "account alexa", "status_pago");
		menuPage.acessarPaginaResumo();
		resumoPage.excluirMovimentacao("alexa", "account alexa");
		Assert.assertEquals(movPage.mensagemSucesso(), "Movimentação removida com sucesso!");
		pageConta.removerConta("account alexa");
	}
	
	@Test
	public void validar_tela_resumo() {
		menuPage.acessarPaginaResumo();
		Assert.assertEquals(movPage.returnUrl(), "https://seubarriga.wcaquino.me/extrato");
		Assert.assertEquals("Seu Barriga - Extrato", DriverFactory.getDriver().getTitle());
	}
}

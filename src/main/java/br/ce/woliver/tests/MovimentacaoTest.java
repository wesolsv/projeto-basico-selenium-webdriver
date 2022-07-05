package br.ce.woliver.tests;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.ce.woliver.core.BaseTest;
import br.ce.woliver.pages.AddContasPage;
import br.ce.woliver.pages.MenuPage;
import br.ce.woliver.pages.MovimentacaoPage;
import br.ce.woliver.pages.ResumoPage;

public class MovimentacaoTest extends BaseTest {
	
	private MovimentacaoPage movPage = new MovimentacaoPage();
	private AddContasPage pageConta = new AddContasPage();
	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();
	
	@Test
	public void inserir_movimentacao() {
		menuPage.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Mov");
		menuPage.acessarPaginaMovimentacao();
		movPage.criarMovimento("REC", "05/07/2022", "20/07/2022", "desc movimento", 
				"Pessoa interessada", "1000", "Conta Mov", "status_pago");
		Assert.assertEquals(movPage.mensagemSucesso(), "Movimenta��o adicionada com sucesso!");
		menuPage.acessarPaginaResumo();
		resumoPage.excluirMovimentacao("desc movimento", "Conta Mov");
		pageConta.removerConta("Conta Mov");
	}
	
	@Test
	public void validar_campos_obrigatorios() {
		menuPage.acessarPaginaMovimentacao();
		movPage.salvarMovimento();
		List<String> erros = movPage.obterErros();
		
		Assert.assertTrue(erros.containsAll(Arrays.asList(
				"Data da Movimenta��o � obrigat�rio", "Data do pagamento � obrigat�rio",
				"Descri��o � obrigat�rio", "Interessado � obrigat�rio",
				"Valor � obrigat�rio", "Valor deve ser um n�mero"
				)));
		Assert.assertEquals(6, erros.size());
		}
	
	@Test
	public void validar_movimentacao_futura() {
		menuPage.acessarPaginaAdicionar();
		pageConta.inserirConta("Account");
		menuPage.acessarPaginaMovimentacao();
		movPage.criarMovimento("DESP", "01/01/2038", "01/01/2039", "desc movi", 
				"Pessoa", "1000", "Account", "status_pago");
		Assert.assertEquals(movPage.mensagemSucesso(), "Data da Movimenta��o deve ser menor ou igual � data atual");
		pageConta.removerConta("Account");
	}
	
	@Test
	public void validar_tela_resumo() {
		menuPage.acessarPaginaResumo();
		Assert.assertEquals(movPage.returnUrl(), "https://seubarriga.wcaquino.me/extrato");
	}
	
	@Test
	public void validar_saldo_conta() {
		menuPage.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Movimenta��o");
		int valor = 1000;
		for(int i = 0; i <= 2; i++) {
			menuPage.acessarPaginaMovimentacao();
			movPage.criarMovimento("REC", "05/07/2022", "20/07/2022", "desc al", 
					"abcde", Integer.toString(valor+i), "Conta Movimenta��o", "status_pago");
		}
		movPage.clicarComXpath("//li[.='Home']");
		Assert.assertEquals(movPage.pegarTextoXpath("//tr[td='Conta Movimenta��o']//td[2]"), "3003.00");
		for(int i = 0; i <= 2; i++) {
			menuPage.acessarPaginaResumo();
			resumoPage.excluirMovimentacao("desc al", "Conta Movimenta��o");
		}
		pageConta.removerConta("Conta Movimenta��o");
	}
}

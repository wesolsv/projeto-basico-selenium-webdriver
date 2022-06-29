package br.ce.woliver.tests;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.woliver.core.BaseTest;
import br.ce.woliver.pages.AddContasPage;
import br.ce.woliver.pages.MovimentacaoPage;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class MovimentacaoTest extends BaseTest {
	
	MovimentacaoPage page = new MovimentacaoPage();
	AddContasPage pageConta = new AddContasPage();
	
	@Test
	public void inserir_movimentacao() {
		pageConta.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Mov");
		page.acessarPaginaMovimentacao();
		page.criarMovimento("REC", "07/06/2022", "20/06/2022", "desc movimento", 
				"Pessoa interessada", "1000", "Conta Mov", "status_pago");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), 
				"Movimenta��o adicionada com sucesso!");
		page.acessarPaginaResumo();
		page.excluirMovimentacao("desc movimento", "Conta Mov");
		pageConta.removerConta("Conta Mov");
	}
	
	@Test
	public void validar_campos_obrigatorios() {
		page.acessarPaginaMovimentacao();
		page.clicarComXpath("//button[.='Salvar']");
		
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Data da Movimenta��o � obrigat�rio']")), "Data da Movimenta��o � obrigat�rio");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Data do pagamento � obrigat�rio']")), "Data do pagamento � obrigat�rio");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Descri��o � obrigat�rio']")), "Descri��o � obrigat�rio");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Interessado � obrigat�rio']")), "Interessado � obrigat�rio");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Valor � obrigat�rio']")), "Valor � obrigat�rio");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Valor deve ser um n�mero']")), "Valor deve ser um n�mero");
	}
	
	@Test
	public void validar_movimentacao_futura() {
		pageConta.acessarPaginaAdicionar();
		pageConta.inserirConta("Account");
		page.acessarPaginaMovimentacao();
		page.criarMovimento("DESP", "01/01/2028", "01/01/2029", "desc movi", 
				"Pessoa", "1000", "Account", "status_pago");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), 
				"Data da Movimenta��o deve ser menor ou igual � data atual");
		pageConta.removerConta("Account");
	}
	
	@Test
	public void remover_conta_com_movimentacao() {
		pageConta.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Movimenta��o");
		page.acessarPaginaMovimentacao();
		page.criarMovimento("DESP", "07/06/2022", "20/06/2022", "aleatoria", 
				"Pessoa", "1000", "Conta Movimenta��o", "status_pago");
		pageConta.removerConta("Conta Movimenta��o");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), "Conta em uso na movimenta��es");
		page.acessarPaginaResumo();
		page.excluirMovimentacao("aleatoria", "Conta Movimenta��o");
		pageConta.removerConta("Conta Movimenta��o");
	}
	
	@Test
	public void remover_movimentacao() {
		pageConta.acessarPaginaAdicionar();
		pageConta.inserirConta("account alexa");
		page.acessarPaginaMovimentacao();
		page.criarMovimento("DESP", "07/06/2022", "20/06/2022", "alexa", 
				"Pessoa", "1000", "account alexa", "status_pago");
		page.acessarPaginaResumo();
		page.excluirMovimentacao("alexa", "account alexa");
		pageConta.removerConta("account alexa");
	}
	
	@Test
	public void validar_tela_resumo() {
		page.acessarPaginaResumo();
		Assert.assertEquals(page.returnUrl(), "https://seubarriga.wcaquino.me/extrato");
	}
	
	@Test
	public void validar_saldo_conta() {
		pageConta.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Movimenta��o");
		int valor = 1000;
		for(int i = 0; i <= 2; i++) {
			page.acessarPaginaMovimentacao();
			page.criarMovimento("REC", "07/06/2022", "20/06/2022", "desc al", 
					"abcde", Integer.toString(valor+i), "Conta Movimenta��o", "status_pago");
		}
		page.clicarComXpath("//li[.='Home']");
		Assert.assertEquals(page.pegarTextoXpath("//tr[td='Conta Movimenta��o']//td[2]"), "3003.00");
		for(int i = 0; i <= 2; i++) {
			page.acessarPaginaResumo();
			page.excluirMovimentacao("desc al", "Conta Movimenta��o");
		}
		pageConta.removerConta("Conta Movimenta��o");
	}
}

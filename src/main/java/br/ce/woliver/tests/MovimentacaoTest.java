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
				"Movimentação adicionada com sucesso!");
		page.acessarPaginaResumo();
		page.excluirMovimentacao("desc movimento", "Conta Mov");
		pageConta.removerConta("Conta Mov");
	}
	
	@Test
	public void validar_campos_obrigatorios() {
		page.acessarPaginaMovimentacao();
		page.clicarComXpath("//button[.='Salvar']");
		
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Data da Movimentação é obrigatório']")), "Data da Movimentação é obrigatório");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Data do pagamento é obrigatório']")), "Data do pagamento é obrigatório");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Descrição é obrigatório']")), "Descrição é obrigatório");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Interessado é obrigatório']")), "Interessado é obrigatório");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Valor é obrigatório']")), "Valor é obrigatório");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']//li[.='Valor deve ser um número']")), "Valor deve ser um número");
	}
	
	@Test
	public void validar_movimentacao_futura() {
		pageConta.acessarPaginaAdicionar();
		pageConta.inserirConta("Account");
		page.acessarPaginaMovimentacao();
		page.criarMovimento("DESP", "01/01/2028", "01/01/2029", "desc movi", 
				"Pessoa", "1000", "Account", "status_pago");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), 
				"Data da Movimentação deve ser menor ou igual à data atual");
		pageConta.removerConta("Account");
	}
	
	@Test
	public void remover_conta_com_movimentacao() {
		pageConta.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Movimentação");
		page.acessarPaginaMovimentacao();
		page.criarMovimento("DESP", "07/06/2022", "20/06/2022", "aleatoria", 
				"Pessoa", "1000", "Conta Movimentação", "status_pago");
		pageConta.removerConta("Conta Movimentação");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), "Conta em uso na movimentações");
		page.acessarPaginaResumo();
		page.excluirMovimentacao("aleatoria", "Conta Movimentação");
		pageConta.removerConta("Conta Movimentação");
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
		pageConta.inserirConta("Conta Movimentação");
		int valor = 1000;
		for(int i = 0; i <= 2; i++) {
			page.acessarPaginaMovimentacao();
			page.criarMovimento("REC", "07/06/2022", "20/06/2022", "desc al", 
					"abcde", Integer.toString(valor+i), "Conta Movimentação", "status_pago");
		}
		page.clicarComXpath("//li[.='Home']");
		Assert.assertEquals(page.pegarTextoXpath("//tr[td='Conta Movimentação']//td[2]"), "3003.00");
		for(int i = 0; i <= 2; i++) {
			page.acessarPaginaResumo();
			page.excluirMovimentacao("desc al", "Conta Movimentação");
		}
		pageConta.removerConta("Conta Movimentação");
	}
}

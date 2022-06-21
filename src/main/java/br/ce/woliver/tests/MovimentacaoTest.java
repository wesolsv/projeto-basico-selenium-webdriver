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
		pageConta.inserirConta("Conta Movimenta��o");
		page.acessarPaginaMovimentacao();
		page.pegarSelectComValue("tipo", "DESP");
		page.escrever("data_transacao", "07/06/2022");
		page.escrever("data_pagamento", "20/06/2022");
		page.escrever("descricao", "Descri��o primeiro movimento");
		page.escrever("interessado", "Pessoa interessada");
		page.escrever("valor", "1000");
		page.pegarSelectComText("conta", "Conta Movimenta��o");
		page.clicarElemento("status_pendente");
		page.clicarComXpath("//button[.='Salvar']");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), "Movimenta��o adicionada com sucesso!");
		pageConta.removerConta("Conta Movimenta��o");
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
		pageConta.inserirConta("Conta Movimenta��o");
		page.acessarPaginaMovimentacao();
		page.pegarSelectComValue("tipo", "DESP");
		page.escrever("data_transacao", "01/01/2028");
		page.escrever("data_pagamento", "01/01/2029");
		page.escrever("descricao", "Descri��o primeiro movimento");
		page.escrever("interessado", "Pessoa interessada");
		page.escrever("valor", "1000");
		page.pegarSelectComText("conta", "Conta Movimenta��o");
		page.clicarElemento("status_pago");
		page.clicarComXpath("//button[.='Salvar']");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), "Data da Movimenta��o deve ser menor ou igual � data atual");
		pageConta.removerConta("Conta Movimenta��o");
	}
	
	//N�O TA PRONTO
	@Test
	public void remover_movimentacao() {
		pageConta.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Movimenta��o");
		page.acessarPaginaMovimentacao();
		page.pegarSelectComValue("tipo", "DESP");
		page.escrever("data_transacao", "07/06/2022");
		page.escrever("data_pagamento", "20/06/2022");
		page.escrever("descricao", "Descri��o primeiro movimento");
		page.escrever("interessado", "Pessoa interessada");
		page.escrever("valor", "1000");
		page.pegarSelectComText("conta", "Conta Movimenta��o");
		page.clicarElemento("status_pago");
		page.clicarComXpath("//button[.='Salvar']");
		pageConta.removerConta("Conta Movimenta��o");
	}
}

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
		pageConta.inserirConta("Conta Movimentação");
		page.acessarPaginaMovimentacao();
		page.pegarSelectComValue("tipo", "REC");
		page.escrever("data_transacao", "07/06/2022");
		page.escrever("data_pagamento", "20/06/2022");
		page.escrever("descricao", "Descrição primeiro movimento");
		page.escrever("interessado", "Pessoa interessada");
		page.escrever("valor", "1000");
		page.pegarSelectComText("conta", "Conta Movimentação");
		page.clicarElemento("status_pago");
		page.clicarComXpath("//button[.='Salvar']");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), "Movimentação adicionada com sucesso!");
		pageConta.removerConta("Conta Movimentação");
		page.acessarPaginaResumo();
		page.excluirMovimentacao("Descrição primeiro movimento", "Conta Movimentação");
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
		pageConta.inserirConta("Conta Movimentação");
		page.acessarPaginaMovimentacao();
		page.pegarSelectComValue("tipo", "DESP");
		page.escrever("data_transacao", "01/01/2028");
		page.escrever("data_pagamento", "01/01/2029");
		page.escrever("descricao", "Descrição primeiro movimento");
		page.escrever("interessado", "Pessoa interessada");
		page.escrever("valor", "1000");
		page.pegarSelectComText("conta", "Conta Movimentação");
		page.clicarElemento("status_pago");
		page.clicarComXpath("//button[.='Salvar']");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), "Data da Movimentação deve ser menor ou igual à data atual");
		pageConta.removerConta("Conta Movimentação");
	}
	
	@Test
	public void remover_conta_com_movimentacao() {
		pageConta.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Movimentação");
		page.acessarPaginaMovimentacao();
		page.pegarSelectComValue("tipo", "DESP");
		page.escrever("data_transacao", "07/06/2022");
		page.escrever("data_pagamento", "20/06/2022");
		page.escrever("descricao", "Descrição aleatoria movimento");
		page.escrever("interessado", "Pessoa interessada");
		page.escrever("valor", "1000");
		page.pegarSelectComText("conta", "Conta Movimentação");
		page.clicarElemento("status_pago");
		page.clicarComXpath("//button[.='Salvar']");
		pageConta.removerConta("Conta Movimentação");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), "Conta em uso na movimentações");
		page.acessarPaginaResumo();
		page.excluirMovimentacao("Descrição aleatoria movimento", "Conta Movimentação");
		pageConta.removerConta("Conta Movimentação");
	}
	
	@Test
	public void remover_movimentacao() {
		pageConta.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Movimentação");
		page.acessarPaginaMovimentacao();
		page.pegarSelectComValue("tipo", "DESP");
		page.escrever("data_transacao", "07/06/2022");
		page.escrever("data_pagamento", "20/06/2022");
		page.escrever("descricao", "Descrição movimento");
		page.escrever("interessado", "Pessoa interessada");
		page.escrever("valor", "1000");
		page.pegarSelectComText("conta", "Conta Movimentação");
		page.clicarElemento("status_pago");
		page.clicarComXpath("//button[.='Salvar']");
		page.acessarPaginaResumo();
		page.excluirMovimentacao("Descrição movimento", "Conta Movimentação");
	}
	
	@Test
	public void validar_tela_resumo() {
		page.acessarPaginaResumo();
		Assert.assertEquals(page.pegarValueXpath("//input[@type='submit']"), "Buscar");
	}
	
	@Test
	public void validar_saldo_conta() {
		pageConta.acessarPaginaAdicionar();
		pageConta.inserirConta("Conta Movimentação");
		int valor = 1000;
		for(int i = 0; i <= 2; i++) {
			page.acessarPaginaMovimentacao();
			page.pegarSelectComValue("tipo", "REC");
			page.escrever("data_transacao", "07/06/2022");
			page.escrever("data_pagamento", "20/06/2022");
			page.escrever("descricao", "Descrição primeiro movimento");
			page.escrever("interessado", "Pessoa interessada");
			page.escrever("valor", Integer.toString(valor+i));
			page.pegarSelectComText("conta", "Conta Movimentação");
			page.clicarElemento("status_pago");
			page.clicarComXpath("//button[.='Salvar']");
		}
		page.clicarComXpath("//li[.='Home']");
		Assert.assertEquals(page.pegarTextoXpath("//tr[td='Conta Movimentação']//td[2]"), "3003.00");
		for(int i = 0; i <= 2; i++) {
			page.acessarPaginaResumo();
			page.excluirMovimentacao("Descrição primeiro movimento", "Conta Movimentação");
		}
		pageConta.removerConta("Conta Movimentação");
	}
}

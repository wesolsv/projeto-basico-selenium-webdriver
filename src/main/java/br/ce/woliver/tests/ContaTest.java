package br.ce.woliver.tests;

import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.woliver.core.BaseTest;
import br.ce.woliver.pages.AddContasPage;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ContaTest extends BaseTest {
	
	AddContasPage page = new AddContasPage();
	
	@Test
	public void inserir_conta() {
		page.acessarPaginaAdicionar();
		page.inserirConta("Conta Inserida");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), "Conta adicionada com sucesso!");
		page.removerConta("Conta Inserida");
	}
	
	@Test
	public void alterar_conta() {
		page.acessarPaginaAdicionar();
		page.inserirConta("Conta Inserida");
		page.alterarConta("Conta Alterada");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), "Conta alterada com sucesso!");
		page.removerConta("Conta Alterada");
	}
	
	@Test
	public void inserir_conta_com_mesmo_nome() {
		page.acessarPaginaAdicionar();
		page.inserirConta("Conta Inserida");
		page.acessarPaginaAdicionar();
		page.inserirConta("Conta Inserida");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), "Já existe uma conta com esse nome!");
		page.removerConta("Conta Inserida");
	}
	
	@Test
	public void remover_conta() {
		page.acessarPaginaAdicionar();
		page.inserirConta("Conta Inserida");
		page.removerConta("Conta Inserida");
		Assert.assertEquals(page.pegarTextoBy(By.xpath("//div[@role='alert']")), "Conta removida com sucesso!");
	}
}

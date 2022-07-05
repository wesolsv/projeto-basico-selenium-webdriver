package br.ce.woliver.tests;

import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;

import br.ce.woliver.core.BaseTest;
import br.ce.woliver.pages.AddContasPage;
import br.ce.woliver.pages.MenuPage;

@SuppressWarnings("deprecation")
public class ContaTest extends BaseTest {
	
	private MenuPage menuPage = new MenuPage();
	AddContasPage page = new AddContasPage();
	
	@Test
	public void inserir_conta() {
		menuPage.acessarPaginaAdicionar();
		page.inserirConta("Conta Inserida");
		Assert.assertEquals(page.mensagemSucesso(), "Conta adicionada com sucesso!");
		page.removerConta("Conta Inserida");
	}
	
	@Test
	public void alterar_conta() {
		menuPage.acessarPaginaAdicionar();
		page.inserirConta("Conta Inserida");
		menuPage.acessarPaginaListar();
		page.alterarConta("Conta Inserida", "Conta Alterada");
		Assert.assertEquals(page.mensagemSucesso(), "Conta alterada com sucesso!");
		page.removerConta("Conta Alterada");
	}
	
	@Test
	public void inserir_conta_com_mesmo_nome() {
		menuPage.acessarPaginaAdicionar();
		page.inserirConta("Conta Inserida");
		menuPage.acessarPaginaAdicionar();
		page.inserirConta("Conta Inserida");
		Assert.assertEquals(page.mensagemSucesso(), "Já existe uma conta com esse nome!");
		page.removerConta("Conta Inserida");
	}
	
	@Test
	public void remover_conta() {
		menuPage.acessarPaginaAdicionar();
		page.inserirConta("Conta Inserida");
		page.removerConta("Conta Inserida");
		Assert.assertEquals(page.mensagemSucesso(), "Conta removida com sucesso!");
	}
}

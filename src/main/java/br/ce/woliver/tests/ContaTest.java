package br.ce.woliver.tests;

import org.junit.Assert;
import org.junit.Test;

import br.ce.woliver.core.BaseTest;
import br.ce.woliver.pages.AddContasPage;

public class ContaTest extends BaseTest {
	
	private AddContasPage page = new AddContasPage();
	
	@Test
	public void inserir_conta() {
		page.inserirConta("Conta T Inserir");
		Assert.assertEquals(page.mensagemSucesso(), "Conta adicionada com sucesso!");
		page.removerConta("Conta T Inserir");
	}
	
	@Test
	public void alterar_conta() {
		page.inserirConta("Conta T Alterar");
		page.alterarConta("Conta T Alterar", "Conta Alterada");
		Assert.assertEquals(page.mensagemSucesso(), "Conta alterada com sucesso!");
		page.removerConta("Conta Alterada");
	}
	
	@Test
	public void inserir_conta_com_mesmo_nome() {
		page.inserirConta("Conta T Mesmo Nome");
		page.inserirConta("Conta T Mesmo Nome");
		Assert.assertEquals(page.mensagemSucesso(), "Já existe uma conta com esse nome!");
		page.removerConta("Conta T Mesmo Nome");
	}
	
	@Test
	public void remover_conta() {
		page.inserirConta("Conta T Remover");
		page.removerConta("Conta T Remover");
		Assert.assertEquals(page.mensagemSucesso(), "Conta removida com sucesso!");
	}
}

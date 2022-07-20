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
		pageConta.inserirConta("Conta T Inserir Mov");
		movPage.criarMovimento("REC", movPage.obterData(), movPage.obterData(), "desc movimento", 
				"Pessoa interessada", "1000", "Conta T Inserir Mov", "status_pago");
		Assert.assertEquals(movPage.mensagemSucesso(), "Movimenta��o adicionada com sucesso!");
		resumoPage.excluirMovimentacao("desc movimento", "Conta T Inserir Mov");
		pageConta.removerConta("Conta T Inserir Mov");
	}
	
	@Test
	public void validar_campos_obrigatorios() {
		menuPage.acessarPaginaMovimentacao();
		movPage.salvarMovimento();
		List<String> erros = movPage.obterErros();
		
		Assert.assertTrue(erros.containsAll(Arrays.asList(
				"Data da Movimenta��o � obrigat�rio", "Data do pagamento � obrigat�rio",
				"Descri��o � obrigat�rio", "Interessado � obrigat�rio",
				"Valor � obrigat�rio", "Valor deve ser um n�mero", "Conta � obrigat�rio"
				)));
		Assert.assertEquals(7, erros.size());
		}
	
	@Test
	public void validar_movimentacao_futura() {
		pageConta.inserirConta("Conta T Mov Futura");
		movPage.criarMovimento("DESP", "01/01/2038", "01/01/2039", "desc movi", 
				"Pessoa", "1000", "Conta T Mov Futura", "status_pago");
		Assert.assertEquals(movPage.mensagemSucesso(), "Data da Movimenta��o deve ser menor ou igual � data atual");
		pageConta.removerConta("Conta T Mov Futura");
	}
	
}

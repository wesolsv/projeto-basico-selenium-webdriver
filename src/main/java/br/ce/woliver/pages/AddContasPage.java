package br.ce.woliver.pages;

import org.openqa.selenium.By;
import static br.ce.woliver.core.DriverFactory.getDriver;
import br.ce.woliver.core.BasePage;

public class AddContasPage extends BasePage {
	
	private MenuPage menuPage = new MenuPage();
	
	public void inserirConta(String nomeConta) {
		escrever("nome", nomeConta);
		clicarComXpath("//button[.='Salvar']");
	}
	
	public void alterarConta(String nomeAntigo, String novoNome) {
		clicarComXpath("//tr[td='"+nomeAntigo+"']//span[@class='glyphicon glyphicon-edit']");
		inserirConta(novoNome);
	}
	
	public void removerConta(String nomeConta) {
		menuPage.acessarPaginaListar();
		clicarComXpath("//tr[td='" + nomeConta + "']//span[@class='glyphicon glyphicon-remove-circle']");
	}
	
	public String mensagemSucesso() {
		return pegarTextoBy(By.xpath("//div[@role='alert']"));
	}
}	

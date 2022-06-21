package br.ce.woliver.pages;

import br.ce.woliver.core.BasePage;
import br.ce.woliver.core.DriverFactory;

public class LoginPage extends BasePage{
	
	public void acessar_tela_inicial() {
		DriverFactory.getDriver().get("https://seubarriga.wcaquino.me");
	}
	
	public void logar(String email, String senha) {
		escrever("email", email);
		escrever("senha", senha);
		clicarComXpath("//button[@type='submit']");
	}
}

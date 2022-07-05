package br.ce.woliver.pages;

import br.ce.woliver.core.BasePage;

public class ResumoPage extends BasePage {

	public void excluirMovimentacao(String desc, String conta) {
		clicarComXpath("//tr[td='" + desc + "'and td='" + conta + "']//span[@class='glyphicon glyphicon-remove-circle']");
	}
}

package br.ce.woliver.core;

import static br.ce.woliver.core.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
	
	//classe criada para centralizar métodos que serão reutilizados

	public void escrever(String id_campo, String texto) {
		getDriver().findElement(By.id(id_campo)).clear();
		getDriver().findElement(By.id(id_campo)).sendKeys(texto);
	}
	
	public void escreverBy(By by, String texto) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}
	
	public String pegarValue(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public String pegarValueXpath(String xpath) {
		return getDriver().findElement(By.xpath(xpath)).getAttribute("value");
	}
	
	public String pegarTexto(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getText();
	}
	
	public String pegarTextoXpath(String xpath) {
		return getDriver().findElement(By.xpath(xpath)).getText();
	}
	
	public String pegarTextoBy(By by) {
		return getDriver().findElement(by).getText();
	}
	
	public void clicarElemento(String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public void clicarComXpath(String xpath) {
		getDriver().findElement(By.xpath(xpath)).click();
	}
	
	public WebElement searchByXpath(String xpath) {
		return getDriver().findElement(By.xpath(xpath));
		 
	}
	
	public void elementoSelectXpath(String nomeElemnto) {
		getDriver().findElement(By.xpath("//input[@value='"+nomeElemnto+"']")).click();;
	}
	
	public boolean isRadioMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void pegarSelectComValue(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		
		Select combo = new Select(element);
		combo.selectByValue(valor);
	}
	
	public void pegarSelectComText(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public WebElement getElement(String id) {
		return getDriver().findElement(By.id(id));
	}
	
	public void selectItem(Select item, String id) {
		item.selectByValue(id);
	}
	public void unSelectItens(Select item) {
		item.deselectAll();
	}
	
	public String validaMsgAlert() {
		Alert alert = getDriver().switchTo().alert();
		String msg = alert.getText();
		alert.accept();
		return msg;
	}
	
	public void alertAcept() {
		Alert alert = getDriver().switchTo().alert();
		alert.accept();
	}
	
	public void switchDefaultContent() {
		getDriver().switchTo().defaultContent();
	}
	
	/*******************JS************************/
	
	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, param);
	}
	public void entrarFrame(String string) {
		getDriver().switchTo().frame(string);
		
	}
	
	
	/*******************Tabela*********************/
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		//procurar coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da celula
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
	}

	private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;	
			for(int i =0; i < linhas.size(); i++) {
				if(linhas.get(i).getText().equals(valor)) {
					idLinha = i+1;
					break;
				}
		}		
		return idLinha;
	}

	private int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i =0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
	public String obterTexto(By xpath) {

		return getDriver().findElement(xpath).getText();
	}
}

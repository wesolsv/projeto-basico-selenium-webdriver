package br.ce.woliver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static br.ce.woliver.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import br.ce.woliver.core.BasePage;

public class MovimentacaoPage extends BasePage {
	
	private MenuPage menuPage = new MenuPage();
	
	public void inserirConta(String nomeConta) {
		escrever("nome", nomeConta);
		clicarComXpath("//button[.='Salvar']");
	}
	
	public void criarMovimento(String tipo, String dtTransacao, String dtPag, String desc, String pessoaInt, String valor, String conta,String status) {
		menuPage.acessarPaginaMovimentacao();
		pegarSelectComValue("tipo", tipo);
		escrever("data_transacao", dtTransacao);
		escrever("data_pagamento", dtPag);
		escrever("descricao", desc);
		escrever("interessado", pessoaInt);
		escrever("valor", valor);
		pegarSelectComText("conta", conta);
		clicarElemento(status);
		salvarMovimento();
	}
	
	public void salvarMovimento() {
		clicarComXpath("//button[.='Salvar']");
	}
	public String mensagemSucesso() {
		return pegarTextoBy(By.xpath("//div[@role='alert']"));
	}
	
	public List<String> obterErros(){
		List<WebElement> erros = getDriver().findElements(By.xpath("//div[@class='alert alert-danger']//li"));
		List<String> retorno = new ArrayList<String>();
		
		for(WebElement erro: erros) {
			retorno.add(erro.getText());
		}
		
		return retorno;
	}
}	

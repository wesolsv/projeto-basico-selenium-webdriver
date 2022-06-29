package br.ce.woliver.pages;

import br.ce.woliver.core.BasePage;

public class MovimentacaoPage extends BasePage {
	
	public void acessarPaginaMovimentacao() {
		clicarComXpath("//a[.='Criar Movimentação']");
	}
	
	public void acessarPaginaListar() {
		clicarComXpath("//a[@data-toggle='dropdown' and @role='button']");
		clicarComXpath("//a[.='Listar']");
	}
	
	public void acessarPaginaResumo() {
		clicarComXpath("//a[.='Resumo Mensal']");
	}
	
	public void inserirConta(String nomeConta) {
		escrever("nome", nomeConta);
		clicarComXpath("//button[.='Salvar']");
	}
	
	public void excluirMovimentacao(String desc, String conta) {
		clicarComXpath("//tr[td='" + desc + "'and td='" + conta + "']//a");
	}
	
	public void alterarConta(String novoNome) {
		acessarPaginaListar();
		clicarComXpath("//tr[td='Conta Inserida']//a[1]");
		inserirConta(novoNome);
	}
	
	public void removerConta(String nomeConta) {
		acessarPaginaListar();
		clicarComXpath("//tr[td='" + nomeConta + "']//a[2]");
	}
	
	public void criarMovimento(String tipo, String dtTransacao, String dtPag, String desc, String pessoaInt, String valor, String conta,String status) {
		acessarPaginaMovimentacao();
		pegarSelectComValue("tipo", tipo);
		escrever("data_transacao", dtTransacao);
		escrever("data_pagamento", dtPag);
		escrever("descricao", desc);
		escrever("interessado", pessoaInt);
		escrever("valor", valor);
		pegarSelectComText("conta", conta);
		clicarElemento(status);
		clicarComXpath("//button[.='Salvar']");
	}
}	

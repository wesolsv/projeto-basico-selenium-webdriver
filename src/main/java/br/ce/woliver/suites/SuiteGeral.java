package br.ce.woliver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.woliver.tests.ContaTest;
import br.ce.woliver.tests.MovimentacaoTest;
import br.ce.woliver.tests.ResumoTest;
import br.ce.woliver.tests.SaldoTest;

@RunWith(Suite.class)

@SuiteClasses({
	ContaTest.class,
	MovimentacaoTest.class,
	ResumoTest.class,
	SaldoTest.class
})

public class SuiteGeral {
	
}

package br.com.tcc.test;

import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;

/**
 * Superclasse para as classes Concordion que precisam da API Unitils.
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext("testContext.xml")
public abstract class IntegrationBaseTestClass  {

}
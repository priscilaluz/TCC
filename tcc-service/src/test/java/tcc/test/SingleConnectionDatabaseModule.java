/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.test;

import java.lang.reflect.Method;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.util.ClassUtils;
import org.unitils.core.TestListener;
import org.unitils.core.dbsupport.DefaultSQLHandler;
import org.unitils.database.DatabaseModule;
import static org.unitils.util.PropertyUtils.getString;

/**
 * Extensão do módulo de banco de dados do Unitils que mantém uma única conexão 
 * aberta durante toda a execução de um teste. A implementação garante que a 
 * mesma conexão de banco é utilizada para executar o DBUnit e, posteriormente,
 * é utilizada pelo código de produção chamado no método de teste. Ao final do teste
 * a conexão é fechada para garantir o isolamento entre o teste atual e o próximo. <p>
 * 
 * Internamente, esta classe fixa o datasource utilizado pelo Unitils para uma 
 * instância de {@link org.springframework.jdbc.datasource.SingleConnectionDataSource}.
 * Isto impede a utilização da propriedade org.unitils.database.config.DataSourceFactory.implClassName, 
 * tornando impossível customizar o tipo de datasource que é criado. Para utilizar 
 * este módulo, o desenvolvedor deve obrigatoriamente configurar algumas propriedades 
 * no arquivo unitils.properties do projeto de teste:
 * <blockquote>
 * <pre>
 * # Definição do módulo
 * unitils.module.database.className=petrobras.snarf.test.unitils.SingleConnectionDatabaseModule
 * 
 * # Configuração do datasource
 * database.driverClassName=
 * database.url=
 * database.userName=
 * database.password=
 * </pre>
 * </blockquote>
 */
public class SingleConnectionDatabaseModule extends DatabaseModule {
    public static final String PROPKEY_DATASOURCE_DRIVERCLASSNAME = "database.driverClassName";
    public static final String PROPKEY_DATASOURCE_URL = "database.url";
    public static final String PROPKEY_DATASOURCE_USERNAME = "database.userName";
    public static final String PROPKEY_DATASOURCE_PASSWORD = "database.password";

    protected DataSource createDataSource() {
        String driverClassName = getString(PROPKEY_DATASOURCE_DRIVERCLASSNAME, configuration);
        String databaseUrl = getString(PROPKEY_DATASOURCE_URL, configuration);
        String userName = getString(PROPKEY_DATASOURCE_USERNAME, null, configuration);
        String password = getString(PROPKEY_DATASOURCE_PASSWORD, null, configuration);
        
        try {
            Class.forName(driverClassName, true, ClassUtils.getDefaultClassLoader());
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Could not load JDBC driver class [" + driverClassName + "]", ex);
        }
        
        DataSource ds = new SingleConnectionDataSource(databaseUrl, userName, password, true);

        // Call the database maintainer if enabled
        if (updateDatabaseSchemaEnabled) {
            updateDatabase(new DefaultSQLHandler(ds));
        }
        return ds;
    }

    @Override
    public TestListener getTestListener() {
        return new SingleConnectionTestListener();
    }
    
    protected class SingleConnectionTestListener extends TestListener {
        @Override public void beforeTestSetUp(Object testObject, Method testMethod) {
            injectDataSource(testObject);
            startTransactionForTestMethod(testObject, testMethod);
        }
        @Override public void afterTestTearDown(Object testObject, Method testMethod) {
            endTransactionForTestMethod(testObject, testMethod);
            resetDataSourceConnection();
        }
    }
    
    private void resetDataSourceConnection() {
        if (dataSource instanceof SingleConnectionDataSource) {
            ((SingleConnectionDataSource)dataSource).resetConnection();
        }
    }
}

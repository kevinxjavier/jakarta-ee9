package com.kevinpina.configs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.kevinpina.util.JPAUtil;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

@ApplicationScoped
/** 
 * @Dependent In CDI, classes annotated with @Dependent are "pseudo-scoped", what means:
 * 		- "No injected instance of the bean is ever shared between multiple injection points"
 *  	- Multiple instances can exist at the same time
 *  	- "Any instance of the bean injected into an object that is being created by the container is bound to the lifecycle of the newly created object."
 *  	- A "POJO" declared with @Dependent annotation and injected into a e.g. @Stateless or @RequestScoped bean has the same lifecycle as the corresponding bean.
 */
public class ProducerResources {

	@Inject
	private Logger log;
	
	@Resource(name = "jdbc/MySQLDB")
	private DataSource ds;

	@Produces
	@RequestScoped
//	@Named("beanConnection") // because auto discovery is all bean-discovery-mode="all" a class of type
							 // Connection.class could be also instantiated so give a name a qualified name.
	@MysqlConnectionPrincipal // Or use @Named("beanConnection")
	private Connection getConnection() throws NamingException, SQLException {
//		Context initContext = new InitialContext();
//		Context envContext = (Context) initContext.lookup("java:/comp/env");
//		DataSource ds = (DataSource) envContext.lookup("jdbc/MySQLDB");
		return ds.getConnection();
	}

	// @Disposes will close automatically the connection
	// Not only works for databases will work for any Bean
	public void close(@Disposes @MysqlConnectionPrincipal Connection connection) throws SQLException {
		connection.close();
		log.info("Closing connection automatically of DataSource!");
	}
	
	@Produces
	// If we do not put a @Scope it will use the class scope in this case @Dependent but @Dependent is "pseudo-scoped" so we put this context @ApplicationScoped
	private Logger beanLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

	@Produces
	@RequestScoped
	// @Produces will create a bean that will be injected when use a attribute of type EntityManager.
	// @RequestScoped this bean once injected will be reused for all the request scopes.
	// this will work because of bean-discovery-mode="annotated" in ./jakarta-ee9-webapp-cdi-jpa/src/main/webapp/WEB-INF/beans.xml
	private EntityManager createEntityManager() {
		return JPAUtil.getEntityManager();
	}

	public void close(EntityManager em) {
		if (em != null && em.isOpen()) {
			em.close();
			log.info("Closing connection automatically of EntityManager!");
		}
	}

}

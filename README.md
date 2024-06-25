## DATABASE (jakarta-ee9-database)

* feature/01_simple_jdbc_singleton
* feature/02_simple_jdbc_one_connection_per_execution
* feature/03_simple_jdbc_pool
* feature/04_simple_jdbc_singleton_transactions
* feature/05_simple_jdbc_pool_transactions
* feature/06_simple_jdbc_pool_transactions_layer_service

## WEB (jakarta-ee9-webapp)

* feature/07_webapp_simple
* feature/08_webapp_form
* feature/09_webapp_form_bootstrap
* feature/10_webapp_form_bootstrap_headers

## COOKIES (jakarta-ee9-webapp-cookies)

* feature/11_webapp_cookies

## SESSION (jakarta-ee9-webapp-session)

* feature/12_webapp_session

## SHOPPING CART (jakarta-ee9-webapp-shopping-cart)
* feature/13_webapp_shopping_cart

## LISTENERS (jakarta-ee9-webapp-shopping-cart-listeners)
* feature/14_webapp_shopping_cart_listeners

## FILTERS (jakarta-ee9-webapp-shopping-cart-filters)
* feature/15_webapp_shopping_cart_filters

## DATABASE (jakarta-ee9-webapp-shopping-cart-filters-database)
* feature/16_webapp_shopping_cart_filters_database

## DATABASE (jakarta-ee9-webapp-shopping-cart-filters-database-crud)
* feature/17_webapp_shopping_cart_filters_database_crud

## JSTL (jakarta-ee9-webapp-shopping-cart-filters-database-crud-jstl)
* feature/18_webapp_shopping_cart_filters_database_crud_jstl

## Layout "Header - Footer" (jakarta-ee9-webapp-shopping-cart-filters-database-crud-jstl-layout)
* feature/19_webapp_shopping_cart_filters_database_crud_jstl_layout

## DATABASE "Web - Datasource" (jakarta-ee9-webapp-jdbc-pool-datasource)
* feature/20_webapp-jdbc-pool-datasource

## WEB "CDI" (jakarta-ee9-webapp-cdi)
* feature/21_webapp-cdi

	- 7bf35cafe169b563ee36a7f26b177ea0ca5e6ed7 Configuring CDI
	- 633e64785294f231774a566332dc41338a413b14 Configuring CDI simplifie
	- 36f2eb4d9e83a7bcc069b1eacb1b82231ede0ee6 CDI Changing traditional set of an attribute to injection (Autowired) @Inject: 
		* CDI Bean @RequestScope in class ProducerResources.java injected in ConnectionFilter.java.
		* CDI Bean @SessionScope in class ShoppingCart.java injected in ShoppingCartServlet.java.
		* CDI Bean @ApplicationScoped in classes ProductRepositoryImpl.java and CategoryRepositoryImpl.java and injected in ProductFormSevlet.java. 
		* CDI Bean @Alternative in class ProductServiceIJdbcmpl.java.
	- d8f0b93c772be1030658a810cdf1b5edcf307fc1 CDI Bean @Resource DataSource
	- 4fa64b42bb3b20136b9955a7b57d24399ba8beb0 @Qualifier
		* CDI Bean @Alternative deleted! instead we use now @Qualifier public @interface ProductServicePrincipal so we dont use @Name("") and
	  	  if we dont @Inject with annotation @ProductServicePrincipal will inject this class ProductServiceIJdbcmpl.java by default because the class 
	  	  ProductServiceImpl.java is annotated with @ProductServicePrincipal so the attributes that need it must use @Inject and @ProductServicePrincipal
		* @Qualifier public @interface @MysqlConnectionPrincipal use it instead of @Named("beanConnection")
	- c57360aef0066998c92f5bf05d08971ec83fe7be @Stereotype, @Disposes And bean-discovery-mode to "annotated"
		* @Stereotype interfaces annotations such as @Cart and @Repository are not only use for semantics like @Qualifier but also allows for define contexts suchs as @ApplicationScoped
		* @Disposes in ProducerResources.java will close automatically the connection and not only works for databases will work for any Bean. See catalina.out
		* bean-discovery-mode to "annotated" changed in WEB-INF/beans.xml: it means now that we have to annotate all the classes that we want it like Beans to be injected such as ProducerResources.java that would be annotated with @Dependent before was @Dependent by default because bean-discovery-mode washas the value "all", also we could annotate it with @ApplicationScoped and will works. Also becauase bean-discovery-mode is "annotated" classes annotated with @Alternative are not necessary to be annotated with @Alternative because classes not annotated with @xxxScopes or other contexts won't be injected like ProductServiceIJdbcmpl.java and this class beside won't be injected now by default with @Dependent so it is not a Bean so ProductServiceImpl.java no need to has @Named or @Qualifier.
	- 35c67bdd663a1167d881e1fb38d188221ea83e5e @PostConstruct, @PreDestroy And java.util.logging.Logger
	- 08a28033be6472fa5c802e36ec47e124cf78cd14 @InterceptorBinding, @Interceptor and @AroundInvoke
		* Intercepts methods from a CDI component, see Logging.java LoggingInterceptor.java ProductServiceImpl.java CategoryServiceImpl.java and @Servicio
	- df52e34c3601ca8f11cb87ac7b4a5b55ca396939 @InterceptorBinding, @Interceptor and @AroundInvoke
		* Intercepts methods from a CDI component, TransactionalJDBC.java TransactionalInterceptor.java
		* ConnectionFilter.java is modified
		* Servicio.java has add @TransactionalJDBC.java
		* ProductServiceImpl has @Servicio
		* Add interceptors in WEB-INF/beans.xml
		* Updating pom.xml changing path of deployment

## WEB "CDI & JPA" (jakarta-ee9-webapp-cdi-jpa)
* feature/22_webapp-cdi-jpa
	- Adding 
		pom.xml > "<artifactId>hibernate-core-jakarta</artifactId>"
		$ mkdir src/main/resources
		$ mkdir src/main/resources/META-INF
		$ touch src/main/resources/META-INF/persistence.xml
	
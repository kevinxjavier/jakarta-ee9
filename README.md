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
	- <we need a empty commit to update this id> CDI Bean @Resource DataSource

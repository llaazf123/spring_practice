package kr.co.uclick.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ignite.cache.hibernate.HibernateRegionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration	//configuration 선언
@ImportResource(locations = "classpath:applicationContext-ignite.xml")	//하나 또는 여러개의 XML configuration 파일들을 import할 때 사용
@ComponentScan({ "kr.co.uclick.service" })	//Component Scan은 XML에 일일이 빈등록을 하지않고 각 빈 클래스에 @Component를 통해 자동 빈 등록
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)	// Java Config 방식으로 트랜잭션을 구현
@EnableSpringConfigured	// 자바 기반 configuration을 사용할 시, 이 EnableSpringConfigured 어노테이션을 사용함으로서, 어떠한  @Configurationclass를 사용할 수 있음
@EnableJpaRepositories(basePackages = "kr.co.uclick.repository")		//JpaRepository 에 대한 설정정보를 자동적으로 로딩하고 이 정보를 토대로 Repository 빈을 등록하는 역할

public class SpringConfiguration {

	@Bean
	@Primary	//스프링은 하나의 타입에 빈 객체가 여러개인경우 그 중 우선순위를 갖는 빈이라는것을 지정할 수 있도록 @Primary 어노테이션을 제공
	public DataSource dataSource() {	//DB연결
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		//dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.23.104:3306/kopoctc?serverTimezone=Asia/Seoul");
		dataSource.setUsername("root");
		dataSource.setPassword("kopo17");
		return dataSource;
		
	}

	@Bean
	@DependsOn("igniteSystem")	//이 빈(Bean)은 igniteSystem이라는 빈을 참조하고 있다는 것을 알려줌
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {	//DataSource와 Hibernate Property, Entity가 위치한 package를 지정
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("kr.co.uclick.entity");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean
	@Primary
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {	//트랜잭션 경계를 지정하는데 사용. 트랜잭션이 어디서 시작하고 종료하는지, 종료할 때 정상 종료(커밋)인지 비정상 종료(롤백)인지를 결정
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {	//이렇게 하면 @Repository 클래스들에 대해 자동으로 예외를 Spring의 DataAccessException으로 일괄 변환해줌
		return new PersistenceExceptionTranslationPostProcessor();
	}

	public Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
		properties.setProperty(AvailableSettings.FORMAT_SQL, Boolean.TRUE.toString());
		properties.setProperty(AvailableSettings.SHOW_SQL, Boolean.TRUE.toString());
		properties.setProperty(AvailableSettings.DIALECT, MySQL5Dialect.class.getName());

		properties.setProperty(AvailableSettings.STATEMENT_BATCH_SIZE, "1000");

		properties.setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, Boolean.TRUE.toString());
		properties.setProperty(AvailableSettings.USE_QUERY_CACHE, Boolean.TRUE.toString());
		properties.setProperty(AvailableSettings.GENERATE_STATISTICS, Boolean.TRUE.toString());
		properties.setProperty(AvailableSettings.CACHE_REGION_FACTORY, HibernateRegionFactory.class.getName());

		properties.setProperty("org.apache.ignite.hibernate.ignite_instance_name", "cafe-grid");
		properties.setProperty("org.apache.ignite.hibernate.default_access_type", "NONSTRICT_READ_WRITE");

		properties.setProperty(AvailableSettings.PHYSICAL_NAMING_STRATEGY,
				CustomPhysicalNamingStrategyStandardImpl.class.getName());
		return properties;
	}

}

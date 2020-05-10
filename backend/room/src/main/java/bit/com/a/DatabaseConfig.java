package bit.com.a;

import java.util.Properties;

import javax.servlet.annotation.MultipartConfig;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "bit.com.a")
public class DatabaseConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		System.out.println("DatabaseConfig sqlSessionFactory");

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		Properties prop = new Properties();
		prop.setProperty("mapUnderscoreToCamelCase", "true");
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigurationProperties(prop);
		/*
		 * sqlSessionFactoryBean.getObject().getConfiguration().
		 * setMapUnderscoreToCamelCase(true);
		 */

		Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
		// myBatisConfig.getInputStream().available();
		sqlSessionFactoryBean.setConfigLocation(myBatisConfig);
		Resource[] arrResource = new PathMatchingResourcePatternResolver().getResources("classpath*:sqls/*.xml");
		// for (int i = 0; i < arrResource.length; i++) {
		// arrResource[i].getInputStream().available();
		// }

		sqlSessionFactoryBean.setMapperLocations(arrResource);

		return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}

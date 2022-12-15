package cn.mtttt.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = DataSourceLatterConfig.PACKAGE,sqlSessionTemplateRef = "latterSqlSessionTemplate")
public class DataSourceLatterConfig {
    static final String PACKAGE = "cn.mtttt.mapper.latter";

    /**
     * 获取数据源的配置
     * @return
     */
    @Bean(name = "latterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.latter")
    public DataSource latterDatasource(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "latterSqlSessionFactory")
    public SqlSessionFactory latterSqlSessionFactory(@Qualifier("latterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }
    @Bean
    public DataSourceTransactionManager latterTransactionManager(@Qualifier("latterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate latterSqlSessionTemplate(@Qualifier("latterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

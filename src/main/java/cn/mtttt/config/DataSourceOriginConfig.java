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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = DataSourceOriginConfig.PACKAGE,sqlSessionTemplateRef = "originSqlSessionTemplate")
public class DataSourceOriginConfig {
    static final String PACKAGE = "cn.mtttt.mapper.origin";
    /**
     * 获取数据源的配置
     * @return
     */
    @Bean(name = "originDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.origin")
    @Primary
    public DataSource originDatasource(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "originSqlSessionFactory")
    @Primary
    public SqlSessionFactory originSqlSessionFactory(@Qualifier("originDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }
    @Bean
    @Primary
    public DataSourceTransactionManager originTransactionManager(@Qualifier("originDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate originSqlSessionTemplate(@Qualifier("originSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

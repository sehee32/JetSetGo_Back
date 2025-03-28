package kr.co.jetsetgo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = {"kr.co.jetsetgo.**.*"},
			annotationClass = org.apache.ibatis.annotations.Mapper.class)
public class MyBatisConfig {

	@Bean(name = "sqlSessionFactory")   //sqlSessionFactory라는 이름으로 Bean등록
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();      //Bean으로 등록할 SqlSessionFactory객체 생성
        sqlSessionFactoryBean.setDataSource(dataSource);        //sqlSessionFactoryBean에 dataSource정보를 set함 (Datasource정보는 application.yml에 명시함)
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/**/*.xml"));    //Mapper xml파일의 위치를 명시
        return sqlSessionFactoryBean.getObject();
    }
	
}

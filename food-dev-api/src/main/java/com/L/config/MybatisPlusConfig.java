package com.L.config;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
@Configuration
public class MybatisPlusConfig {
 @Bean public MybatisPlusInterceptor mybatisPlusInterceptor(@Value("${app.db-type:MYSQL}") String dbType){
  MybatisPlusInterceptor i=new MybatisPlusInterceptor();
  DbType type = "H2".equalsIgnoreCase(dbType) ? DbType.H2 : DbType.MYSQL;
  i.addInnerInterceptor(new PaginationInnerInterceptor(type));
  return i;
 }
}

package cn.azzhu.myo2o.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration	//该类是一个配置类
@EnableSwagger2	//启用swagger功能
public class Swagger2Config {
	@Bean
	public Docket adminApiConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("adminApi")
				.apiInfo(adminApiInfo())	//参数ApiInfo，我们这里直接调用的是下面的方法
				.select()
				.apis(RequestHandlerSelectors.basePackage("cn.azzhu.myo2o.controller.sys"))	//为哪个包下的Controller生成对应的接口文档
				.paths(PathSelectors.any()).build();
	}

	//设置Api的基本信息
	private ApiInfo adminApiInfo(){
		return new ApiInfoBuilder()
				.title("后台管理系统-O2O系统管理API文档")
				.description("本文档描述了后台管理系统O2O系统管理微服务接口定义")
				.version("1.0")
				.contact(new Contact("azzhu", "http://baidu.com", "519715276@qq.com"))
				.build();
	}
}
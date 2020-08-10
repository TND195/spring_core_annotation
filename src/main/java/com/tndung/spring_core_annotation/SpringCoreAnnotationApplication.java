package com.tndung.spring_core_annotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

interface Outfit {
	void wear();
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class SimpleBean {
	private String name;

	@Override
	public String toString() {
		return "This is a simple bean, name: " + name;
	}
}

@Primary
@Component("bikini")
@Data
 class Bikini implements Outfit {
	@Override
	public void wear() {
		System.out.println("Mặc bikini");
	}
}

@Component("default")
class Default implements Outfit {
	@Override
	public void wear() {
		System.out.println("Mặc đồ");
	}
}

@Component("naked")
 class Naked implements Outfit {
	@Override
	public void wear() {
		System.out.println("Đang không mặc gì");
	}
}

@Component
@Data
 class Girl {
	@Autowired
	@Qualifier("bikini")
	Outfit outfit;

}


@SpringBootApplication
public class SpringCoreAnnotationApplication {
	@Bean
	SimpleBean simpleBeanConfig() {
		return new SimpleBean("tnd");
	}

	public static void main(String[] args)
	{
		ApplicationContext context = SpringApplication.run(SpringCoreAnnotationApplication.class, args);
		// Khi chạy xong, lúc này context sẽ chứa các Bean có đánh
		// dấu @Component.
		// System.out.println(context.getBeanDefinitionCount());
		Girl girl = context.getBean(Girl.class);

		SimpleBean simpleBean = context.getBean(SimpleBean.class);

//		// Lấy Bean ra bằng cách
//		Outfit outfit = context.getBean(Outfit.class);
//
//		// In ra để xem thử nó là gì
//		System.out.println("Instance: " + outfit);

		System.out.println("Girl Instance: " + girl);

		System.out.println("Girl Outfit: " + girl.outfit);

		girl.outfit.wear();

		// In ra màn hình
		System.out.println("Simple Bean Example: " + simpleBean.toString());
	}

}

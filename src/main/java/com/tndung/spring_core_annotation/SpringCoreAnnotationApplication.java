package com.tndung.spring_core_annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

interface Outfit {
	void wear();
}

@Component("bikini")
 class Bikini implements Outfit {
	@Override
	public void wear() {
		System.out.println("Mặc bikini");
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

	public static void main(String[] args)
	{
		ApplicationContext context = SpringApplication.run(SpringCoreAnnotationApplication.class, args);
		// Khi chạy xong, lúc này context sẽ chứa các Bean có đánh
		// dấu @Component.

		Girl girl = context.getBean(Girl.class);

		System.out.println("Girl Instance: " + girl);

		System.out.println("Girl Outfit: " + girl.outfit);

		girl.outfit.wear();
	}

}

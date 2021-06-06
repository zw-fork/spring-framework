import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircularDependenciesTest {

	@Bean
	public Demo01 demo01() {
		return new Demo01();
	}

	@Bean
	public Demo02 demo02() {
		return new Demo02();
	}

	public static void main(String[] args) {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CircularDependenciesTest.class);
		Demo01 demo01 = applicationContext.getBean("demo01", Demo01.class);
		System.out.println(demo01);
	}


}

class Demo01 {

	@Autowired
	private Demo02 demo02;
}

class Demo02 {
	@Autowired
	private Demo01 demo01;
}
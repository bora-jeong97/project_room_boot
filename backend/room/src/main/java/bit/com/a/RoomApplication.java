package bit.com.a;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan(basePackages = "bit.com.a")
public class RoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomApplication.class, args);
	}

}

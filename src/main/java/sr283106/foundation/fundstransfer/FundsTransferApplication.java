package sr283106.foundation.fundstransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FundsTransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundsTransferApplication.class, args);
	}

}

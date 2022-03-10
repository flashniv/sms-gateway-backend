package ua.com.serverhelp.smsgateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ua.com.serverhelp.smsgateway.db.AccountRepository;
import ua.com.serverhelp.smsgateway.entity.Account;

@SpringBootApplication
@EnableJpaRepositories
public class SmsGatewayApplication {
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			AccountRepository accountRepository=ctx.getBean(AccountRepository.class);

			Account account=new Account();
			account.setNumber("+16062038541");
			account.setPassword("uang8ieQu|ae^T*oo^l9thaeSaiVi1");

			accountRepository.save(account);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SmsGatewayApplication.class, args);
	}
}

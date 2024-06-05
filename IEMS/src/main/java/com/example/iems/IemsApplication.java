package com.example.iems;

		import com.example.iems.model.Enum.Role;
		import com.example.iems.dto.User.CreateUserRequest;
		import com.example.iems.service.UserService;
		import org.springframework.boot.CommandLineRunner;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;

		import java.util.Set;

@SpringBootApplication
public class IemsApplication implements CommandLineRunner {

	private final UserService userService;

	public IemsApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(IemsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createDummyData();
	}

	private void createDummyData(){
		CreateUserRequest request = CreateUserRequest.builder()
				.name("ADMIN")
				.username("admin")
				.password("1")
				.authorities(Set.of(Role.ROLE_ADMIN))
				.build();
		userService.createUser(request);
	}
}

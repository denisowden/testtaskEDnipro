package com.journal.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@RequiredArgsConstructor
public class DemoApplication {

//	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@PostConstruct
//	public void init(){
//		System.out.println(passwordEncoder.encode("admin"));
//	}

}

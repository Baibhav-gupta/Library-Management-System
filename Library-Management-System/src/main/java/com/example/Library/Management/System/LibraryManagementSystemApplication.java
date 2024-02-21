package com.example.Library.Management.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
//(exclude={DataSourceAutoConfiguration.class})
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

}

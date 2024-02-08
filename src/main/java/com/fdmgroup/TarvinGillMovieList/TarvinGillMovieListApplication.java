package com.fdmgroup.TarvinGillMovieList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fdmgroup.TarvinGillMovieList.security.RsaKeyProperties;
@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class TarvinGillMovieListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarvinGillMovieListApplication.class, args);
	}
}

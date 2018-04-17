package com.cxn.concurrency;

import com.cxn.concurrency.annotations.NotRecommend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author http://start.spring.io
 */
@SpringBootApplication
public class ConcurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}
}

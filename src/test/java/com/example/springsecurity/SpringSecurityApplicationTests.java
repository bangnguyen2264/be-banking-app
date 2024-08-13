package com.example.springsecurity;

import com.example.springsecurity.model.dto.TransactionDto;
import com.example.springsecurity.util.Ultilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringSecurityApplicationTests {

	@Test
	public void testSerialization() {
		TransactionDto dto = TransactionDto.builder()
				.id(1L)
				.amount(10000)
				.transactionType("DEPOSIT")
				.description("aaa")
				.build();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(dto);
			System.out.println("Serialized DTO: " + json);
		} catch ( Exception e) {
			log.error(e.getMessage());
		}
	}

}

package ru.danilenko.neoflex;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.danilenko.neoflex.controller.HolidayPaymentCalculator;

@SpringBootTest
@AutoConfigureMockMvc
class NeoflexApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private HolidayPaymentCalculator holidayPaymentCalculator;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(holidayPaymentCalculator);
	}


	@Test
	void controllerTestCalculation1() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate").param("salary","1"))
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("Incorrect input data")));

	}

	@Test
	void controllerTestCalculation2() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate").param("salary","1").param("dayOfHolidays","1"))
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("1")));

	}

	@Test
	void controllerTestCalculation3() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate").param("salary","1")
						.param("dayOfHolidays","1")
						.param("dayOfStart","2024-03-20")
				)
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("1")));

	}
	@Test
	void controllerTestCalculation4() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate").param("salary","1")
						.param("dayOfHolidays","1")
						.param("dayOfStart","2024-03-20")
						.param("dayOfEnd","2024-03-23")
				)
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("3")));

	}

	@Test
	void controllerTestCalculation5() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate").param("salary","1")
						.param("dayOfStart","2024")
						.param("dayOfEnd","2024")
				)
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());

	}

}

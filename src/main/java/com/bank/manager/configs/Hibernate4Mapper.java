package com.bank.manager.configs;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class Hibernate4Mapper extends ObjectMapper {

	public Hibernate4Mapper() {
		registerModule(new Hibernate4Module());
	}

}

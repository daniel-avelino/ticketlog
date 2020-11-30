package com.ticketlogapi.services;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class usd implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ask")
	private Double ask;

	public Double getAsk() {
		return ask;
	}

	@Override
	public String toString() {
		return "[Dolar =" + getAsk() + "]";
	}

}

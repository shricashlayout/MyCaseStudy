package com.myretail.domain;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

public class Price implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NumberFormat
	private double value;
	@NotEmpty
	private String currency_code;
	
	public Price() {
		super();
	}
	public Price(double value, String currency_code) {
		super();
		this.value = value;
		this.currency_code = currency_code;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	@Override
	public String toString() {
		return "Price [value=" + value + ", currency_code=" + currency_code + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency_code == null) ? 0 : currency_code.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (currency_code == null) {
			if (other.currency_code != null)
				return false;
		} else if (!currency_code.equals(other.currency_code))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}
	
}

package fr.rmo.bak.api.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AmountBean {

	// Consider an operation with a value equals to 0 non
	@Min(value = 1)
	@NotNull
	private Long value;

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}

package br.com.harley.budget.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AmountDTO {

	@NotBlank
	@NotEmpty
	@ApiModelProperty(value = "Valor d", name = "underInvestigation", dataType = "Boolean", example = "false")
	private BigDecimal amount;
	
}

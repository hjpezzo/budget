package br.com.harley.budget.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.BeanUtils;

import br.com.harley.budget.entity.Budget;
import br.com.harley.budget.enums.Folder;
import br.com.harley.budget.enums.Origin;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddBudgetRequest {

	@NotBlank
	@NotEmpty
	@ApiModelProperty(value = "Total do Orçamento", name = "totalAmount", dataType = "BigDecimal", example = "1000.00")
	private BigDecimal totalAmount;

	@NotBlank
	@NotEmpty
	@ApiModelProperty(value = "Orçamento gasto", name = "spentAmount", dataType = "BigDecimal", example = "100.00")
	private BigDecimal spentAmount;

	@Enumerated(EnumType.STRING)
	@NotBlank
	@NotEmpty
	@ApiModelProperty(value = "Pastas", name = "possibleDestinations", dataType = "Enum: ex. HEALTH, SPORTS", example = "HEALTH")
	private List<Folder> possibleDestinations;

	@Enumerated(EnumType.STRING)
	@NotBlank
	@NotEmpty
	@ApiModelProperty(value = "Origem", name = "origin", dataType = "Enum: FEDERAL, STATE, COUNTY", example = "COUNTY")
	private Origin origin;

	public Budget toEntity() {
		Budget budget = new Budget();
		BeanUtils.copyProperties(this, budget);
		return budget;
	}
}

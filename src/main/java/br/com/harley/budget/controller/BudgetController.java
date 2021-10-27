package br.com.harley.budget.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.harley.budget.dto.AddBudgetRequest;
import br.com.harley.budget.dto.AmountDTO;
import br.com.harley.budget.entity.Budget;
import br.com.harley.budget.enums.Folder;
import br.com.harley.budget.service.BudgetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

	BudgetService budgetService;

	public BudgetController(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Adicionar orçamento", notes = "Esse método adiciona um novo orçamento")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Orçamento criado"),
			@ApiResponse(code = 500, message = "Erro Interno - inconsistência nos valores informados (ver campo 'message' no retorno)") })
	public Budget addBudget(@RequestBody AddBudgetRequest budget) {
		return budgetService.addBudget(budget);
	}

	@GetMapping()
	@ApiOperation(value = "Lista orçamentos", notes = "Esse método retorna uma lista com todos os orçamentos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno da lista") })
	public List<Budget> listBudgets() {
		return budgetService.listBudgets(null);
	}

	@GetMapping(path = "/possibleDestination/{folder}")
	@ApiOperation(value = "Lista orçamentos por destino", notes = "Esse método retorna uma lista com todos os orçamentos do destino informado")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno da lista"),
			@ApiResponse(code = 400, message = "Destino informado inexistente")})
	public List<Budget> listBudgets(@PathVariable(required = false) Folder folder) {
		return budgetService.listBudgets(folder);
	}

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Lista orçamentos por id", notes = "Esse método retorna o orçamento com id informado")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno do orçamento")})
	public Optional<Budget> getBudget(@PathVariable Long id) {
		return budgetService.getBudget(id);
	}

	@PatchMapping(path = "/{id}/expense")
	@ApiOperation(value = "Atualiza valor gasto", notes = "Esse método atualiza o valor já gasto do orçamento com o id informado")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Orçamento atualizado"),
			@ApiResponse(code = 500, message = "Erro Interno - inconsistência nos valores informados (ver campo 'message' no retorno)")})
	public Budget updateSpentAmount(@PathVariable Long id, @RequestBody AmountDTO amount) {
		return budgetService.updateSpentAmount(id, amount);
	}

}

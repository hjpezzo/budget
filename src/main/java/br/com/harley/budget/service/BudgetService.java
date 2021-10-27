package br.com.harley.budget.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.harley.budget.dto.AddBudgetRequest;
import br.com.harley.budget.dto.AmountDTO;
import br.com.harley.budget.entity.Budget;
import br.com.harley.budget.enums.Folder;
import br.com.harley.budget.repository.BudgetRepository;

@Service
public class BudgetService {

	BudgetRepository budgetRepository;

	public BudgetService(BudgetRepository budgetRepository) {
		this.budgetRepository = budgetRepository;
	}

	public Budget addBudget(AddBudgetRequest addBudgetRequest) {
		validarSpentAmoutPositivo(addBudgetRequest.getSpentAmount());
		
		return budgetRepository.save(addBudgetRequest.toEntity());
	}

	public List<Budget> listBudgets(Folder folder) {
		if (folder != null) {
			return budgetRepository.findAllByPossibleDestinations(folder);
		}
		return budgetRepository.findAll();
	}
	
	public Optional<Budget> getBudget(Long budgetId) {
		return budgetRepository.findById(budgetId);
	}

	@Transactional
	public Budget updateSpentAmount(Long budgetId, AmountDTO amount) {

		validarSpentAmoutMaiorQueZero(amount.getAmount());
		
		Budget budget = budgetRepository.getById(budgetId);
		
		BigDecimal subtract = budget.getTotalAmount().subtract(budget.getSpentAmount());
		int compareTo = amount.getAmount().compareTo(subtract);
		if (compareTo > 0) {
			throw new RuntimeException("Valor maior que o orçamento disponível");
		}
		
		budget.setSpentAmount(budget.getSpentAmount().add(amount.getAmount()));
		budgetRepository.save(budget);

		return budget;
	}

	private void validarSpentAmoutPositivo(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new RuntimeException("Valor deve ser maior que zero");
		}
	}

	private void validarSpentAmoutMaiorQueZero(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("Valor deve ser maior que zero");
		}
	}

}

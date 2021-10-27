package br.com.harley.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.harley.budget.entity.Budget;
import br.com.harley.budget.enums.Folder;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

	List<Budget> findAllByPossibleDestinations(Folder folder);

}

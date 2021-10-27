package br.com.harley.budget.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.harley.budget.enums.Folder;
import br.com.harley.budget.enums.Origin;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private BigDecimal totalAmount;

	@Column(nullable = false)
	private BigDecimal spentAmount;

	@ElementCollection(targetClass = Folder.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private List<Folder> possibleDestinations;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Origin origin;

}

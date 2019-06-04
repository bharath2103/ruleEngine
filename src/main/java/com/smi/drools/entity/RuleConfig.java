package com.smi.drools.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smi.drools.audit.RuleConfigEntityListener;
import com.smi.drools.enumutil.ConditionalEnum;
import com.smi.drools.enumutil.EnrichmentEnum;
import com.smi.drools.enumutil.FilterEnum;
import com.smi.drools.enumutil.ModelTypeEnum;

import lombok.Data;

@Entity
@Table(name = "RULE_CONFIG")
@EntityListeners(RuleConfigEntityListener.class)
@Data
public class RuleConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 859456017285881118L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotEmpty
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@NotEmpty
	@Column(name = "ruleDescription")
	private String ruleDescription;
	
	@NotEmpty
	@Column(name = "emailId", nullable = false)
	private String emailId;

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "ruleConfig", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<RuleBuilder> ruleBuilders;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rule_id", referencedColumnName = "id")
	private Rule rule;

	@Column(name = "enable")
	private boolean enable;

	/*public static void main(String[] args) {
		RuleConfig ruleConfig = new RuleConfig();
		ruleConfig.setName("Invoice Enricher");
		ruleConfig.setRuleDescription("This rule is to enrich invoice number if it is empty");

		List<RuleBuilder> ruleBuilders = new ArrayList<>();
		// Rule 1
		RuleBuilder ruleBuilder = new RuleBuilder();
		ruleBuilder.setRuleName("drule11");
		ruleBuilder.setRuleGroupName("");
		ruleBuilder.setModelType(ModelTypeEnum.CUSTOMERDOCUMENT);
		List<ConditionBuilder> conditionBuilders = new ArrayList<>();

		ConditionBuilder conditionBuilder = new ConditionBuilder();
		conditionBuilder.setMetaField(ModelTypeEnum.SENDEREID);
		conditionBuilder.setFilter(FilterEnum.EQUALS);
		conditionBuilder.setMetaValue("S123");
		conditionBuilder.setConditionOperator(ConditionalEnum.AND);
		conditionBuilder.setRuleBuilder(ruleBuilder);
		conditionBuilders.add(conditionBuilder);

		ConditionBuilder conditionBuilder1 = new ConditionBuilder();
		conditionBuilder1.setMetaField(ModelTypeEnum.RECEIVEREID);
		conditionBuilder1.setFilter(FilterEnum.EQUALS);
		conditionBuilder1.setMetaValue("R123");
		conditionBuilder1.setConditionOperator(null);
		conditionBuilder1.setRuleBuilder(ruleBuilder);
		conditionBuilders.add(conditionBuilder1);

		ruleBuilder.setConditionBuilders(conditionBuilders);

		List<ActionBuilder> actionBuilders = new ArrayList<>();

		ActionBuilder actionBuilder = new ActionBuilder();
		actionBuilder.setEnrichement(EnrichmentEnum.RULE);
		actionBuilder.setEnrichmentAction("Enricher Common");
		actionBuilder.setRuleBuilder(ruleBuilder);
		actionBuilders.add(actionBuilder);

		ActionBuilder actionBuilder1 = new ActionBuilder();
		actionBuilder1.setEnrichement(EnrichmentEnum.NUMBERENRICHMENT);
		actionBuilder1.setEnrichmentAction("roundOffAmount");
		actionBuilders.add(actionBuilder1);

		ruleBuilder.setActionBuilders(actionBuilders);
		ruleBuilder.setRuleConfig(ruleConfig);
		ruleBuilders.add(ruleBuilder);
		ruleConfig.setRuleBuilders(ruleBuilders);

		ObjectMapper mapper = new ObjectMapper();
		try {
			String writeValueAsString = mapper.writeValueAsString(ruleConfig);
			System.out.println(writeValueAsString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}*/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleConfig other = (RuleConfig) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
}

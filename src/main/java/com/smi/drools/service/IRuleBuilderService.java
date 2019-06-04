package com.smi.drools.service;

import com.smi.drools.exception.RuleException;

public interface IRuleBuilderService {
	
	public String getRuleNameById(long id) throws RuleException;

	public String getRuleGroupNameById(Long id) throws RuleException;

	public long getCountByRuleName(String ruleName);

	public long getCountByRuleGroupName(String ruleGroupName);
	
	

}

package com.smi.drools.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smi.drools.dao.RuleBuilderRepository;
import com.smi.drools.exception.RuleException;
import com.smi.drools.service.IRuleBuilderService;
import com.smi.drools.util.ErrorConstants;

@Service
public class RuleBuilderService implements IRuleBuilderService {

	@Autowired
	private RuleBuilderRepository ruleBuilderRepository;

	@Override
	public String getRuleNameById(long id) throws RuleException {
		String ruleName = "";
		try {
			ruleName = ruleBuilderRepository.getRuleNameById(id);
		} catch (Exception e) {
			throw new RuleException(ErrorConstants.DB_CONNECTION_UNAVAILABLE, e.getCause());
		}
		return ruleName;
	}

	@Override
	public String getRuleGroupNameById(Long id) throws RuleException {
		String groupName = "";
		try {
			groupName = ruleBuilderRepository.getRuleGroupNameById(id);
		} catch (Exception e) {
			throw new RuleException(ErrorConstants.DB_CONNECTION_UNAVAILABLE, e.getCause());
		}
		return groupName;
	}

	@Override
	public long getCountByRuleName(String ruleName) {
		return ruleBuilderRepository.getCountByRuleName(ruleName);
	}

	@Override
	public long getCountByRuleGroupName(String ruleGroupName) {
		return ruleBuilderRepository.getCountByRuleGroupName(ruleGroupName);
	}

}

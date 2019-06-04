package com.smi.drools.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smi.drools.entity.RuleConfig;

public interface RuleConfigRepository extends JpaRepository<RuleConfig, Long> {

	List<RuleConfig> findByEmailId(String emailId);

	@Query("SELECT count(*) from RuleConfig rc where rc.name=:name")
	public Long getCountByName(@Param("name") String name);

	@Query("SELECT name from RuleConfig rc where rc.id=:id")
	String getRuleConfigNameById(@Param("id") Long id);

}

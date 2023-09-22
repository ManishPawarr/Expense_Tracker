package com.example.project.projecttry3.service;

import java.util.List;

public interface UserSpecificService {

	public Long calculateSpent(List<Long> spent);
	
	public Long calculateDeposit(List<Long> deposit);
	
	public Long calculateProfit(List<Long> spent, List<Long> deposit);
	
}

package com.example.project.projecttry3.service;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class UserSpecificServiceImpl implements UserSpecificService{

	
	@Override
	public Long calculateSpent(List<Long> spent) {
		 long sum1 = 0;
	      for (int i = 0; i < spent.size(); i++) { 
	         sum1 += spent.get(i); 
	      }
		return sum1;
	}

	@Override
	public Long calculateDeposit(List<Long> deposit) {
		long sum2 = 0;
		for (int i = 0; i < deposit.size(); i++) {
			sum2 += deposit.get(i);
		}
		return sum2;
	}

	@Override
	public Long calculateProfit(List<Long> spent, List<Long> deposit) {
		long sum1 = 0;
		long sum2 = 0;
	      for (int i = 0; i < spent.size(); i++) { 
	         sum1 += spent.get(i); 
	      }
	      for (int i = 0; i < deposit.size(); i++) {
				sum2 += deposit.get(i);
		  }
	    
	      return (sum2 - sum1);
	}

}

package com.example.project.projecttry3.service;

import java.util.List;
import java.util.Map;

public interface FriendsService {

	public Map<String, Integer> calculateSums(List<String> names, List<Integer> amounts);
	
	public void deleteDataByEmail(String email);
}

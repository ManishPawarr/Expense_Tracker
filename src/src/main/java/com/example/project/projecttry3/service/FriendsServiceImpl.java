package com.example.project.projecttry3.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.projecttry3.model.FriendsCategory;
import com.example.project.projecttry3.repository.FriendsRepository;

@Service
public class FriendsServiceImpl implements FriendsService{

	@Autowired
	private FriendsRepository fRepo;
	
	@Override
	public Map<String, Integer> calculateSums(List<String> names, List<Integer> amounts) {
		Map<String, Integer> personSums = new HashMap<>();

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            int amount = amounts.get(i);

            personSums.put(name, personSums.getOrDefault(name, 0) + amount);
        }

        return personSums;
	}

	@Override
    @Transactional
    public void deleteDataByEmail(String email) {
        // Retrieve the entity by email
        List<FriendsCategory> friendsCategory = fRepo.findByEmail(email);

        if (friendsCategory != null) {
            // Delete the retrieved entity
            fRepo.deleteAll(friendsCategory);
        }
    }
	

	
	
}

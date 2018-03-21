package com.pluralsight.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;
import com.pluralsight.util.ServiceError;

public class RestControllerTest {

	@Test(timeout=7000)
	public void testCreateRide() {
		RestTemplate restTemplate = new RestTemplate();  // restful route
		
		Ride ride = new Ride();
		ride.setName("Transactional Ride");
		ride.setDuration(10);
		
		ride = restTemplate.postForObject("http://localhost:8080/ride_tracker/ride", ride, Ride.class); // 用 postForObject 返回物件，用 put 不行
		System.out.println("ride " + ride);
	}
	
	
	@Test(timeout=6000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/ride_tracker/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}
	
	@Test(timeout=8000)
	public void testGetRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);
		System.out.println("Ride name: " + ride.getName());
	}
	
	@Test(timeout=8000)
	public void testUpdateRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);
		
		ride.setDuration(ride.getDuration() + 1);
		
		restTemplate.put("http://localhost:8080/ride_tracker/ride", ride);
		
		System.out.println("Ride name: " + ride.getName());
	}
	
	@Test(timeout=8000)
	public void testBatchUpdate() {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getForObject("http://localhost:8080/ride_tracker/batch", Object.class);
	}
	
	@Test(timeout=8000)
	public void testDelete() {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete("http://localhost:8080/ride_tracker/delete/4");
	}
	
	@Test(timeout=8000)
	public void test() {
		RestTemplate restTemplate = new RestTemplate();
		ServiceError err = restTemplate.getForObject("http://localhost:8080/ride_tracker/test", ServiceError.class);
		System.out.println(err);
	}
	
}
 
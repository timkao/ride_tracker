package com.pluralsight.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pluralsight.model.Ride;

public class RideRowMapper implements RowMapper<Ride> {
	
	@Override
	public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {   // 逐筆 row 透過 ResultSet 帶入，並透過 rowNum 得知這是第幾筆
		// 因此我們可以針對每一筆資料去操作
		Ride ride = new Ride();
		ride.setId(rs.getInt("id"));
		ride.setName(rs.getString("name"));
		ride.setDuration(rs.getInt("duration"));
		
		// 返回的每一筆資料都會被儲存到一個 List 裡面，最後返回這個 List，很像是 javaScript 的 array.map(func(element))
		return ride;
	}
}

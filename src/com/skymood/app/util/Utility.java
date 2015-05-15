package com.skymood.app.util;

import android.text.TextUtils;

import com.skymood.app.db.City;
import com.skymood.app.db.County;
import com.skymood.app.db.Province;
import com.skymood.app.db.SkyMoodDB;

public class Utility {
	public synchronized static boolean handleProvincesResponse(SkyMoodDB skyMoodDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					skyMoodDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	public synchronized static boolean handleCitiesResponse(SkyMoodDB skyMoodDB, 
			String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					skyMoodDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}	
	
	public synchronized static boolean handleCountiesResponse(SkyMoodDB skyMoodDB, 
			String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					skyMoodDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}	
	
}

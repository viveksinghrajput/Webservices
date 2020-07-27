/*
*PrePlanningServiceImpl.java
*Created By		:Kamal Thappa
*Created Date	:Apr 4, 2018
*/
package com.rural.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rural.DAO.PrePlanningDAO;
import com.rural.Model.CensusMaster;
import com.rural.Model.ConversionMaster;
import com.rural.Model.PrePlanItemList;
import com.rural.Model.PrePlanMaster;
import com.rural.Model.UserMaster;

@Component
@Service
public class PrePlanningServiceImpl implements PrePlanningService {

	@Autowired
	PrePlanningDAO planningDAO;

	CensusMaster census;
	static Logger logger =Logger.getLogger(PrePlanningServiceImpl.class);

	@Override
	public Map<String, String> getBrandList() {
		return planningDAO.brandMap();
	}

	@Override
	public Map<String, String> getStateList() {
		return planningDAO.stateMap();
	}

	@Override
	public List<String> getCityList() {
		return null;
	}

	@Override
	public List<PrePlanMaster> getAllPrePlan() {
		return planningDAO.showAllPrePlanData();
	}

	@Override
	public List<String> getCityofState(String State) {
		return planningDAO.getcityList(State);
	}
	
	@Override
	public List<String> getCityofStateBrand(String strBrand, String strState) {
		
		return planningDAO.getcityList(strBrand,strState);
	}

	@Override
	public List<String> getConvAndSat(String strBrand) {
		return planningDAO.getBrandConvAndSat(strBrand);
	}

	@Override
	public String getSatData(String[] strCity){
		StringBuffer strCityAndSat=new StringBuffer(); 
		for(String city:strCity) {
			
			String sat=planningDAO.getCitySaturation(city);
			strCityAndSat.append(city+": "+sat+"\n");
		}
		return strCityAndSat.toString();
	}
	
	@Override
	public String getSatData(String[] strCity, String strBrand, String strState) {
		StringBuffer strCityAndSat=new StringBuffer(); 
		for(String city:strCity) {
			
			String sat=planningDAO.getSaturation(city,strBrand,strState);
			strCityAndSat.append(city+": "+sat+"\n");
		}
		return strCityAndSat.toString();
	}

	public List<PrePlanItemList> testPrePlanListData(String strBrand, String strState, String strCity, String strSpan) {
		List<PrePlanItemList> preplantemp = new ArrayList<PrePlanItemList>();

		// Test Data
		PrePlanItemList test = new PrePlanItemList();
		test.setBrand("Lipton");
		test.setState("KA");
		test.setCity("Bangalore");
		test.setTot_pop(190000);
		test.setTot_hh(14000);
		test.setConversion(200);
		test.setContactDone("50");
		test.setAvgSales(20);
		test.setPromotorNum(5);
		test.setTeamNum(1);

		PrePlanItemList test2 = new PrePlanItemList();
		test2.setBrand("Axe");
		test2.setState("Tamil Nadu");
		test2.setCity("Chennai");
		test2.setTot_pop(110000);
		test2.setTot_hh(11000);
		test2.setConversion(80);
		test2.setContactDone("2000");
		test2.setAvgSales(190);
		test2.setPromotorNum(98);
		test2.setTeamNum(12);

		PrePlanItemList test3 = new PrePlanItemList();
		test3.setBrand("Cif");
		test3.setState("Punjab");
		test3.setCity("Hisar");
		test3.setTot_pop(100002);
		test3.setTot_hh(2000);
		test3.setConversion(40);
		test3.setContactDone("00");
		test3.setAvgSales(0);
		test3.setPromotorNum(7);
		test3.setTeamNum(1);

		preplantemp.add(test);
		preplantemp.add(test2);
		preplantemp.add(test3);

		return preplantemp;
	}

	@Override
	public List<PrePlanItemList> getPrePlanListData(String strBrand, String strState, String[] strCity, String strSpan)
	{
		List<PrePlanItemList> preplantemp = new ArrayList<PrePlanItemList>();
		for(String city: strCity) {
			PrePlanItemList prePlan_ItemList = getPrePlanItemList(strBrand,strState,city,strSpan);
			preplantemp.add(prePlan_ItemList);
		}
		
		return preplantemp;
	}
	
	public PrePlanItemList getPrePlanItemList(String strBrand, String strState, String strCity, String strSpan) 
	{
		PrePlanItemList prePlan_ItemList = new PrePlanItemList();

		//List<String> citySat=new ArrayList<String>(); 
		//String sat= planningDAO.getCitySaturation(strCity);
		String sat= planningDAO.getBrandCitySaturation(strBrand,strCity);
		//citySat.add(sat);
		
		//String strSaturation = citySat.get(0);
		//double saturation = Double.parseDouble((strSaturation.substring(0, strSaturation.length() - 1)));
		double saturation = Double.parseDouble(sat);
		//int saturation = Integer.parseInt(sat);
		int totHH = 0;
		double noOfPromo = 0;
		double noOfTeam = 0;
		// Get Data from Census Master - State/City/pop/hh
		List<CensusMaster> censusData = new ArrayList<CensusMaster>();
		censusData = planningDAO.getCensusData(strCity);
		Iterator<CensusMaster> censusItr = censusData.iterator();
		while (censusItr.hasNext()) {
			CensusMaster censustemp = new CensusMaster();
			censustemp = censusItr.next();

			String strTotpop = censustemp.getTot_pop();
			// Convert to Int for division
			long longTot_pop = Long.parseLong(strTotpop);
			totHH = (int) (longTot_pop / 4);
			prePlan_ItemList.setState(censustemp.getState());
			prePlan_ItemList.setCity(censustemp.getCity());
			prePlan_ItemList.setTot_pop(Integer.parseInt(strTotpop));
			prePlan_ItemList.setTot_hh(totHH);

		}

		// Save to Preplantemp variable

		// Get Data from Conversion Master -
		// Brand/Conversion/ContactsDone/AvgSales/NoPromotor/Team
		List<ConversionMaster> conversionData = new ArrayList<ConversionMaster>();
		conversionData = planningDAO.getConversionData(strBrand);
		Iterator<ConversionMaster> conversionItr = conversionData.iterator();
		while (conversionItr.hasNext()) {
			ConversionMaster conversiontemp = new ConversionMaster();
			conversiontemp = conversionItr.next();
			
			String avgSales = conversiontemp.getAvgsales();
			String conpercent = conversiontemp.getConpercent();
			double conversionPercentage = (Double.parseDouble(conpercent) /100);
			//Start - Calculate Saturation and Conversion 
			double satpercent;
			double conversion = 0;

			if (saturation > 0) {

				satpercent = saturation / 100;
				conversion = totHH * satpercent;
			} else {
				satpercent = saturation;

			}
			/*if (saturation > 0) {

				
				conversion = totHH * saturation;
			} else {
				//satpercent = saturation;

			}*/
			
			//conversion = round(conversion, 2);
			conversion=(int) Math.ceil(conversion);
			//End - Calculate Saturation and Conversion / Conversion Percentage
			
			
			// Start - Calculate Contacts
			double contactsDone = (conversion / conversionPercentage) ;
			contactsDone = Math.ceil(contactsDone);
			int contactsFinal=(int)contactsDone;
			int conversionFinal=(int)conversion;
			//Math.round(contactsDone);
			//contactsDone = round(contactsDone, 0);
			
			// End - Calculate Contacts

			// Start - Calculate No of Promotors and No of Team
			noOfPromo = (conversionFinal / Double.parseDouble((strSpan)));
			noOfPromo = noOfPromo / Double.parseDouble(avgSales);
			int promotors = (int) Math.ceil(noOfPromo);
			noOfTeam = noOfPromo / 12;
			Math.round(noOfTeam);
			
			int team = (int) Math.ceil(noOfTeam);
			// End - Calculate No of Promotors
			prePlan_ItemList.setBrand(strBrand);
			prePlan_ItemList.setConversion(conversionFinal);
			prePlan_ItemList.setContactDone(Integer.toString(contactsFinal));
			prePlan_ItemList.setAvgSales(Integer.parseInt(avgSales));
			prePlan_ItemList.setPromotorNum(promotors);
			prePlan_ItemList.setTeamNum(team);
			prePlan_ItemList.setSpan(Integer.parseInt(strSpan));

			}
		return prePlan_ItemList;
	}
	
	
	public List<PrePlanItemList> getPrePlanListData(String strBrand, String strState, String strCity, String strSpan) {

		List<PrePlanItemList> preplantemp = new ArrayList<PrePlanItemList>();
		PrePlanItemList prePlan_ItemList = new PrePlanItemList();

		List<String> citySat=new ArrayList<String>(); 
		String sat= planningDAO.getCitySaturation(strCity);
		citySat.add(sat);
		
		String strSaturation = citySat.get(0);
		double saturation = Double.parseDouble((strSaturation.substring(0, strSaturation.length() - 1)));
		int totHH = 0;
		double noOfPromo = 0;
		double noOfTeam = 0;
		// Get Data from Census Master - State/City/pop/hh
		List<CensusMaster> censusData = new ArrayList<CensusMaster>();
		censusData = planningDAO.getCensusData(strCity);
		Iterator<CensusMaster> censusItr = censusData.iterator();
		while (censusItr.hasNext()) {
			CensusMaster censustemp = new CensusMaster();
			censustemp = censusItr.next();

			String strTotpop = censustemp.getTot_pop();
			// Convert to Int for division
			long longTot_pop = Long.parseLong(strTotpop);
			totHH = (int) (longTot_pop / 4);
			prePlan_ItemList.setState(censustemp.getState());
			prePlan_ItemList.setCity(censustemp.getCity());
			prePlan_ItemList.setTot_pop(Integer.parseInt(strTotpop));
			prePlan_ItemList.setTot_hh(totHH);

		}

		// Save to Preplantemp variable

		// Get Data from Conversion Master -
		// Brand/Conversion/ContactsDone/AvgSales/NoPromotor/Team
		List<ConversionMaster> conversionData = new ArrayList<ConversionMaster>();
		conversionData = planningDAO.getConversionData(strBrand);
		Iterator<ConversionMaster> conversionItr = conversionData.iterator();
		while (conversionItr.hasNext()) {
			ConversionMaster conversiontemp = new ConversionMaster();
			conversiontemp = conversionItr.next();
			// String conversionper=conversiontemp.getConpercent();
			// String contactsDone=conversiontemp.getContactsdone();
			String avgSales = conversiontemp.getAvgsales();
			String conpercent = conversiontemp.getConpercent();
			// noOfPromo=Integer.parseInt(conversiontemp.getNo_of_promotors());

			double satpercent;
			double conversion = 0;

			if (saturation > 0) {

				satpercent = saturation / 100;
				conversion = totHH * satpercent;
			} else {
				satpercent = saturation;

			}
			conversion = round(conversion, 2);
			// Start - Calculate Contacts
			double contactsDone = (conversion / Double.parseDouble(conpercent)) / 100;
			// contactsDone=(contactsDone)/100;
			contactsDone = round(contactsDone, 2);
			// End - Calculate Contacts

			// Start - Calculate No of Promotors and No of Team
			noOfPromo = (conversion / Double.parseDouble((strSpan)));
			noOfPromo = noOfPromo / Double.parseDouble(avgSales);
			int promotors = (int) Math.ceil(noOfPromo);
			noOfTeam = noOfPromo / 12;
			Math.round(noOfTeam);
			int team = (int) Math.ceil(noOfTeam);

			// End - Calculate No of Promotors
			prePlan_ItemList.setBrand(strBrand);
			prePlan_ItemList.setConversion((int) conversion);
			prePlan_ItemList.setContactDone(Double.toString(contactsDone));
			prePlan_ItemList.setAvgSales(Integer.parseInt(avgSales));
			prePlan_ItemList.setPromotorNum(promotors);
			prePlan_ItemList.setTeamNum(team);

		}
		preplantemp.add(prePlan_ItemList);
		return preplantemp;
	}
	
	

	@Transactional
	@Override
	public int savePrePlanMaster(PrePlanItemList itemList, UserMaster user) {
		
		
		// Create Object for PrePlanMaster
		PrePlanMaster prePlanMaster = new PrePlanMaster();

		// Start -- PreplanMaster name from Brand+State+Month+Year
		String brandName = itemList.getBrand();
		String state = itemList.getState();

		LocalDate currentDate = LocalDate.now();
		Month m = currentDate.getMonth();
		
		int year = currentDate.getYear();

		String monthYear = m.toString().substring(0,3) + Integer.toString(year);
		String prePlanName = brandName+"_"+ state +"_"+ monthYear;
		// End -- PreplanMaster name from Brand+State+Month+Year

		// Start -- PreplanMaster created By from User
		String createdBy = user.getUsername();
		// End -- PreplanMaster created By from User

		// Start -- PreplanMaster createdDate Field
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss"); // your template here
		Date createdDate = new Date();
		java.sql.Date dateDB = new java.sql.Date(createdDate.getTime());
		// End -- PreplanMaster createdDate Field

		// Start -- PreplanMaster cityTown Field
		String citytown = itemList.getCity();
		// End -- PreplanMaster cityTown Field
		
		// Start -- PreplanMaster cityTown Field
		int span = itemList.getSpan();
		// End -- PreplanMaster cityTown Field

		// Start -- PreplanMaster Total Population Field
		int tot_pop = itemList.getTot_pop();
		// End -- PreplanMaster Total Population Field

		// Start -- PreplanMaster Total HouseHold Field
		int strTOT_HH = itemList.getTot_hh();
		// End -- PreplanMaster Total HouseHold Field

		// Start -- PreplanMaster conversion Field
		double strConversion = itemList.getConversion();
		// End -- PreplanMaster conversion Field

		// Start -- PreplanMaster contactsDone Field
		String strContactsDone = itemList.getContactDone();
		// End -- PreplanMaster contactsDone Field

		// Start -- PreplanMaster avgSales Field
		int avgSales = itemList.getAvgSales();
		// End -- PreplanMaster avgSales Field

		// Start -- PreplanMaster No Of Promotors Field
		int promotorNum = itemList.getPromotorNum();
		// End -- PreplanMaster No Of Promotors Field

		// Start -- PreplanMaster No of Team Field
		int teamNum = itemList.getTeamNum();
		// End -- PreplanMaster No of Team Field

		// Save data to prePlanMaster
		prePlanMaster.setPrePlanName(prePlanName);
		prePlanMaster.setCreatedBy(createdBy);
		prePlanMaster.setCreatedDate(dateDB);
		prePlanMaster.setIsActive(1);
		prePlanMaster.setBrand(brandName);
		prePlanMaster.setSpanDays(span);
		prePlanMaster.setState(state);
		prePlanMaster.setCitytown(citytown);
		prePlanMaster.setTot_pop(tot_pop);
		prePlanMaster.setTot_hh(strTOT_HH);
		prePlanMaster.setConversion(Double.toString(strConversion));
		prePlanMaster.setContactDone(strContactsDone);
		prePlanMaster.setAvgSales(avgSales);
		prePlanMaster.setPromotorNum(promotorNum);
		prePlanMaster.setTeamNum(teamNum);


		int result = planningDAO.persistPrePlanMaster(prePlanMaster);

		return result;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	@Override
	public List<String> getStateOfBrand(String strBrand) {
		
		return planningDAO.getStateList(strBrand);
	}

	

	

}
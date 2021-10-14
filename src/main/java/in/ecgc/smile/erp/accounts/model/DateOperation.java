package in.ecgc.smile.erp.accounts.model;

import java.time.Month;

public class DateOperation {
	
	public String currentMonth = null;
	public String prevMonth = null;
	public String nextMonth = null;

	
	public DateOperation(String month) {
		
		Month currMonth = null;
		if (month.equals("jan") || month.equals("1")) {
			currMonth = Month.JANUARY;
		} else if (month.equals("feb") || month.equals("2")){
			currMonth = Month.FEBRUARY;
		}else if (month.equals("mar") || month.equals("3")){
			currMonth = Month.MARCH;
		}else if (month.equals("apr") || month.equals("4")){
			currMonth = Month.APRIL;
		}else if (month.equals("may") || month.equals("5")){
			currMonth = Month.MAY;
		}else if (month.equals("jun") || month.equals("6")){
			currMonth = Month.JUNE;
		}else if (month.equals("jul") || month.equals("7")){
			currMonth = Month.JULY;
		}else if (month.equals("aug") || month.equals("8")){
			currMonth = Month.AUGUST;
		}else if (month.equals("sep") || month.equals("9")){
			currMonth = Month.SEPTEMBER;
		}else if (month.equals("oct") || month.equals("10")){
			currMonth = Month.OCTOBER;
		}else if (month.equals("nov") || month.equals("11")){
			currMonth = Month.NOVEMBER;
		}else if (month.equals("dec") || month.equals("12")){
			currMonth = Month.DECEMBER;
		}
		
		nextMonth = currMonth.minus(-1).toString().toLowerCase();
		nextMonth = nextMonth.substring(0, 3);
		prevMonth = currMonth.minus(1).toString().toLowerCase();
		prevMonth = prevMonth.substring(0, 3);
		currentMonth = currMonth.toString().toLowerCase();
		currentMonth = currentMonth.substring(0, 3);
	}
	
	public DateOperation(Integer month) {
		
		Month currMonth = null;
		if (month == 1) {
			currMonth = Month.JANUARY;
		} else if ( month == 2){
			currMonth = Month.FEBRUARY;
		}else if ( month == 3){
			currMonth = Month.MARCH;
		}else if ( month == 4){
			currMonth = Month.APRIL;
		}else if ( month == 5){
			currMonth = Month.MAY;
		}else if ( month == 6){
			currMonth = Month.JUNE;
		}else if ( month == 7){
			currMonth = Month.JULY;
		}else if ( month == 8){
			currMonth = Month.AUGUST;
		}else if ( month == 9){
			currMonth = Month.SEPTEMBER;
		}else if ( month == 10){
			currMonth = Month.OCTOBER;
		}else if ( month == 11){
			currMonth = Month.NOVEMBER;
		}else if ( month == 12){
			currMonth = Month.DECEMBER;
		}
		
		nextMonth = currMonth.minus(-1).toString().toLowerCase();
		nextMonth = nextMonth.substring(0, 3);
		prevMonth = currMonth.minus(1).toString().toLowerCase();
		prevMonth = prevMonth.substring(0, 3);
		currentMonth = currMonth.toString().toLowerCase();
		currentMonth = currentMonth.substring(0, 3);
	}
}

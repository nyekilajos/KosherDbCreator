package hu.bme.aut.amorg.nyekilajos.kosherfood.dbcreator;

public class NotKosherPairs {
	private int food_first_id;
	private int food_second_id;
	private String information;

	public int getFood_first_id() {
		return food_first_id;
	}

	public void setFood_first_id(int food_first_id) {
		this.food_first_id = food_first_id;
	}

	public int getFood_second_id() {
		return food_second_id;
	}

	public void setFood_second_id(int food_second_id) {
		this.food_second_id = food_second_id;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}

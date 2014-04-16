package hu.bme.aut.amorg.nyekilajos.kosherfood.dbcreator;

public class Foods {
	private int _id;
	private String name;
	private int is_kosher;
	private String information;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIs_kosher() {
		return is_kosher;
	}

	public void setIs_kosher(int is_kosher) {
		this.is_kosher = is_kosher;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

}

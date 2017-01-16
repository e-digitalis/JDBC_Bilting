
package jdbcBilting;


public class Person {
	private int id;
	private String name;
	private int birth;
	private String city;
	public int getId() {
		return id;
	}
        
        public Person(int id, String name, int birth, String city) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.city = city;
	}
        
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "[" + id + "/" + name + "/" + birth + "/" + city + "]";
	}
	
	

}

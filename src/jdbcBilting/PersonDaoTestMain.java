package jdbcBilting;


import java.time.chrono.JapaneseDate;
import java.util.List;

public class PersonDaoTestMain {
	

	public static void main(String[] args) {

		PersonDao pdao = new PersonDaoJDBCImpl();
		
		List<Person> list =  pdao.getByName("B");
		System.out.println(list);
                
        System.out.println("By city: "+ pdao.getByCity("P"));
		
		System.out.println("By ID: "+ pdao.getById(3));
		System.out.println("By birth: "+ pdao.getByBirth(2016, 2017));
		
		System.out.println(pdao.update("Lund", 3));
		System.out.println("By ID: "+ pdao.getById(3));
		
		pdao.create("Sune", 1949, "Skara");
		list =  pdao.getAll();
		System.out.println(list);
				
		list = pdao.getByName("Sune");
	
		for (Person p : list) {
			pdao.delete(p.getId());
		}
		
		list =  pdao.getAll();
		System.out.println(list);

	}

}



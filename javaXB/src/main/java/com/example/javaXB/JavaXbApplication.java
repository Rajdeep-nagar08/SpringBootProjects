package com.example.javaXB;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.StringReader;
import java.io.StringWriter;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class JavaXbApplication {

	public static void main(String[] args) throws JAXBException {

		SpringApplication.run(JavaXbApplication.class, args);

		String xml= "<Employee id=\"1234\"> " +
				"<MyName>Rajdeep</MyName> " +
				"<age>22</age> " +
				"<company id=\"56789\">Kanerika</company> " +
				"</Employee>";

		// XML to POJO
		//1. Create POJO or bind the schema and generate the classes
		//2. Create the JAXBContext object
		JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);

		// 3. Create the unmarshaller objects.
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		//4. Call the unmarsheller method
		Employee pojo= (Employee) unmarshaller.unmarshal(new StringReader(xml));

		//5. Use getter methods of POJO to access the data
		System.out.println(pojo.getName());
		System.out.println(pojo.getAge());
		System.out.println(pojo.getId());
		System.out.println(pojo.getCompany().getName());


		// POJO TO XML
		//1. Create POJO or bind the schema and generate the classes, DONE
        //2. Create the JAXBContext object, DONE
		//3. Create the Marshaller objects

		Marshaller marshaller= jaxbContext.createMarshaller();

		// 4. Create the context tree by using set methods
		pojo.setName("Gagandeep");
		pojo.setAge(24);
		pojo.setId(4321);
		Company company=new Company();
		company.setName("Kanerika Software Ltd");
		company.setId("5678910");
		pojo.setCompany(company);

		// 5. Call the marshal methods
		StringWriter sw= new StringWriter();
		marshaller.marshal(pojo,sw);
		System.out.println(sw.toString());

	}

}

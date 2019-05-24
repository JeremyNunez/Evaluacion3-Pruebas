package com.tecsup.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OwnerServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
	private OwnerService ownerService;

	/**
	 * 
	 */
	//@Test
	public void testFindById() {

		String NAME = "Eduardo";
		long ID = 3;
		Owner owner = null;
		
		try {
			owner = ownerService.findById(ID);
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		logger.info("" + owner);

		assertEquals(NAME, owner.getFirstname());

	}

	/**
	 * 
	 */
	//@Test
	public void testFindByFirstname() {

		String FIND_NAME = "Jean";
		int SIZE_EXPECTED = 6;

		List<Owner> owners = ownerService.findByFirstname(FIND_NAME);

		assertEquals(SIZE_EXPECTED, owners.size());
	}


	/**
	 * 
	 */
	//@Test
	public void testFindByLastname() {

		String LAST_NAME = "Black";
		int SIZE_EXPECTED = 7;

		List<Owner> owners = ownerService.findByLastname(LAST_NAME);

		assertEquals(SIZE_EXPECTED, owners.size());
	}
	
	
	/**
	 * 
	 */
	//@Test
	public void testFindByCity() {

		String CITY = "Waunakee";
		int SIZE_EXPECTED = 10;

		List<Owner> owners = ownerService.findByCity(CITY);

		assertEquals(SIZE_EXPECTED, owners.size());
	}

	/**
	 * 
	 */
	@Test
	public void testCreateOwnerandFound() {

		String OWNER_FIRSTNAME = "Osorio";
		String OWNER_LASTNAME = "Renato";
		String OWNER_ADDRESS = "";
		String OWNER_CITY = "Ate Vitarte";
		String OWNER_TELEPHONE = "";		

		Owner owner = new Owner(OWNER_FIRSTNAME, OWNER_LASTNAME,
				OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);
		
		owner = ownerService.create(owner);
		
		try {
			Owner ownerCreatedFound = ownerService.findById(owner.getId());
			logger.info("Owner id: "+ ownerCreatedFound.getId() + " owner created.");
		}catch (OwnerNotFoundException e) {
			logger.info("Owner no creado");
		}
		
		Iterable<Owner> ownersFound = ownerService.findAll();
		
		
		while(ownersFound.iterator().hasNext()) {
			try {
				Owner ownerInListFound = ownerService.findById(owner.getId());
				logger.info("Owner: "+ ownerInListFound.getId() + " existe.");
				break;
			}catch (OwnerNotFoundException e) {
				logger.info("Owner no existe");
			}
		}
				
	}
	
}

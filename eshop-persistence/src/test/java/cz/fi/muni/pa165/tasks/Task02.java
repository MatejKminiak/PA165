package cz.fi.muni.pa165.tasks;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Category;
import cz.fi.muni.pa165.entity.Product;

 
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class Task02 extends AbstractTestNGSpringContextTests {

	@PersistenceUnit
	private EntityManagerFactory emf;
        
        private Category electro;
        private Category kitchen;
        private Product flashlight;
        private Product kitchenRobot;
        private Product plate;
	
        @BeforeClass
        public  void generateTestData(){
            EntityManager entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            
            kitchen = new Category();
            electro = new Category();
            kitchen.setName("Kitchen");
            electro.setName("Electro");
            
            flashlight = new Product();
            kitchenRobot = new Product();
            plate = new Product();
            flashlight.setName("Flashlight");
            kitchenRobot.setName("Kitchen robot");
            plate.setName("Plate");
            
            flashlight.addCategory(electro);
            kitchenRobot.addCategory(electro);
            kitchenRobot.addCategory(kitchen);
            plate.addCategory(kitchen);

            
            entityManager.persist(kitchen);
            entityManager.persist(electro);
            entityManager.persist(flashlight);
            entityManager.persist(kitchenRobot);
            entityManager.persist(plate);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        
        
        @Test(expectedExceptions = ConstraintViolationException.class)
        public void testDoesntSaveNullName() {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Product p = new Product();
            em.persist(p);
            em.getTransaction().commit();
            em.close();
        }

       @Test
       public void electroTest() {
           EntityManager entityManager = emf.createEntityManager();
           Category found = entityManager.find(Category.class, electro.getId());
           assertContainsProductWithName(found.getProducts(), "Flashlight");
       }

       @Test
       public void kitchenTest() {
           EntityManager entityManager = emf.createEntityManager();
           Category found = entityManager.find(Category.class, kitchen.getId());
           assertContainsProductWithName(found.getProducts(), "Kitchen robot");
           assertContainsProductWithName(found.getProducts(), "Plate");
       }

       @Test
       public void flashlightTest() {
           EntityManager entityManager = emf.createEntityManager();
           Product found = entityManager.find(Product.class, flashlight.getId());
           assertContainsCategoryWithName(found.getCategories(), "Electro");
       }

       @Test
       public void kitchenRobotTest() {
           EntityManager entityManager = emf.createEntityManager();
           Product found = entityManager.find(Product.class, kitchenRobot.getId());
           assertContainsCategoryWithName(found.getCategories(), "Electro");
           assertContainsCategoryWithName(found.getCategories(), "Kitchen");
       }

       @Test
       public void plateTest() {
           EntityManager entityManager = emf.createEntityManager();
           Product found = entityManager.find(Product.class, plate.getId());
           assertContainsCategoryWithName(found.getCategories(), "Kitchen");
       }    
	
        
	private void assertContainsCategoryWithName(Set<Category> categories,
			String expectedCategoryName) {
		for(Category cat: categories){
			if (cat.getName().equals(expectedCategoryName))
				return;
		}
			
		Assert.fail("Couldn't find category "+ expectedCategoryName+ " in collection "+categories);
	}
	private void assertContainsProductWithName(Set<Product> products,
			String expectedProductName) {
		
		for(Product prod: products){
			if (prod.getName().equals(expectedProductName))
				return;
		}
			
		Assert.fail("Couldn't find product "+ expectedProductName+ " in collection "+products);
	}
        
       
}

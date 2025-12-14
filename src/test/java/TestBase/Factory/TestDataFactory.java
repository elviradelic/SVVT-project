package TestBase.Factory;

public class TestDataFactory {

    public static TestUser validTestUser() {
        return  TestUser.builder().build();

    }


    public static TestUser invalidEmailUser() {
        return  TestUser.builder().setEmail("test@example.com").build();

    }

    // Product test data
    public static class Products {
        public static final String TV_CATEGORY = "TELEVIZORI";
        public static final String GAMING_CATEGORY = "GAMING";
        public static final String LAPTOP_CATEGORY = "LAPTOPI";

        public static String[] searchTerms() {
            return new String[]{"iPhone", "Samsung", "PlayStation", "LG", "Sony"};
        }
    }




}

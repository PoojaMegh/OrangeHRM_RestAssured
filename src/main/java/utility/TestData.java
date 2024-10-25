package utility;

import com.github.javafaker.Faker;

public class TestData {

    //Skill page test data
    public static String getSkillName(){
        Faker faker = new Faker();
        return faker.job().position();
    }

    public static String getSkillDescription(){
        Faker faker = new Faker();
        return faker.book().genre();
    }

    //Education page test data
    public static String getEducationName(){
        Faker faker = new Faker();
        return faker.educator().course();
    }

    //Vendor page test data
    public static String getVendorName(){
        Faker faker = new Faker();
        return faker.artist().name();
    }

    public static String getEmail(){
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String getAddress(){
        Faker faker = new Faker();
        return faker.address().fullAddress();
    }

    public static String getWebsite(){
        //Faker faker = new Faker();
        // Generate random components for the URL
        String email = getEmail();

        // Construct the URL
        String customUrl = String.format("https://%s", email);
        return customUrl;
    }

    public static String getContactNumber(){
        Faker faker = new Faker();
        return faker.phoneNumber().cellPhone();
    }
}

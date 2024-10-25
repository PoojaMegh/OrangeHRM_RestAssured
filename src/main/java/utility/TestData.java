package utility;

import com.github.javafaker.Faker;

public class TestData {

    public static String getSkillName(){
        Faker faker = new Faker();
        return faker.job().position();
    }

    public static String getSkillDescription(){
        Faker faker = new Faker();
        return faker.book().genre();
    }

    public static String getEducationName(){
        Faker faker = new Faker();
        return faker.educator().course();
    }
}

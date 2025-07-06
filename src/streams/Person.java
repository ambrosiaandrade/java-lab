package src.streams;

public class Person {

    private String name;
    private int age;
    private String favoriteColor;
    private char gender;

    public Person() {
        this.name = "Unknown";
        this.age = 0;
        this.favoriteColor = "None";
        this.gender = 'U';
    }

    public Person(String name, int age, String favoriteColor, char gender) {
        this.name = name;
        this.age = age;
        this.favoriteColor = favoriteColor;
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public char getGender() {
        return gender;
    }

    public String toString() {
        return "Person{" +
                "age=" + age + '\'' +
                ", name='" + name + '\'' +
                ", favoriteColor='" + favoriteColor + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

}

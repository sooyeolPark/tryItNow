package study.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("psy");

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        helloLombok.setAge(100);
        System.out.println("helloLombok = " + helloLombok);
    }
}

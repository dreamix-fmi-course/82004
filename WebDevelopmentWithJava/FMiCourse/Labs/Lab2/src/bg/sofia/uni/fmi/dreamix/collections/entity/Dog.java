package bg.sofia.uni.fmi.dreamix.collections.entity;

import java.util.Comparator;
import java.util.Objects;

public class Dog {
    private int age;
    private String breed;
    private String name;
    private double weight;

    public Dog(String name, int age, String breed, double weight) {
        this.age = age;
        this.breed = breed;
        this.name = name;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return age == dog.age && Objects.equals(breed, dog.breed) && Objects.equals(name, dog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, breed, name);
    }

    public double getWeight() {
        return weight;
    }
}

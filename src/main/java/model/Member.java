package model;

import java.util.Objects;

public class Member {
    private String name;
    private int age;
    private long memberId; // Unique ID "
    private String email;

    public Member(String name, int age, String email, long memberId) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Member member)) return false;
        return age == member.age && Objects.equals(name, member.name) && Objects.equals(memberId, member.memberId) && Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, memberId, email);
    }

    @Override
    public String toString() {
        return "Member name= " + name + ", age=" + age + ", memberId= " + memberId + ", email=" + email;
    }
}

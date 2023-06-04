package model;

public class Student {
    public String fname;
    public String lname;
    public int age;
    public String cin;
    public String class_grade;
    public String email;
    public String infos;
    public String imagePath;

    public Student() {
        this.fname = "";
        this.lname = "";
        this.age = 0;
        this.cin = "";
        this.class_grade = "";
        this.email = "";
        this.infos = "";
    }

    public Student(String fname, String lname, int age, String cin, String class_grade, String email, String infos,String imagePath) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.cin = cin;
        this.class_grade = class_grade;
        this.email = email;
        this.infos = infos;
        this.imagePath = imagePath;
    }

    public String getFname() {
        return this.fname;
    }

    public int getAge() {
        return this.age;
    }

    public String getCin() {
        return this.cin;
    }

    public String getLname() {
        return this.lname;
    }

    public String getClass_grade() {
        return this.class_grade;
    }

    public String getEmail() {
        return this.email;
    }

    public String getInfos() {
        return this.infos;
    }
    public String getImagepath() {
        return this.imagePath;
    }

}

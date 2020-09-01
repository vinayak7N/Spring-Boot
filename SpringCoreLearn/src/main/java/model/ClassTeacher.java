package model;

import java.util.Set;

public class ClassTeacher {

    private int id;
    private String name;
    Set<Student> students;

    public ClassTeacher(){}

    public ClassTeacher(int id,String name,Set<Student> students){
        this.id = id;
        this.name =name;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ClassTeacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}

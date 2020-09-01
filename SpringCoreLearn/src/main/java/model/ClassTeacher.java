package model;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Set;

public class ClassTeacher implements InitializingBean, DisposableBean {

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

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Init method called....");
        this.name="Helen";
        System.out.println("Name updated inside init...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy method called....");
    }
}

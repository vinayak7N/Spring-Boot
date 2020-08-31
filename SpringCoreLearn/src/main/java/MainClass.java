import model.Student;
import model.Vehicle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {


    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Vehicle vehicle = (Vehicle) context.getBean("bike");
        vehicle.drive();

        /*Student student = (Student) context.getBean("student");
        System.out.println(student);
        Student student2 = (Student) context.getBean("student2");
        System.out.println(student2);*/

        Student student1 = context.getBean("student1",Student.class);
        System.out.println(student1);
    }
}

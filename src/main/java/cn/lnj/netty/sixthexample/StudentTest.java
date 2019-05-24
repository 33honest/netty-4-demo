package cn.lnj.netty.sixthexample;

public class StudentTest {

    public static void main(String[] args) {

        DateInfo.Student student = DateInfo.Student.newBuilder().setName("Nathan").setAge(34).setAddress("上海").build();

        System.out.println(student);
        System.out.println(student.getName());
        System.out.println(student.getAge());
        System.out.println(student.getAddress());
    }

}

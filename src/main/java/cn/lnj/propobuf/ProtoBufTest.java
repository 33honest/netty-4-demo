package cn.lnj.propobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Date;

public class ProtoBufTest {

    public static void main(String[] args) {

        try {
            DateInfo.Student student = DateInfo.Student.newBuilder().setName("Nathan").setAge(35).setAddress("中国江苏").build();
            byte[] studenByteArray = student.toByteArray();

            DateInfo.Student stu = DateInfo.Student.parseFrom(studenByteArray);
            System.out.println(stu);
            System.out.println(stu.hasName());
            System.out.println(stu.getName());
            System.out.println(stu.getAddress());
            System.out.println(stu.getAge());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }

}

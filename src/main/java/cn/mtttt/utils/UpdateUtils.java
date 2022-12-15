package cn.mtttt.utils;

import cn.mtttt.bean.Course;
import cn.mtttt.bean.CourseScore;
import cn.mtttt.bean.Profession;
import cn.mtttt.bean.SchoolRoll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jasypt.encryption.StringEncryptor;

import java.util.ArrayList;

public class UpdateUtils<T> {
    static MyJasyptUtils myJasyptUtils;
    static {
        myJasyptUtils = new MyJasyptUtils();
    }
    public ArrayList<T> handle(ArrayList<T> objects) {
        if(objects.size() == 0){
            return null;
        }
        if(objects.get(0).getClass().equals(SchoolRoll.class)){
            StringEncryptor encryptor = myJasyptUtils.getEncryptor();
            for (int i = 0;i<objects.size();i++) {
                SchoolRoll schoolRoll = (SchoolRoll) objects.get(i);
                schoolRoll.setPassword(encryptor.encrypt(schoolRoll.getPassword()));
                schoolRoll.setSno(encryptor.encrypt(schoolRoll.getSno()));
                schoolRoll.setName(encryptor.encrypt(schoolRoll.getName()));
                schoolRoll.setProfessionId(encryptor.encrypt(schoolRoll.getProfessionId()));
                schoolRoll.setSex(encryptor.encrypt(schoolRoll.getSex()));
                objects.set(i, (T) schoolRoll);
            }
        }else if(objects.get(0).getClass().equals(Course.class)){
            StringEncryptor encryptor = myJasyptUtils.getEncryptor();
            for (int i = 0;i<objects.size();i++) {
                Course course = (Course) objects.get(i);
                course.setName(encryptor.encrypt(course.getName()));
                course.setCno(encryptor.encrypt(course.getCno()));
                objects.set(i, (T) course);
            }
        }else if(objects.get(0).getClass().equals(Profession.class)){
            StringEncryptor encryptor = myJasyptUtils.getEncryptor();
            for (int i = 0;i<objects.size();i++) {
                Profession profession = (Profession) objects.get(i);
                profession.setName(encryptor.encrypt(profession.getName()));
                profession.setPno(encryptor.encrypt(profession.getPno()));
                objects.set(i, (T) profession);
            }
        }else if(objects.get(0).getClass().equals(CourseScore.class)){
            StringEncryptor encryptor = myJasyptUtils.getEncryptor();
            for (int i = 0;i<objects.size();i++) {
                CourseScore courseScore = (CourseScore) objects.get(i);
                courseScore.setCourseId(encryptor.encrypt(courseScore.getCourseId()));
                courseScore.setStudentId(encryptor.encrypt(courseScore.getStudentId()));
                objects.set(i, (T) courseScore);
            }
        }
        return objects;
    }
}

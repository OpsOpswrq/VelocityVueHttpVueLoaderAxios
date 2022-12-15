package cn.mtttt.mapper.origin;

import cn.mtttt.bean.Course;
import cn.mtttt.bean.CourseScore;
import cn.mtttt.bean.Profession;
import cn.mtttt.bean.SchoolRoll;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface OriginMapper {
    @Insert("replace into profession(pno,name) values(#{Pno},#{Name});")
    void insertProfession(Profession profession);
    @Insert("replace into schoolroll(sno,name,sex,professionid,password,auth) values(#{Sno},#{Name},#{Sex},#{ProfessionId},#{Password},#{Auth});")
    void insertSchoolRoll(SchoolRoll schoolRoll);
    @Insert("replace into course(cno,name) values(#{Cno},#{Name});")
    void insertCourse(Course course);
    @Insert("replace into coursescore(studentid,courseid,score) values(#{StudentId},#{CourseId},#{Score});")
    void insertCourseScore(CourseScore courseScore);
    @Select("select * from schoolroll where sno=#{Sno}")
    SchoolRoll getSchoolRoll(String Sno);
    @Select("select * from schoolroll limit #{start},1000;")
    ArrayList<SchoolRoll> getSchoolRollOffsetOrigin(int start);
    @Select("select * from course limit #{start},100;")
    ArrayList<Course> getCourseOffsetOrigin(int start);
    @Select("select * from profession limit #{start},100;")
    ArrayList<Profession> getProfessionOffsetOrigin(int start);
    @Select("select * from origin.coursescore limit #{start},2000;")
    ArrayList<CourseScore> getCourseScoreOrigin(int start);
}

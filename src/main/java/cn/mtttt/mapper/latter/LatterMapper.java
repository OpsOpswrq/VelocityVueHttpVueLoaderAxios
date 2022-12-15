package cn.mtttt.mapper.latter;

import cn.mtttt.bean.Course;
import cn.mtttt.bean.CourseScore;
import cn.mtttt.bean.Profession;
import cn.mtttt.bean.SchoolRoll;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface LatterMapper {

    @Select("select * from latter.schoolroll limit #{start},100;")
    ArrayList<SchoolRoll> getSchoolRollOffsetLatter100(int start);
    @Select("select * from latter.schoolroll;")
    ArrayList<SchoolRoll> getSchoolRollOffsetLatter();
    @Select("select * from latter.course limit #{start},100;")
    ArrayList<Course> getCourseOffsetLatter(int start);
    @Select("select * from latter.course;")
    ArrayList<Course> getCourseOffsetLatterFull();
    @Select("select * from latter.profession limit #{start},100;")
    ArrayList<Profession> getProfessionOffsetLatter(int start);
    @Select("select * from latter.profession;")
    ArrayList<Profession> getProfessionOffsetLatterFull();
    @Insert("replace into latter.schoolroll(sno,name,sex,professionid,password,auth) values(#{Sno},#{Name},#{Sex},#{ProfessionId},#{Password},#{Auth});")
    void updateSchoolRollLatter(SchoolRoll schoolRoll);
    @Insert("replace into latter.profession(pno,name) values(#{Pno},#{Name});")
    void updateProfessionLatter(Profession profession);
    @Insert("replace into latter.course(cno,name) values(#{Cno},#{Name});")
    void updateCourseLatter(Course course);
    @Select("select * from latter.coursescore limit #{start},100;")
    ArrayList<CourseScore> getCourseScoreLatter100(int start);
    @Select("select * from latter.coursescore;")
    ArrayList<CourseScore> getCourseScoreLatterFull();
    @Insert("replace into latter.coursescore(studentid,courseid,score) values(#{StudentId},#{CourseId},#{Score});")
    void updateCourseScoreLatter(CourseScore courseScore);
}

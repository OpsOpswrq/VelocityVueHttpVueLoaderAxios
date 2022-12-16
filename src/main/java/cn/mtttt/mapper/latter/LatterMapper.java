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

    @Select("select * from schoolroll limit #{start},100;")
    ArrayList<SchoolRoll> getSchoolRollOffsetLatter100(int start);
    @Select("select * from schoolroll;")
    ArrayList<SchoolRoll> getSchoolRollOffsetLatter();
    @Select("select * from course limit #{start},100;")
    ArrayList<Course> getCourseOffsetLatter(int start);
    @Select("select * from course;")
    ArrayList<Course> getCourseOffsetLatterFull();
    @Select("select * from profession limit #{start},100;")
    ArrayList<Profession> getProfessionOffsetLatter(int start);
    @Select("select * from profession;")
    ArrayList<Profession> getProfessionOffsetLatterFull();
    @Insert("replace into schoolroll(sno,name,sex,professionid,password,auth,version) values(#{Sno},#{Name},#{Sex},#{ProfessionId},#{Password},#{Auth},#{Version});")
    void updateSchoolRollLatter(SchoolRoll schoolRoll);
    @Insert("replace into profession(pno,name,version) values(#{Pno},#{Name},#{Version});")
    void updateProfessionLatter(Profession profession);
    @Insert("replace into course(cno,name,version) values(#{Cno},#{Name},#{Version});")
    void updateCourseLatter(Course course);
    @Select("select * from coursescore limit #{start},100;")
    ArrayList<CourseScore> getCourseScoreLatter100(int start);
    @Select("select * from coursescore;")
    ArrayList<CourseScore> getCourseScoreLatterFull();
    @Insert("replace into coursescore(studentid,courseid,score,version) values(#{StudentId},#{CourseId},#{Score},#{Version});")
    void updateCourseScoreLatter(CourseScore courseScore);
}

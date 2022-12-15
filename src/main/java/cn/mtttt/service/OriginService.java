package cn.mtttt.service;

import cn.mtttt.bean.Course;
import cn.mtttt.bean.CourseScore;
import cn.mtttt.bean.Profession;
import cn.mtttt.bean.SchoolRoll;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface OriginService {
    void insertSchoolRoll(SchoolRoll schoolRoll);
    void insertProfession(Profession profession);
    void insertCourse(Course course);
    void insertCourseScore(CourseScore courseScore);
    SchoolRoll getSchoolRoll(String Sno);
    ArrayList<SchoolRoll> getSchoolRollOffsetOrigin(int start);
    ArrayList<Course> getCourseOffsetOrigin(int start);
    ArrayList<Profession> getProfessionOffsetOrigin(int start);
    ArrayList<CourseScore> getCourseScoreOrigin(int start);
}

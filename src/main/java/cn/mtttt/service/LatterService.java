package cn.mtttt.service;

import cn.mtttt.bean.Course;
import cn.mtttt.bean.CourseScore;
import cn.mtttt.bean.Profession;
import cn.mtttt.bean.SchoolRoll;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface LatterService {
    void updateSchoolRollLatter(SchoolRoll schoolRoll);
    void updateProfessionLatter(Profession profession);
    void updateCourseLatter(Course course);
    void updateCourseScoreLatter(CourseScore courseScore);
    ArrayList<SchoolRoll> getSchoolRollOffsetLatter100(int start);
    ArrayList<CourseScore> getCourseScoreLatter100(int start);
    ArrayList<Profession> getProfessionOffsetLatter(int start);
    ArrayList<Course> getCourseOffsetLatter(int start);
    ArrayList<SchoolRoll> getSchoolRollOffsetLatter();
    ArrayList<Course> getCourseOffsetLatterFull();
    ArrayList<Profession> getProfessionOffsetLatterFull();
    ArrayList<CourseScore> getCourseScoreLatterFull();
}

package cn.mtttt.service.impl;

import cn.mtttt.bean.Course;
import cn.mtttt.bean.CourseScore;
import cn.mtttt.bean.Profession;
import cn.mtttt.bean.SchoolRoll;
import cn.mtttt.mapper.origin.OriginMapper;
import cn.mtttt.service.OriginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class OriginServiceImpl implements OriginService {
    @Resource
    private OriginMapper originMapper;
    @Override
    public void insertSchoolRoll(SchoolRoll schoolRoll) {
        originMapper.insertSchoolRoll(schoolRoll);
    }

    @Override
    public void insertProfession(Profession profession) {
        originMapper.insertProfession(profession);
    }

    @Override
    public void insertCourse(Course course) {
        originMapper.insertCourse(course);
    }

    @Override
    public void insertCourseScore(CourseScore courseScore) {
        originMapper.insertCourseScore(courseScore);
    }

    @Override
    public SchoolRoll getSchoolRoll(String Sno) {
        return originMapper.getSchoolRoll(Sno);
    }

    @Override
    public ArrayList<SchoolRoll> getSchoolRollOffsetOrigin(int start) {
        return originMapper.getSchoolRollOffsetOrigin(start);
    }

    @Override
    public ArrayList<Course> getCourseOffsetOrigin(int start) {
        return originMapper.getCourseOffsetOrigin(start);
    }

    @Override
    public ArrayList<Profession> getProfessionOffsetOrigin(int start) {
        return originMapper.getProfessionOffsetOrigin(start);
    }

    @Override
    public ArrayList<CourseScore> getCourseScoreOrigin(int start) {
        return originMapper.getCourseScoreOrigin(start);
    }
}

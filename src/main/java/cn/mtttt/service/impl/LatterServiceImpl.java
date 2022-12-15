package cn.mtttt.service.impl;

import cn.mtttt.bean.Course;
import cn.mtttt.bean.CourseScore;
import cn.mtttt.bean.Profession;
import cn.mtttt.bean.SchoolRoll;
import cn.mtttt.mapper.latter.LatterMapper;
import cn.mtttt.service.LatterService;
import cn.mtttt.utils.MyJasyptUtils;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class LatterServiceImpl implements LatterService {
    @Resource
    LatterMapper latterMapper;

    @Override
    public void updateSchoolRollLatter(SchoolRoll schoolRoll) {
        latterMapper.updateSchoolRollLatter(schoolRoll);
    }

    @Override
    public void updateProfessionLatter(Profession profession) {
        latterMapper.updateProfessionLatter(profession);
    }

    @Override
    public void updateCourseLatter(Course course) {
        latterMapper.updateCourseLatter(course);
    }

    @Override
    public void updateCourseScoreLatter(CourseScore courseScore) {
        latterMapper.updateCourseScoreLatter(courseScore);
    }

    @Override
    public ArrayList<SchoolRoll> getSchoolRollOffsetLatter100(int start) {
        ArrayList<SchoolRoll> schoolRollOffsetOrigin100 = latterMapper.getSchoolRollOffsetLatter100(start);
        return schoolRollOffsetOrigin100;
    }

    @Override
    public ArrayList<CourseScore> getCourseScoreLatter100(int start) {
        ArrayList<CourseScore> courseScoreOrigin100 = latterMapper.getCourseScoreLatter100(start);
        return courseScoreOrigin100;
    }

    @Override
    public ArrayList<Profession> getProfessionOffsetLatter(int start) {
        return latterMapper.getProfessionOffsetLatter(start);
    }

    @Override
    public ArrayList<Course> getCourseOffsetLatter(int start) {
        return latterMapper.getCourseOffsetLatter(start);
    }

    @Override
    public ArrayList<SchoolRoll> getSchoolRollOffsetLatter() {
        return latterMapper.getSchoolRollOffsetLatter();
    }

    @Override
    public ArrayList<Course> getCourseOffsetLatterFull() {
        return latterMapper.getCourseOffsetLatterFull();
    }

    @Override
    public ArrayList<Profession> getProfessionOffsetLatterFull() {
        return latterMapper.getProfessionOffsetLatterFull();
    }

    @Override
    public ArrayList<CourseScore> getCourseScoreLatterFull() {
        return latterMapper.getCourseScoreLatterFull();
    }
}

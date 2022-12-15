package cn.mtttt.service;

import cn.mtttt.bean.Course;
import cn.mtttt.bean.CourseScore;
import cn.mtttt.bean.Profession;
import cn.mtttt.bean.SchoolRoll;
import cn.mtttt.utils.UpdateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AsyncTaskService {
    @Autowired
    OriginService originService;
    @Autowired
    LatterService latterService;

    @Async
    public void executeUpdateSchoolRoll(int i){
        UpdateUtils<SchoolRoll> updateUtils = new UpdateUtils<SchoolRoll>();
        ArrayList<SchoolRoll> schoolRollOffsetOrigin = originService.getSchoolRollOffsetOrigin(i * 1000);
        if(schoolRollOffsetOrigin.size() > 0){
            ArrayList<SchoolRoll> arrayList = updateUtils.handle(schoolRollOffsetOrigin);
            for (Object object : arrayList) {
                latterService.updateSchoolRollLatter((SchoolRoll) object);
            }
        }
    }

    @Async
    public void executeUpdateProfession(int i){
        UpdateUtils<Profession> updateUtils = new UpdateUtils<Profession>();
        ArrayList<Profession> professionOffsetOrigin = originService.getProfessionOffsetOrigin(i * 100);
        if(professionOffsetOrigin.size() > 0){
            ArrayList<Profession> arrayList = updateUtils.handle(professionOffsetOrigin);
            for (Object object : arrayList) {
                latterService.updateProfessionLatter((Profession) object);
            }
        }
    }

    @Async
    public void executeUpdateCourse(int i){
        UpdateUtils<Course> updateUtils = new UpdateUtils<Course>();
        ArrayList<Course> courseOffsetOrigin = originService.getCourseOffsetOrigin(i * 100);
        if(courseOffsetOrigin.size() > 0){
            ArrayList<Course> arrayList = updateUtils.handle(courseOffsetOrigin);
            for (Object object : arrayList) {
                latterService.updateCourseLatter((Course) object);
            }
        }
    }

    @Async
    public void executeUpdateCourseScore(int i){
        UpdateUtils<CourseScore> updateUtils = new UpdateUtils<CourseScore>();
        ArrayList<CourseScore> courseScoreOrigin = originService.getCourseScoreOrigin(i * 2000);
        if(courseScoreOrigin.size() > 0){
            ArrayList<CourseScore> arrayList = updateUtils.handle(courseScoreOrigin);
            for (CourseScore object : arrayList) {
                latterService.updateCourseScoreLatter(object);
            }
        }
    }
}

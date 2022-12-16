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
            int version_pre = schoolRollOffsetOrigin.get(0).getVersion();
            ArrayList<SchoolRoll> arrayList = updateUtils.handle(schoolRollOffsetOrigin);
            int version_post = arrayList.get(0).getVersion();
            if(version_pre == version_post - 1){
                for (Object object : arrayList) {
                    latterService.updateSchoolRollLatter((SchoolRoll) object);
                }
            }else{
                throw new RuntimeException("数据已经被操作过了，无法更新数据");
            }
        }
    }

    @Async
    public void executeUpdateProfession(int i){
        UpdateUtils<Profession> updateUtils = new UpdateUtils<Profession>();
        ArrayList<Profession> professionOffsetOrigin = originService.getProfessionOffsetOrigin(i * 100);
        if(professionOffsetOrigin.size() > 0){
            int version_pre = professionOffsetOrigin.get(0).getVersion();
            ArrayList<Profession> arrayList = updateUtils.handle(professionOffsetOrigin);
            int version_post = arrayList.get(0).getVersion();
            if(version_post - 1 == version_pre){
                for (Object object : arrayList) {
                    latterService.updateProfessionLatter((Profession) object);
                }
            }else{
                throw new RuntimeException("数据已经被操作过了，无法更新数据");
            }
        }
    }

    @Async
    public void executeUpdateCourse(int i){
        UpdateUtils<Course> updateUtils = new UpdateUtils<Course>();
        ArrayList<Course> courseOffsetOrigin = originService.getCourseOffsetOrigin(i * 100);
        if(courseOffsetOrigin.size() > 0){
            int version_pre = courseOffsetOrigin.get(0).getVersion();
            ArrayList<Course> arrayList = updateUtils.handle(courseOffsetOrigin);
            int version_post = arrayList.get(0).getVersion();
            if(version_post - 1 == version_pre){
                for (Object object : arrayList) {
                    latterService.updateCourseLatter((Course) object);
                }
            }else{
                throw new RuntimeException("数据已经被操作过了，无法更新数据");
            }
        }
    }

    @Async
    public void executeUpdateCourseScore(int i){
        UpdateUtils<CourseScore> updateUtils = new UpdateUtils<CourseScore>();
        ArrayList<CourseScore> courseScoreOrigin = originService.getCourseScoreOrigin(i * 2000);
        if(courseScoreOrigin.size() > 0){
            int version_pre = courseScoreOrigin.get(0).getVersion();
            ArrayList<CourseScore> arrayList = updateUtils.handle(courseScoreOrigin);
            int version_post = arrayList.get(0).getVersion();
            if(version_post - 1 == version_pre){
                for (CourseScore object : arrayList) {
                    latterService.updateCourseScoreLatter(object);
                }
            }else{
                throw new RuntimeException("数据已经被操作过了，无法更新数据");
            }
        }
    }
}

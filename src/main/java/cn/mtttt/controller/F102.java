package cn.mtttt.controller;

import cn.mtttt.bean.*;
import cn.mtttt.service.AsyncTaskService;
import cn.mtttt.service.LatterService;
import cn.mtttt.service.OriginService;
import cn.mtttt.utils.AuthUtils;
import cn.mtttt.utils.ExportExcelUtils;
import cn.mtttt.utils.MyJasyptUtils;
import cn.mtttt.utils.VHelper;
import com.alibaba.fastjson2.JSON;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class F102 {
    @Autowired
    LatterService latterService;
    @Autowired
    OriginService originService;
    @Autowired
    AsyncTaskService asyncTaskService;
    static MyJasyptUtils myJasyptUtils;
    static{
        myJasyptUtils = new MyJasyptUtils();
    }
    static int cnt = 0;
    @RequestMapping(value = "/permitMain",method = RequestMethod.GET)
    public String permitMain(HttpServletRequest request){
        Cookie[] cookies = request.getCookies() == null ? new Cookie[]{} : request.getCookies();
        if(cookies.length != 0){
            boolean isPermit = true;
            String sno = cookies[0].getValue();
            StringEncryptor encryptor = myJasyptUtils.getEncryptor();
            String decrypt = "";
            try{
                decrypt = encryptor.decrypt(sno);
            }catch (EncryptionOperationNotPossibleException e){
                isPermit = false;
                System.out.println(e);
            }
            if(isPermit){
                SchoolRoll schoolRoll = originService.getSchoolRoll(decrypt);
                if(schoolRoll != null){
                    return JSON.toJSONString(new ResultRes(200,true));
                }else{
                    return JSON.toJSONString(new ResultRes(403,false));
                }
            }else{
                return JSON.toJSONString(new ResultRes(403,false));
            }
        }else{
            return JSON.toJSONString(new ResultRes(403,false));
        }
    }
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String mainHome(){
        VHelper vHelper = new VHelper("/F102/index.html");
        return vHelper.render();
    }
    @RequestMapping(value = "/serialize",method = RequestMethod.POST)
    public void serialize(){
        if(cnt == 0){
            int i = 0;
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList<SchoolRoll> schoolRolls = new ArrayList<>();
            ArrayList<Course> courses = new ArrayList<>();
            ArrayList<CourseScore> courseScores = new ArrayList<>();
            ArrayList<Profession> professions = new ArrayList<>();
            do{
                courses = originService.getCourseOffsetOrigin(i * 100);
                asyncTaskService.executeUpdateCourse(i);
                i++;
            }while (courses.size() > 0);
            i = 0;
            do{
                professions = originService.getProfessionOffsetOrigin(i * 100);
                asyncTaskService.executeUpdateProfession(i);
                i++;
            }while (professions.size() > 0);
            i = 0;
            do{
                schoolRolls = originService.getSchoolRollOffsetOrigin(i * 1000);
                asyncTaskService.executeUpdateSchoolRoll(i);
                i++;
            }while (schoolRolls.size() >0);
            do{
                courseScores = originService.getCourseScoreOrigin(i * 2000);
                asyncTaskService.executeUpdateCourseScore(i);
                i++;
            }while (courseScores.size()>0);
            long l1 = System.currentTimeMillis();
            System.out.println(l1-currentTimeMillis);
            cnt++;
        }
    }
@RequestMapping(value = "/permitDownload",method = RequestMethod.POST)
public String permit(HttpServletRequest request){
    StringEncryptor encryptor = myJasyptUtils.getEncryptor();
    String sno = encryptor.decrypt(request.getCookies()[0].getValue());
    SchoolRoll schoolRoll = originService.getSchoolRoll(sno);
    int auth = schoolRoll.getAuth();
    ArrayList<String> roles = AuthUtils.getRoles(auth);
    if(roles.contains("vip3")){
        return JSON.toJSONString(new ResultRes(200,true));
    }else{
        return JSON.toJSONString(new ResultRes(403,false));
    }
}
    /*支持导出xlsx*/
    @RequestMapping(value = "/downloadSchoolRoll",method = RequestMethod.POST)
    public void downloadSchoolRoll(HttpServletRequest request, HttpServletResponse response){
        String sheetName = "SchoolRoll";
        String[] title = {"学号","姓名","账号密码","性别","专业代码","权限"};
        List<SchoolRoll> schoolRolls = latterService.getSchoolRollOffsetLatter();
        ExportExcelUtils<SchoolRoll> exportExcelUtils = new ExportExcelUtils<SchoolRoll>();
        exportExcelUtils.ExportThing(response,sheetName,title,schoolRolls);
    }
    @RequestMapping(value = "/downloadCourse",method = RequestMethod.POST)
    public void downloadCourse(HttpServletRequest request,HttpServletResponse response){
        String sheetName = "Course";
        String[] title = {"课程代码","课程名"};
        List<Course> courses = latterService.getCourseOffsetLatterFull();
        ExportExcelUtils<Course> exportExcelUtils = new ExportExcelUtils<Course>();
        exportExcelUtils.ExportThing(response,sheetName,title,courses);
    }
    @RequestMapping(value = "/downloadProfession",method = RequestMethod.POST)
    public void downloadProfession(HttpServletRequest request,HttpServletResponse response){
        String sheetName = "Profession";
        String[] title = {"专业代码","专业名"};
        List<Profession> professions = latterService.getProfessionOffsetLatterFull();
        ExportExcelUtils<Profession> exportExcelUtils = new ExportExcelUtils<Profession>();
        exportExcelUtils.ExportThing(response,sheetName,title,professions);
    }
    @RequestMapping(value = "/downloadCourseScore",method = RequestMethod.POST)
    public void downloadCourseScore(HttpServletRequest request,HttpServletResponse response){
        String sheetName = "CourseScore";
        String[] title = {"学号","课程号","成绩"};
        List<CourseScore> courseScores = latterService.getCourseScoreLatterFull();
        ExportExcelUtils<CourseScore> exportExcelUtils = new ExportExcelUtils<CourseScore>();
        exportExcelUtils.ExportThing(response,sheetName,title,courseScores);
    }
    @RequestMapping(value = "/permitSearchStudent",method = RequestMethod.GET)
    public String permitSearchStudent(HttpServletRequest request){
        StringEncryptor encryptor = myJasyptUtils.getEncryptor();
        String sno = encryptor.decrypt(request.getCookies()[0].getValue());
        SchoolRoll schoolRoll = originService.getSchoolRoll(sno);
        int auth = schoolRoll.getAuth();
        ArrayList<String> roles = AuthUtils.getRoles(auth);
        if(roles.contains("vip1")){
            return JSON.toJSONString(new ResultRes(200,true));
        }else{
            return JSON.toJSONString(new ResultRes(403,false));
        }
    }
    @RequestMapping(value = "/searchSchoolRollOne",method = RequestMethod.POST)
    public String searchSchoolRollOne(@RequestBody Map<String,Object> params){
        String sno = params.get("sno").toString();
        SchoolRoll schoolRoll = originService.getSchoolRoll(sno);
        if (schoolRoll != null){
            return JSON.toJSONString(new ResultRes(200,schoolRoll));
        }else{
            return JSON.toJSONString(new ResultRes(404,false));
        }
    }
    @RequestMapping(value = "/permitSearch",method = RequestMethod.POST)
    public String permitSearch(HttpServletRequest request){
        StringEncryptor encryptor = myJasyptUtils.getEncryptor();
        String sno = encryptor.decrypt(request.getCookies()[0].getValue());
        SchoolRoll schoolRoll = originService.getSchoolRoll(sno);
        int auth = schoolRoll.getAuth();
        ArrayList<String> roles = AuthUtils.getRoles(auth);
        if(roles.contains("vip2")){
            return JSON.toJSONString(new ResultRes(200,true));
        }else{
            return JSON.toJSONString(new ResultRes(403,false));
        }
    }
    @RequestMapping(value = "/searchSchoolRoll",method = RequestMethod.POST)
    public String searchSchoolRoll(HttpServletRequest request,@RequestBody Map<String,Object> params){
        int start = Integer.parseInt(params.get("start").toString());
        ArrayList<SchoolRoll> schoolRollOffsetOrigin = latterService.getSchoolRollOffsetLatter100(start);
        return JSON.toJSONString(new ResultRes(200,schoolRollOffsetOrigin));
    }
    @RequestMapping(value = "/searchProfession",method = RequestMethod.POST)
    public String searchProfession(HttpServletRequest request,@RequestBody Map<String,Object> params){
        int start = Integer.parseInt(params.get("start").toString());
        ArrayList<Profession> professions = latterService.getProfessionOffsetLatter(start);
        return JSON.toJSONString(new ResultRes(200,professions));
    }
    @RequestMapping(value = "/searchCourse",method = RequestMethod.POST)
    public String searchCourse(HttpServletRequest request,@RequestBody Map<String,Object> params){
        int start = Integer.parseInt(params.get("start").toString());
        ArrayList<Course> courses = latterService.getCourseOffsetLatter(start);
        return JSON.toJSONString(new ResultRes(200,courses));
    }
    @RequestMapping(value = "/searchCourseScore",method = RequestMethod.POST)
    public String searchCourseScore(HttpServletRequest request,@RequestBody Map<String,Object> params){
        int start = Integer.parseInt(params.get("start").toString());
        ArrayList<CourseScore> courseScores = latterService.getCourseScoreLatter100(start);
        return JSON.toJSONString(new ResultRes(200,courseScores));
    }
}

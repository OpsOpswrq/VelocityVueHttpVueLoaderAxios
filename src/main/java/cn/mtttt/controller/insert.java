//package cn.mtttt.controller;
//
//import cn.mtttt.bean.Course;
//import cn.mtttt.bean.CourseScore;
//import cn.mtttt.bean.Profession;
//import cn.mtttt.bean.SchoolRoll;
//import cn.mtttt.service.OriginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@RestController
//public class insert {
//    @Autowired
//    OriginService originService;
//
//    static long baseSno = 2000310500;
//    static final int SnoCnt = 3000;
//    static List<String> Snos = new ArrayList<>();
//    static long basePno = 1000;
//    static List<String> Pnos = new ArrayList<>();
//    static String[] sex = {"男","女"};
//    static Random r = new Random();
//    static String[] PnoName = {"哲学","宗教学","教育学","体育教育","汉语言文学","古典文献学","英语","新闻学","历史学","数学与应用数学","化学","物理学","地理科学","海洋科学","地球物理学","生物科学","材料科学与工程","能源与动力工程","电子信息工程","计算机科学与技术"};
//    static String[] SnoName = {"张吉惟","林国瑞","林玟书","林雅南","江奕云","刘柏宏","阮建安","林子帆","夏志豪","吉茹定","李中冰","黄文隆","谢彦文","傅智翔","洪振霞","刘姿婷","荣姿康","吕致盈","方一强","黎芸贵","郑伊雯","雷进宝","吴美隆","吴心真","王美珠","郭芳天","李雅惠","陈文婷","曹敏侑","王依婷", "陈婉璇","吴美玉","蔡依婷","郑昌梦","林家纶","黄丽昆","李育泉","黄芸"};
//    static long baseCno = 100000;
//    static List<String> Cnos = new ArrayList<>();
//    static String[] CourseName = {"计算机基础","大学英语1A","大学英语1B","中国近现代史纲要","高等数学1","体育1","大学英语2A","大学英语2B","思想道德修养与法律基础","大学物理A1","高等数学2","体育2","程序设计基础","大学英语3A","大学英语3B","毛泽东思想和中国特色社会主义理论体系概论","大学物理A2","大学物理实验","体育3","大学英语4A","大学英语4B","计算机网络","企业管理","大学英语5","马克思主义基本原理概论","形势与政策","学科专业前沿知识及职业导航"};
//    static {
//        for(int i = 0;i < SnoCnt;i++){
//            Snos.add(Long.toString(baseSno + i));
//        }
//        for(int i = 0;i<PnoName.length;i++){
//            Pnos.add(Long.toString(basePno + i));
//        }
//        for(int i = 0;i<CourseName.length;i++){
//            Cnos.add(Long.toString(baseCno + i));
//        }
//    }
//    @GetMapping("/insert")
//    public String preData(){
//        for(int i = 0;i<PnoName.length;i++){
//            originService.insertProfession(new Profession(Pnos.get(i),PnoName[i]));
//        }
//        for(int i = 0;i<SnoCnt;i++){
//            originService.insertSchoolRoll(new SchoolRoll(Snos.get(i),SnoName[r.nextInt(SnoName.length)], sex[r.nextInt(2)], Pnos.get(r.nextInt(Pnos.size())),"root",r.nextInt(3)+1));
//        }
//        for(int i = 0;i<CourseName.length;i++){
//            originService.insertCourse(new Course(Cnos.get(i),CourseName[i]));
//        }
//        for(int i = 0;i<SnoCnt;i++){
//            for(int j = 0;j<CourseName.length;j++){
//                originService.insertCourseScore(new CourseScore(Snos.get(i),Cnos.get(j),(r.nextInt(50)+50)));
//            }
//        }
//        return "ok";
//    }
//
//}

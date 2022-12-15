package cn.mtttt.test;

import cn.mtttt.bean.SchoolRoll;
import cn.mtttt.utils.KieSessionUtils;
import org.junit.Test;
import org.kie.api.runtime.KieSession;

public class test {
    @Test
    public void test1() throws Exception {
        SchoolRoll userInfo = new SchoolRoll();
        userInfo.setName("李");
        KieSession kieSession = KieSessionUtils.getAllRules();
        kieSession.insert(userInfo);
//        kieSession.getAgenda().getAgendaGroup("abc").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}

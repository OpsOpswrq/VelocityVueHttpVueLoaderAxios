package cn.mtttt.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolRoll {
    private String Sno;
    private String Name;
    private String Sex;
    private String ProfessionId;
    private String Password;
    private int Auth;
}

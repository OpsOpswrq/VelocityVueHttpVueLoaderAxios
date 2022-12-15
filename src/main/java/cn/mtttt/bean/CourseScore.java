package cn.mtttt.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseScore {
    private String StudentId;
    private String CourseId;
    private double Score;
}

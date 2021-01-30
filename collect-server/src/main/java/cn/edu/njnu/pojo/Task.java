package cn.edu.njnu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int taskId;
    private String code;
    private String taskName;
    private String describe;
    private int collector;
    private long startTime;
    private long endTime;
    private int exceptedNumber;
    private String fileAddress;
    private int completedNumber;
    private String completedName;
}

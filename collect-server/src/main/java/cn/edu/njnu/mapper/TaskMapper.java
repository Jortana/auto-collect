package cn.edu.njnu.mapper;

import cn.edu.njnu.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskMapper {
    // 添加任务
    void addTask(Task task);
    // 根据收集者ID查找任务
    List<Task> queryTaskByCollector(int collector);
    // 根据任务ID删除任务
    void removeTask(int taskId);
    // 根据任务ID更改任务内容
    void updateTask(Task task);
    // 完成任务人数+1
    void submitTask(Task task);
}

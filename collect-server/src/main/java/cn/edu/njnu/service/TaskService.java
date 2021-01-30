package cn.edu.njnu.service;

import cn.edu.njnu.mapper.TaskMapper;
import cn.edu.njnu.pojo.Task;
import cn.edu.njnu.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class TaskService {
    private final TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public List<Task> getByCollector(int collector) {
        return taskMapper.queryTaskByCollector(collector);
    }

    public void removeTask(int taskId) {
        taskMapper.removeTask(taskId);
    }

    public void updateTask(Task task) {
        taskMapper.updateTask(task);
    }

    public void submitTask(Task task) {
        taskMapper.submitTask(task);
    }

    public Task addTask(Task task) {
        // 默认创建长度为16的随机字符串作为识别码和存放收集的文件的地址
        int length = 16;
        String directory = mkDirectory(length);
        task.setCode(directory);
        if (directory.equals("")) {
            return task;
        }
        task.setCompletedNumber(0);
        task.setCompletedName("");
        taskMapper.addTask(task);
        return task;
    }

    /**
     * 生成指定长度的随机字符串作为任务存储的文件夹名并创建该文件夹
     * @param length 字符串长度
     * @return path - 创建成功则返回文件夹地址，否则返回空字符串
     */
    public String mkDirectory(int length) {
        String basePath = "A:/desktop/collect/upload";
        // 生成随机字符串
        String fileDirectory = StringUtils.getRandomString(length);
        String path = basePath + fileDirectory;
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    return path;
                }
            } else {
                return "";
            }
        } catch (Exception ignored) {
        } finally {
            file = null;
        }
        return "";
    }
}

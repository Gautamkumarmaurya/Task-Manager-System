package Task.Manger.com.example.Service.TaskServiceImpl;

import Task.Manger.com.example.DTO.TaskDTO;
import Task.Manger.com.example.Entity.Task;
import Task.Manger.com.example.Entity.TaskPriority;
import Task.Manger.com.example.Entity.TaskStatus;
import Task.Manger.com.example.Exception.ResourceNotFoundException;
import Task.Manger.com.example.Repository.TaskRepository;
import Task.Manger.com.example.Service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskServiceImplementation implements TaskService{

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskServiceImplementation(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task saved = taskRepository.save(task);
        return modelMapper.map(saved, TaskDTO.class);
    }

    @Override
    public Optional<TaskDTO> getByIdTask(@RequestBody Long id) {
         Task task = taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found"));
         return Optional.of(modelMapper.map(task, TaskDTO.class));
    }

    @Override
    public List<TaskDTO> getAllTask() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> taskDTOS = new ArrayList<>();
        for (Task task : tasks) {
            taskDTOS.add(modelMapper.map(task, TaskDTO.class));
        }
        return taskDTOS;
    }

    @Override
    public TaskDTO updateTask(@RequestBody  Long id,TaskDTO taskDTO) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found"));
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found"));
        taskRepository.delete(task);

    }

    public List<Task> filterByPriority(TaskPriority priority) {
        return taskRepository.findByPriority(priority);
    }

    public List<Task> filterByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> findDueBefore(LocalDate date) {
        return taskRepository.findByDueDateBefore(date);
    }

    public List<Task> findByAssignedUserId(Long userId) {
        return taskRepository.findByAssignedUser_Id(userId);
    }
}
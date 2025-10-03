package Task.Manger.com.example.Service;

import Task.Manger.com.example.DTO.TaskDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {



    public TaskDTO createTask(TaskDTO taskDTO);

    public Optional<TaskDTO> getByIdTask(Long id);

    public List<TaskDTO> getAllTask();

    TaskDTO updateTask( Long id,TaskDTO taskDTO);

    public void deleteTask(Long id);

}



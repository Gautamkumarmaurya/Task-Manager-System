package Task.Manger.com.example.Repository;

import Task.Manger.com.example.Entity.Task;
import Task.Manger.com.example.Entity.TaskPriority;
import Task.Manger.com.example.Entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByPriority(TaskPriority priority);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByDueDateBefore(LocalDate date);
    List<Task> findByAssignedUser_Id(Long userId);

}

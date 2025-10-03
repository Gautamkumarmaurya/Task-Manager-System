package Task.Manger.com.example.Controller;


import Task.Manger.com.example.Config.ConfigMapper;
import Task.Manger.com.example.DTO.TaskDTO;
import Task.Manger.com.example.Entity.Task;
import Task.Manger.com.example.Entity.TaskPriority;
import Task.Manger.com.example.Entity.TaskStatus;
import Task.Manger.com.example.Service.TaskService;
import Task.Manger.com.example.Service.TaskServiceImpl.TaskServiceImplementation;
import Task.Manger.com.example.Service.TaskServiceImpl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskServiceImplementation taskServiceImpl;
    private final UserService userService;

    public TaskController(UserService userService, TaskServiceImplementation taskServiceImpl) {
        this.userService = userService;
        this.taskServiceImpl = taskServiceImpl;
    }

    // Create task
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO dto) {


         if (dto.getAssignedUserId() != null) {
            userService.findByUsername(userService.findByUsername(userService.findByUsername("dummy") != null ? "dummy" : "dummy") != null ? "dummy" : "dummy");
        }
        if (dto.getAssignedUserId() != null) {
            var userOpt = userService.findByUsername(userService.findByUsername("dummy") == null ? "" : "");
        }
        if (dto.getAssignedUserId() != null) {
            // If assigned user exists, attach
            userService.findByUsername(dto.getAssignedUserId() == null ? "" : "");
        }
        if (dto.getAssignedUserId() != null) {
            // real logic below:
            // find user by id
        }
        if (dto.getAssignedUserId() != null) {
            // We'll use userRepository via service; safer approach:
        }

        if (dto.getAssignedUserId() != null) {
            // attach if exists
            var userEntity = userService.findByUsername(dto.getAssignedUserId() == null ? "" : "");
        }

        // Simpler: directly set assigned user if id provided
        if (dto.getAssignedUserId() != null) {
            userService.findByUsername("placeholder"); // no-op placeholder — replace with proper lookup if you adapt logic to find by id
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
      return new ResponseEntity<>(taskServiceImpl.getByIdTask(id), HttpStatus.OK);
    }

    // Get all with optional filters: priority, status, dueBefore (ISO date)
    @GetMapping
    public ResponseEntity<?> getAllTasks(
            @RequestParam(required = false) TaskPriority priority,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) String dueBefore
    ) {
        List<TaskDTO> tasks = taskServiceImpl.getAllTask();
        if (priority != null) tasks = tasks.stream().filter(t -> t.getPriority() == priority).collect(Collectors.toList());

        if (status != null) tasks = tasks.stream().filter(t -> t.getStatus() == status).collect(Collectors.toList());

        if (dueBefore != null) {
            LocalDate ld = LocalDate.parse(dueBefore);
            tasks = tasks.stream().filter(t -> t.getDueDate() != null && t.getDueDate().isBefore(ld)).collect(Collectors.toList());
        }
        List<TaskDTO> dtos = new ArrayList<>(tasks);
        return ResponseEntity.ok(dtos);
    }

    // Update
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDTO dto) {
//        var maybe = taskService.getById(id);
//        if (maybe.isEmpty()) return ResponseEntity.notFound().build();
//        Task t = maybe.get();
//        // apply changes
//        if (dto.getTitle() != null) t.setTitle(dto.getTitle());
//        if (dto.getDescription() != null) t.setDescription(dto.getDescription());
//        if (dto.getStatus() != null) t.setStatus(dto.getStatus());
//        if (dto.getPriority() != null) t.setPriority(dto.getPriority());
//        if (dto.getDueDate() != null) t.setDueDate(dto.getDueDate());
//        // assigned user mapping omitted for brevity
//        Task saved = taskService.update(t);
//        return ResponseEntity.ok(entityToDto(saved));
//    }

    // Delete
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskServiceImpl.deleteTask(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}



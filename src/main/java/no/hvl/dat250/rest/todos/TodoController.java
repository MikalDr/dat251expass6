package no.hvl.dat250.rest.todos;

import no.hvl.dat250.rest.counters.Counters;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Rest-Endpoint for todos.
 */
@RestController
public class TodoController {

  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";

  private long counter = 0;
  private ArrayList<Todo> todos = new ArrayList<Todo>();
  @GetMapping("/todos")
  public ArrayList<Todo> getTodos() {
    return todos;
  }
  @GetMapping("/todos/{ID}")
  public Todo getTodo(@RequestParam Long id) {
    for (Todo todo: todos){
        if (Objects.equals(todo.getId(), id)){
            return todo;
        }
    }
    return null;
  }
  @PutMapping("/todos")
  public Todo updateTodo(@RequestBody Todo newTodo){
    for (Todo todo: todos){
      if (Objects.equals(todo.getId(), newTodo.getId())){
          todos.remove(todo);
          todos.add(newTodo);
      }
    }
      return newTodo;
  }
  @PostMapping("/todos")
  public Todo postTodo(@RequestBody Todo newTodo){
      newTodo.setId(counter);
      counter++;
      todos.add(newTodo);
      return newTodo;
  }
  @DeleteMapping("/todos")
  public Todo deleteTodo(@RequestBody Todo deleteTodo){
    todos.remove(deleteTodo);
    return deleteTodo;
  }
}

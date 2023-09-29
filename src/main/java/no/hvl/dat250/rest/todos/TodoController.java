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
  @GetMapping("/todos/{id}")
  public Todo getTodo(@PathVariable Long id) throws Exception {
    for (Todo todo: todos){
        if (Objects.equals(todo.getId(), id)){
            return todo;
        }
    }
    return errorTodo(id);
  }
  @PutMapping("/todos/{id}")
  public Todo updateTodo(@PathVariable Long id, @RequestBody Todo newTodo){
      newTodo.setId(id);
      for (Todo todo: todos){
          if (Objects.equals(todo.getId(), id)){
              todos.remove(todo);
              todos.add(newTodo);
          }
    }
      return errorTodo(id);
  }
  @PostMapping("/todos")
  public Todo postTodo(@RequestBody Todo newTodo){
      newTodo.setId(counter);
      counter++;
      todos.add(newTodo);
      return newTodo;
  }
  @DeleteMapping("/todos/{id}")
  public Todo deleteTodo(@PathVariable Long id) {
      for (Todo todo : todos) {
          if (Objects.equals(todo.getId(), id)) {
              todos.remove(todo);
              return todo;
          }
      }
      return errorTodo(id);
  }

  public Todo errorTodo(Long id){
      Todo err = new Todo();
      err.setDescription(String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id));

      return err;
  }
}

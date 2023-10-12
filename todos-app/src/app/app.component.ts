import { Component } from '@angular/core';
import { APIService } from './api.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string = 'todos-app';
  todos : any[] = []
  description: any;
  summary: any;
  constructor(private todo:APIService) {
    this.todo.getPosts().subscribe(data=>{
      console.warn(data)
      this.todos = data
    })
  }
  public refreshTodos(){
    this.todo.getPosts().subscribe(data=>{
      this.todos = data
    })
  }
  public deleteTodo(id: number){
      this.todo.deletePost(id).subscribe(data=>{
        console.log(data)
        this.refreshTodos()
    })
  }

  public createTodo(){
    this.todo.createPost(this.summary,this.description).subscribe(data=>{
      console.log(data)
      this.refreshTodos()
    })
  }
}

package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") //hello 라고 치면 호출
    public String hello(Model model){
    model.addAttribute("data", "Spring!!");
    //hello.html의 ${data} 부분에서 출력
    return  "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {//외부에서 파라미터를 받아온다 model에 담으면 그걸 view에서 랜더링 할때 사용
        model.addAttribute("name", name);
        // 파라미터에서 넘어온 name을 넘겨준다 앞에 있는 "name"은 키 뒤에있는 name임
        return " hello-template";
        //->templates의 hello-template.html으로 연결

    }
    @GetMapping("hello-string")
    @ResponseBody //중요(제일 마지막에 넣어주기),  http의 body에 내용을 직접 넣어줄것임
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //"hello spring" 요청한 클라우드에 그대로 내려감
    }
    @GetMapping("hello-api")
    @ResponseBody
    public  Helloapi(@RequestParam("name") String name){
        Hello hello =new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private  String name;
    //생성자 , getter and setter 자동완성 command + n (왼쪽 클릭으로도 설정 가능 )
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}

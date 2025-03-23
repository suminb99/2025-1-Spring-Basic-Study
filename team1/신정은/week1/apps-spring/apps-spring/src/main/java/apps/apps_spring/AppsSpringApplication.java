package apps.apps_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppsSpringApplication.class, args);
	}

}

@GetMapping("hello-mvc")
public String helloMvc(@RequestParam("name") String name, Model model) {
	model.addAttribute(attributeName: "name", name);
	return "hello-template";
}

@GetMapping("hello-string")
@ResponseBody
public String helloString(@RequestParam("name") String name) {
	return "hello" + name;
}
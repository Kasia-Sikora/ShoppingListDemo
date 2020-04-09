package pl.sikora.katarzyna.ShoppingList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class ShoppingListApplication {

	@GetMapping("/")
	public String welcome() {
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingListApplication.class, args);
	}

}

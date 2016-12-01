package first.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This displays the welcome screen that shows what will be happening in this
 * chapter.
 *
 * @author Rob Winch
 *
 */
@Controller
public class WelcomeController {

	private static final Logger logger = Logger.getLogger(WelcomeController.class);

	public WelcomeController() {
		logger.info("•••  criando WelcomeController");
	}

	@RequestMapping("/")
	public String welcome() {
		logger.info("•••  welcome() running");

		return "index";
	}
}
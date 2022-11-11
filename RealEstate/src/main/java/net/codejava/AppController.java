package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private HouseService service;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<House> listHouses = service.listAll();
		model.addAttribute("listHouses", listHouses);

		return "index";
	}

	@RequestMapping("/new")
	public String showNewHouseForm(Model model) {
		House house = new House();
		model.addAttribute("house", house);

		return "new_house";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveHouse(@ModelAttribute("house") House house) {
		service.save(house);

		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditHouseForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_house");

		House house = service.get(id);
		mav.addObject("house", house);

		return mav;
	}

	@RequestMapping("/delete/{id}")
	public String deleteHouse(@PathVariable(name = "id") Long id) {
		service.delete(id);

		return "redirect:/";
	}
}

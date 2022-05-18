package ru.mirea.coursework.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ru.mirea.coursework.entity.*;
import ru.mirea.coursework.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@SuppressWarnings("ALL")
@Controller
@EnableAutoConfiguration
public class MainController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CarService carService;

    @GetMapping("/reg")
    public String reg(Authentication authentication, Model model) {
        if (authentication != null) {
            model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        }
        return "reg";
    }

    @GetMapping(path = "/")
    public String index(Authentication authentication, Model model) {
        if (authentication == null) {
            return "redirect:/reg";
        }
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        model.addAttribute("client", clientService.findByName(authentication.getName()));
        model.addAttribute("name", authentication.getName());
        return "home";
    }

    @PostMapping(path = "/sign")
    public String reg(@RequestParam String name, String pass, String pass2, String email, String role, String address, Model model) {
        if (!pass.equals(pass2)) {
            model.addAttribute("Status", "incorrect_pass");
            return "reg";
        }
        if (clientService.loadUserByUsername(name) != null) {
            model.addAttribute("Status", "existed_user");
            return "reg";
        }
        clientService.create(name, pass, email, role, address);
        return "redirect:/";
    }

    @GetMapping(path = "/goods")
    public String goods(Authentication authentication, Model model) {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        model.addAttribute("goods", goodsService.readAll());
        model.addAttribute("name", authentication.getName());
        return "goods";
    }

    @GetMapping(path = "/settings")
    public String settings(Authentication authentication, Model model) {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        model.addAttribute("products", goodsService.readAll());
        model.addAttribute("name", authentication.getName());
        if (model.getAttribute("role").equals("user")) {
            return "redirect:/";
        }
        return "settings";
    }

    @PostMapping(path = "/changeMail")
    public String changeMail(Authentication authentication, Model model, String email) {
        clientService.findByName(authentication.getName()).setEmail(email);
        clientService.saveChanges(clientService.findByName(authentication.getName()));
        return "redirect:/";
    }

    @PostMapping(path = "/changeAddress")
    public String changeAddress(Authentication authentication, Model model, String address) {
        clientService.findByName(authentication.getName()).setAddress(address);
        clientService.saveChanges(clientService.findByName(authentication.getName()));
        return "redirect:/";
    }

    @PostMapping(path = "/toCar")
    public String toCar(Authentication authentication, Model model, String name, int count) {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        model.addAttribute("name", authentication.getName());
        model.addAttribute("client", clientService.findByName(authentication.getName()));
        model.addAttribute("goods", goodsService);
        Car car = new Car();
        car.setClient(clientService.findByName(authentication.getName()));
        car.setGoods(goodsService.findByName(name));
        car.setCount(count);
        clientService.findByName(authentication.getName()).getCar().add(car);
        goodsService.findByName(name).getCar().add(car);
        carService.create(car);
        goodsService.findByName(name).setCount(goodsService.findByName(name).getCount()-count);
        goodsService.create(goodsService.findByName(name));
        return "redirect:/goods";
    }

    @PostMapping(path = "/removeFromCar")
    public String removeFromCar(Authentication authentication, Model model, int count, Long id) {
        return "redirect:/car";
    }

    @PostMapping(path = "/order")
    public String order(Authentication authentication, Model model, Long id) {
        return "redirect:/car";
    }

    @GetMapping(path = "/car")
    public String car(Authentication authentication, Model model) {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        model.addAttribute("client", clientService.findByName(authentication.getName()));
        model.addAttribute("goods", goodsService.readAll());
        model.addAttribute("goodsService", goodsService);
        model.addAttribute("name", authentication.getName());
        model.addAttribute("car", carService.readAll());
        int sum = 0;
        model.addAttribute("sum", sum);
        return "car";
    }

    @GetMapping(path = "/settings/clients")
    public String clients(Authentication authentication, Model model) throws IOException {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        if (model.getAttribute("role").equals("user")) {
            return "redirect:/";
        }
        model.addAttribute("client", clientService.readAll());
        return "/settings/clients";
    }

    @PostMapping(path = "/changeRole")
    public String changeRole(Authentication authentication, Model model, String name, String role) {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        if (model.getAttribute("role").equals("user")) {
            return "redirect:/";
        }
        clientService.findByName(name).setRole(role);
        clientService.saveChanges(clientService.findByName(name));
        return "redirect:/settings/clients";
    }

    @PostMapping(path = "/deleteClient")
    public String deleteClient(Authentication authentication, Model model, String name) {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        if (model.getAttribute("role").equals("user")) {
            return "redirect:/";
        }
        model.addAttribute("name", authentication.getName());
        //carService.deleteByClient(clientService.findByName(name));
        clientService.delete(name);
        return "redirect:/settings/clients";
    }

    @GetMapping(path = "/settings/goods")
    public String goodsSet(Authentication authentication, Model model) {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        if (model.getAttribute("role").equals("user")) {
            return "redirect:/";
        }
        model.addAttribute("goods", goodsService.readAll());
        return "/settings/goods";
    }

    @PostMapping(path = "/addGoods")
    public String addGoods(Authentication authentication, Model model, String name, float price, int count) throws IOException {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        if (model.getAttribute("role").equals("user")) {
            return "redirect:/";
        }
        model.addAttribute("goods", goodsService.readAll());
        Goods goods = new Goods();
        goods.setName(name);
        goods.setPrice(price);
        goods.setCount(count);
        goodsService.create(goods);
        return "redirect:/settings/goods";
    }

    @PostMapping(path = "/countGoods")
    public String countGoods(Authentication authentication, Model model, String name, int count) throws IOException {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        if (model.getAttribute("role").equals("user")) {
            return "redirect:/";
        }
        model.addAttribute("goods", goodsService.readAll());
        goodsService.findByName(name).setCount(count);
        goodsService.create(goodsService.findByName(name));
        return "redirect:/settings/goods";
    }

    @PostMapping(path = "/priceGoods")
    public String priceGoods(Authentication authentication, Model model, String name, int price) throws IOException {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        if (model.getAttribute("role").equals("user")) {
            return "redirect:/";
        }
        model.addAttribute("goods", goodsService.readAll());
        goodsService.findByName(name).setPrice(price);
        goodsService.create(goodsService.findByName(name));
        return "redirect:/settings/goods";
    }

    @PostMapping(path = "/deleteGoods")
    public String deleteGoods(Authentication authentication, Model model, String name) throws IOException {
        model.addAttribute("role", clientService.findByName(authentication.getName()).getRole());
        if (model.getAttribute("role").equals("user")) {
            return "redirect:/";
        }
        model.addAttribute("goods", goodsService.readAll());
        goodsService.delete(name);
        return "redirect:/settings/goods";
    }
}

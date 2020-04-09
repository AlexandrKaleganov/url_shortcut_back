package ru.akaleganov.job4j_url_shortcut.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/")
public class IndexServlet {

    @GetMapping(value = "/")
    public String getIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       return "index";
    }

}

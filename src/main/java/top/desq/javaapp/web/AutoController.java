package top.desq.javaapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.desq.javaapp.model.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static top.desq.javaapp.util.DateTimeUtil.parseLocalDate;
import static top.desq.javaapp.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/auto")
public class AutoController extends AbstractAutoController {

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/auto";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("auto", super.get(getId(request)));
        model.addAttribute("brands", Brand.values());
        model.addAttribute("bodies", BodyStyle.values());
        model.addAttribute("colors", Color.values());
        model.addAttribute("fuels", FuelType.values());
        return "autoForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("auto", new Auto(Brand.NOT_SELECTED, "", BodyStyle.NOT_SELECTED, Color.NOT_SELECTED, 0, FuelType.NOT_SELECTED, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))); // LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
        model.addAttribute("brands", Brand.values());
        model.addAttribute("bodies", BodyStyle.values());
        model.addAttribute("colors", Color.values());
        model.addAttribute("fuels", FuelType.values());
        return "autoForm";
    }

    // Brand brand, String model, BodyStyle body, Color color, Integer power, FuelType fuel, LocalDateTime dateTime

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Auto auto = new Auto(
                Brand.valueOf(request.getParameter("brand")),
                request.getParameter("model"),
                BodyStyle.valueOf(request.getParameter("body")),
                Color.valueOf(request.getParameter("color")),
                Integer.parseInt(request.getParameter("power")),
                FuelType.valueOf(request.getParameter("fuel")),
                LocalDateTime.parse(request.getParameter("dateTime"))
        );

        if (request.getParameter("id").isEmpty()) {
            super.create(auto);
        } else {
            super.update(auto, getId(request));
        }
        return "redirect:/auto";
    }

    @GetMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("autoList", super.getBetween(startDate, startTime, endDate, endTime));
        return "auto";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}

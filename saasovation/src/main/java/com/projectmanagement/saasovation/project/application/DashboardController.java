package com.projectmanagement.saasovation.project.application;

import com.projectmanagement.saasovation.team.domain.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class DashboardController {

    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String getUserAccount(Model model) {
        Member currentUser = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("member", currentUser);
//        List<CalendarDayEntry> holidays = userService.getAllHolidayDays(currentUser);
//        List <CalendarDayEntry> sickDays = userService.getAllSickDays(currentUser);
//        List <CalendarDayEntry> homeOfficeDays = userService.getAllHomeOfficeDays(currentUser);

        model.addAttribute("member", currentUser);
        model.addAttribute("first_name", currentUser.getFirstName());
        model.addAttribute("last_name", currentUser.getLastName());
//        model.addAttribute("homeOfficeDays", homeOfficeDays);
//        model.addAttribute("totalHolidays", holidays.size());
//        model.addAttribute("totalSickDays", sickDays.size());
//        model.addAttribute("totalHomeOfficeDays", homeOfficeDays.size());
        return "index";
    }

    @RequestMapping(value = "/user")
    public Principal user(Principal principal) {
        return principal;
    }
}

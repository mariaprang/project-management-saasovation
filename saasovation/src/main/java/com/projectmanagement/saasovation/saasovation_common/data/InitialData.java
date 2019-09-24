package com.projectmanagement.saasovation.saasovation_common.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Configuration
public class InitialData {

    private static final Logger log = LoggerFactory.getLogger(InitialData.class);


    private final UserRepository userRepository;
    private final CalendarEntryRepository calendarEntryRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitialData(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       CalendarEntryRepository calendarEntryRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.calendarEntryRepository = calendarEntryRepository;
    }

    @PostConstruct
    public void init() {

        User user = new User();
        user.setName("Maria");
        user.setLastName("Prangishvili");
        user.setEmail("test@email.com");
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode("test"));
        userRepository.save(user);

        List<CalendarDayEntry> days = new ArrayList<>();
        CalendarDayEntry entry1 = new CalendarDayEntry(new Date(), CalendarEntryType.Holiday);
        CalendarDayEntry entry2 = new CalendarDayEntry(new Date(), CalendarEntryType.Holiday);

        days.add(entry1);
        days.add(entry2);

        calendarEntryRepository.saveAll(days);

        user.getCalendarDays().addAll(days);
        entry1.getUsers().add(user);



        Optional<User> check = userRepository.findUserById(user.getId());
        log.info("Email of test user: " + check.get().getEmail());


        log.info("ID for test user: " + user.getId());
    }

}

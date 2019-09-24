package com.projectmanagement.saasovation.security.infrastructure;

import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.infrustructure.IMemberRepository;
import com.projectmanagement.saasovation.team.infrustructure.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final MemberRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(MemberDetailsService.class);
    @Autowired
    public MemberDetailsService(MemberRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = userRepository.loadUserByUsername(username);
        if(member!=null) {
            log.info(member.toString());
            return member;
        }else{
            log.info("USER NOT FOUND!!!");
            throw new UsernameNotFoundException( "User with the username '" + username + "' not found.");
        }
    }
}

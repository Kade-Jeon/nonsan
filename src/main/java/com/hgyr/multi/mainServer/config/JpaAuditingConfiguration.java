package com.hgyr.multi.mainServer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
/*
* 회원가입 일 자동주입
* */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
}

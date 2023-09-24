package com.hgyr.blogpd.controller;

import com.hgyr.blogpd.dto.UserDto;
import com.hgyr.blogpd.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.session.StandardSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.net.URI;
import java.util.Enumeration;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 레디스 세션정보 공통 메서드
    /*public void sessionUid(Model model, HttpSession session) {
        String sid = session.getId();
        String uid = (String) session.getAttribute("user");
        System.out.println(sid + "::::::::" + uid);

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:1777")
                .path("/valid/user/"+uid)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity(uri, uid, UserDto.class);
        model.addAttribute("uid", uid);
        model.addAttribute("nickName", responseEntity.getBody().getNickName());
    }*/

    /* 글목록 */
    @GetMapping({"", "/"})
    public String index(Model model,
                        @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        HttpSession session) {
        String sid = session.getId();
        String uid = (String) session.getAttribute("user");
        System.out.println(sid + "::::::::" + uid);

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:1777")
                .path("/valid/user/"+uid)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity(uri, uid, UserDto.class);
        model.addAttribute("uid", uid);
        model.addAttribute("nickName", responseEntity.getBody().getNickName());
        model.addAttribute("boards", boardService.글목록(pageable));
        return "index";
    }

    /* 글쓰기 폼으로 이동 */
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    /* 글 상세보기 */
    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id, Model model, HttpSession session) {
        String sid = session.getId();
        String uid = (String) session.getAttribute("user");
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:1777")
                .path("/valid/user/"+uid)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity(uri, uid, UserDto.class);
        model.addAttribute("uid", uid);
        model.addAttribute("nickName", responseEntity.getBody().getNickName());

        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
    }

    /* 글수정 */
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/updateForm";
    }
}

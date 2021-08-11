package hacklearn.mygiftsavor.module.controller;

import hacklearn.mygiftsavor.module.model.domain.User;
import hacklearn.mygiftsavor.module.model.dto.GifticonDtos;
import hacklearn.mygiftsavor.module.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hacklearn.mygiftsavor.module.model.dto.GifticonDtos.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HomeController {

    private final HomeService homeService;

    /**
     * 기프티콘 목록(메인)
     * localhost:8080/home
     *
     * @param user
     * @return ResponseEntity
     */
    @GetMapping("/home")
    public ResponseEntity<?> home(@AuthenticationPrincipal User user) {
        log.info("[Request] Retrieve Gifticons " + user.getEmail());
        List<GifticonResDto> result = homeService.home(user);
        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

package org.sopt.domain.styleHint.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.styleHint.service.StyleHintService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StyleHintController {
    private final StyleHintService styleHintService;
}

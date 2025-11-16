package org.sopt.domain.styleHint.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.styleHint.repository.StyleHintRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StyleHintServiceImpl implements StyleHintService {
    private StyleHintRepository styleHintRepository;
}

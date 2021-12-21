package com.simbaleon.spring.API;

import com.simbaleon.spring.models.records.RecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Records")
@RequestMapping("records/")
public class RecordController {
    RecordService service;
}

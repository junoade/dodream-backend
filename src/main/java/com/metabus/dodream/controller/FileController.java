package com.metabus.dodream.controller;

import com.metabus.dodream.config.path.PathDeterminant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dodream")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    PathDeterminant pathDeterminant = new PathDeterminant();
}

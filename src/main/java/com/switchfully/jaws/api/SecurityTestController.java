package com.switchfully.jaws.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class SecurityTestController {


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CREATE_PARKING_LOT')")
    void test() {
        System.out.println("Had access");
    }

}

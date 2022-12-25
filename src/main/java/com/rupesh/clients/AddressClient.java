package com.rupesh.clients;

import com.rupesh.model.AddressDTO;
import com.rupesh.util.global.GlobalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${address-service.name}", url = "${address-service.base-url}", path = "${address-service.path}")
public interface AddressClient {

    @GetMapping("/get/{name}")
    String getAddress(@PathVariable final String name);

    @GetMapping("/{userId}")
    ResponseEntity<GlobalResponse<AddressDTO>> getAddressByUserId(@PathVariable final String userId);

    @PostMapping("/register")
    ResponseEntity<GlobalResponse<AddressDTO>> saveAddress(@RequestBody final AddressDTO addressDTO);

}

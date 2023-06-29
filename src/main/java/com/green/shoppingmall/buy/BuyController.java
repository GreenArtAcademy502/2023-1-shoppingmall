package com.green.shoppingmall.buy;

import com.green.shoppingmall.buy.model.BuyInsDto;
import com.green.shoppingmall.buy.model.BuyVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/buy")
@RequiredArgsConstructor
public class BuyController {
    private final BuyService service;

    @PostMapping
    public ResponseEntity<Integer> postBuy(@RequestBody BuyInsDto dto) {
        return ResponseEntity.ok(service.insBuy(dto));
    }

    @GetMapping("/{icustomer}")
    public ResponseEntity<List<BuyVo>> getBuy(@PathVariable Long icustomer) {
        return ResponseEntity.ok(service.selBuy(icustomer));
    }

}

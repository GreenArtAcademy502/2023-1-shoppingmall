package com.green.shoppingmall.product;

import com.green.shoppingmall.product.model.ProductEntity;
import com.green.shoppingmall.product.model.ProductInsDto;
import com.green.shoppingmall.product.model.ProductVo;
import com.green.shoppingmall.product.model.SingSangSongDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE
                            , MediaType.APPLICATION_JSON_VALUE })
    public int insProduct(@RequestPart MultipartFile img
                        , @RequestPart ProductInsDto dto) {
        return service.insProduct(img, dto);
    }

    @PostMapping(value="/{iproduct}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Long insProductPics(@PathVariable Long iproduct, @RequestPart List<MultipartFile> pics) throws Exception{
        return service.insProductPics(iproduct, pics);
    }

    @GetMapping
    public List<ProductVo> getProduct() {
        return service.selProduct();
    }



    //연습했음
    @PostMapping(value="/file", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public int singSangSong(@RequestPart MultipartFile file, @RequestPart SingSangSongDto data) {
        System.out.println(data);
        System.out.println(file.getOriginalFilename());
        return 0;
    }
}

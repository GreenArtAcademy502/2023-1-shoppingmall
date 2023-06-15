package com.green.shoppingmall.product;

import com.green.shoppingmall.product.model.ProductEntity;
import com.green.shoppingmall.product.model.ProductInsDto;
import com.green.shoppingmall.product.model.SingSangSongDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

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




    //연습했음
    @PostMapping(value="/file", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public int singSangSong(@RequestPart MultipartFile file, @RequestPart SingSangSongDto data) {
        System.out.println(data);
        System.out.println(file.getOriginalFilename());
        return 0;
    }


    @RequestMapping(value="/test1", method = RequestMethod.GET)
    public String test1() {
        return "test1";
    }

    @GetMapping(value="/test2")
    public String test2() {
        return "test2";
    }
}

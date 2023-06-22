package com.green.shoppingmall.product;

import com.green.shoppingmall.product.model.*;
import com.green.shoppingmall.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Value("${file.dir}")
    private String fileDir;

    private final ProductMapper mapper;

    public int insProduct(MultipartFile img, ProductInsDto dto) {
        ProductEntity entity = new ProductEntity();
        entity.setPrice(dto.getPrice());
        entity.setCtnt(dto.getCtnt());
        entity.setNm(dto.getNm());
        entity.setBrand(dto.getBrand());

        //1. 랜덤한 파일명 만든다.
        String savedFileNm = FileUtils.makeRandomFileNm(img.getOriginalFilename());
        System.out.println("savedFileNm : " + savedFileNm);

        //2. insert한다
        entity.setMainPic(savedFileNm);
        int result = mapper.insProduct(entity);

        //3. pk값 얻는다
        System.out.println("result : " + result);
        System.out.println("pk : " + entity.getIproduct());

        //4. D:/download/shoppingmall/product/pk값 폴더 생성
        String targetDir = String.format("%s/product/%d", fileDir, entity.getIproduct());
        File fileTargetDir = new File(targetDir);
        if(!fileTargetDir.exists()) {
            fileTargetDir.mkdirs();
        }

        //5. 이미지를 해당 폴더로 옮긴다.
        //File fileTarget = new File(targetDir + "/" + savedFileNm);
        File fileTarget = new File(String.format("%s/%s", targetDir, savedFileNm));
        try {
            img.transferTo(fileTarget);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public Long insProductPics(Long iproduct, List<MultipartFile> pics) throws Exception {
        // pk > pics 폴더를 만든다.
        String targetDir = String.format("%s/product/%d/pics", fileDir, iproduct);
        File fileTargetDir = new File(targetDir);
        if(!fileTargetDir.exists()) {
            fileTargetDir.mkdirs();
        }

        List<ProductPicEntity> picList = new ArrayList<>();

        for(MultipartFile img : pics) {
            String savedFileNm = FileUtils.makeRandomFileNm(img.getOriginalFilename());
            System.out.println("savedFileNm : " + savedFileNm);

            File fileTarget = new File(String.format("%s/%s", targetDir, savedFileNm));
            try {
                img.transferTo(fileTarget);
            } catch (IOException e) {
                e.printStackTrace();
                throw new Exception("이미지 저장 실패");
            }

            ProductPicEntity entity = new ProductPicEntity();
            entity.setIproduct(iproduct);
            entity.setPic(savedFileNm);
            picList.add(entity);
        }

        return Long.valueOf(mapper.insProductPic(picList));
    }

    List<ProductVo> selProduct() {
        return mapper.selProduct();
    }

    public ProductDetailResponse selProductDetail(ProductGetDetailDto dto) {
        ProductDetailVo data = mapper.selProductById(dto);
        List<String> pics = mapper.selProductPics(dto);

        return ProductDetailResponse.builder()
                .data(data)
                .pics(pics)
                .build();
    }
}

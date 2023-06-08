package com.green.shoppingmall.product;

import com.green.shoppingmall.product.model.ProductEntity;
import com.green.shoppingmall.product.model.ProductInsDto;
import com.green.shoppingmall.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ProductService {
    @Value("${file.dir}")
    private String fileDir;

    private final ProductMapper mapper;

    @Autowired
    public ProductService(ProductMapper mapper) {
        this.mapper = mapper;
    }

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
}

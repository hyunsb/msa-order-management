package com.sparta.msa_exam.product.application.outputport;

import com.sparta.msa_exam.product.application.domain.Product;
import com.sparta.msa_exam.product.application.domain.ProductForCreate;

public interface ProductOutputPort {

    Product saveOne(ProductForCreate productForCreate);
}

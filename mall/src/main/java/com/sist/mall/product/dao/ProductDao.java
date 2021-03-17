package com.sist.mall.product.dao;

import org.springframework.stereotype.Repository;

import com.sist.mall.db.DBManager;
import com.sist.mall.product.vo.ProductVo;

@Repository
public class ProductDao {
	
	public int registerProduct(ProductVo pv) {
		return DBManager.registerProduct(pv);
	}

}

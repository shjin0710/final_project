package com.openrun.ticket.product.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openrun.ticket.product.ProductVO;

@Service
public class ProductService {
	
	final static public int PRODUCT_PNUM_ALREADY_EXIST = 0;
	final static public int PRODUCT_REGISTER_SUCCESS = 1;
	final static public int PRODUCT_REGISTER_FAIL = -1;
	
	@Autowired
	ProductDAO productDao;
	
	public int registerProductConfirm(ProductVO productVo) {
		System.out.println("[ProductService] registerProductConfirm");
		
		boolean isPNum = productDao.isPNum(productVo.getP_no());
		
		
		if(!isPNum) {
			int result = productDao.insertProduct(productVo);
			
			if(result > 0) {
				return PRODUCT_REGISTER_SUCCESS;
			} else {
				return PRODUCT_REGISTER_FAIL;
			}
		} else {
			return PRODUCT_PNUM_ALREADY_EXIST;
		}
	}
	
	public List<ProductVO> searchProductConfirm(ProductVO productVo) {
		System.out.println("[ProductService] searchProductConfirm()");
		
		return productDao.selectProductBySearch(productVo);
	}
	
	public ProductVO productDetail(int p_no) {
		System.out.println("[ProductService] productDetail()");
		
		return productDao.selectProduct(p_no);
	}
	
	public ProductVO modifyProductForm(int p_no) {
		
		return productDao.selectProduct(p_no);
	}
	
	public int modifyProductConfirm(ProductVO productVo) {
		System.out.println("[ProductService] modifyProductConfirm()");
		
		return productDao.updateProduct(productVo);
	}
	
	public int deleteProductConfirm(int p_no) {
		System.out.println("[ProductService] deleteProductConfirm()");
		
		return productDao.deleteProduct(p_no);
	}


}

package com.openrun.ticket.product.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.openrun.ticket.product.ProductVO;
import com.openrun.ticket.product.admin.util.UploadFileService;


@Controller
@RequestMapping("/product/admin")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UploadFileService uploadFileService;
	
	@GetMapping("/registerProductForm")
	public String registerProductForm() {
		System.out.println("[ProductController] registerProductForm()");
		
		String nextPage = "admin/product/register_product_form";
		
		return nextPage;
	}
	
	@PostMapping("/registerProdcutConfirm")
	public String registerProductConfirm(ProductVO productVo,
									  @RequestParam("file") MultipartFile file) {
		System.out.println("[ProductController] registerProductConfirm()");
		
		String nextPage = "admin/product/register_product_ok";
		
		String savedFileName = uploadFileService.upload(file);
		
		if(savedFileName != null) {
			productVo.setP_img(savedFileName);
			int result = productService.registerProductConfirm(productVo);
			
			if(result <= 0 ) {
				nextPage = "admin/product/register_product_ng";
			} else {
				nextPage = "admin/product/register_product_ok";
			}
		}
		return nextPage;
	}
	
	@GetMapping("/searchProductConfirm")
	public String searchProductConfirm(ProductVO productVo, Model model) {
		System.out.println("[ProductController] searchProductConfirm()");
		
		String nextPage = "admin/product/search_product";
		
		List<ProductVO> productVos = productService.searchProductConfirm(productVo);
		
		model.addAttribute("productVos", productVos);
		
		return nextPage;
	}
	
	@GetMapping("/productDetail")
	public String productDetail(@RequestParam("p_no") int p_no, Model model) {
		System.out.println("[ProductController] productDetail()");
		
		String nextPage = "admin/product/product_detail";
		
		ProductVO productVo = productService.productDetail(p_no);
		
		model.addAttribute("productVo", productVo);
		
		return nextPage;
	}
	
	@GetMapping("/modifyProductForm")
	public String modifyProductForm(@RequestParam("p_no") int p_no, Model model) {
		System.out.println("[Productcontroller] modifyProductForm()");
		
		String nextPage = "admin/product/modify_product_form";
		
		ProductVO productVo = productService.modifyProductForm(p_no);
		
		model.addAttribute("productVo", productVo);
		
		return nextPage;
	}
	
	@PostMapping("/modifyProductConfirm")
	public String modifyProductConfirm(ProductVO productVo, @RequestParam("file") MultipartFile file) {
		System.out.println("[ProductController] modifyProductConfirm()");
		
		String nextPage = "admin/product/modify_product_ok";
		
		if(!file.getOriginalFilename().equals("")) {
			String savedFileName = uploadFileService.upload(file);
			
			if(savedFileName != null) {
				productVo.setP_img(savedFileName);
			}
		}
		int result = productService.modifyProductConfirm(productVo);
		
		if(result <= 0)
			nextPage = "admin/product/modify_product_ng";
		
		return nextPage;
	}
	
	@GetMapping("/deleteProductConfirm")
	public String deleteProductConfirm(@RequestParam("p_no") int p_no) {
		System.out.println("[ProductController] deleteProductConfirm()");
		
		String nextPage = "admin/product/delete_product_ok";
		
		int result = productService.deleteProductConfirm(p_no);
		
		if(result <= 0) {
			nextPage = "admin/product/delete_product_ng";
		}
		return nextPage;
	}
}

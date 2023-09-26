package com.openrun.ticket.product.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.openrun.ticket.product.ProductVO;

@Component
public class ProductDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean isPNum(int p_no) {
		System.out.println("[ProductDAO] isPNum()");
		
		String sql = "SELECT COUNT(*) FROM product WHERE p_no = ?";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class, p_no);
		
		return result > 0 ? true : false;
	}
	
	public int insertProduct(ProductVO productVo) {
		System.out.println("[ProductDAO] insertProduct()");
		
		String sql = "INSERT INTO product(p_no, "
					+"p_name, "+"p_img, "+ "p_perfo_date," +"p_viewing_time, "
					+"p_viewing_grade, "+"p_grade, "+"p_price, "
					+"p_perchase_able, "+"p_reg_date, "+"p_end_date) "
					+"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
		
	
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, 
										productVo.getP_no(), productVo.getP_name(),
										productVo.getP_img(), productVo.getP_perfo_date(),
										productVo.getP_viewing_time(), productVo.getP_viewing_grade(),
										productVo.getP_perchase_able(), productVo.getP_reg_date(),
										productVo.getP_end_date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<ProductVO> selectProductBySearch(ProductVO productVo) {
		System.out.println("[ProductDAO] selectProductBySearch()");
		
		String sql = "SELECT * FROM product WHERE p_name LIKE ? ORDER BY p_no DESC";
		
		List<ProductVO> productVos = null;
		
		try {
			productVos = jdbcTemplate.query(sql, new RowMapper<ProductVO>() {
				@Override
				public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					ProductVO productVo = new ProductVO();
					
					productVo.setP_no(rs.getInt("p_no"));
					productVo.setP_img(rs.getString("p_img"));
					productVo.setP_name(rs.getString("p_name"));
					productVo.setP_perfo_date(rs.getString("p_perfo_date"));
					productVo.setP_viewing_time(rs.getString("p_viewing_time"));
					productVo.setP_viewing_grade(rs.getString("p_viewing_grade"));
					productVo.setP_grade(rs.getString("p_grade"));
					productVo.setP_price(rs.getInt("p_price"));
					productVo.setP_reg_date(rs.getString("p_reg_date"));
					productVo.setP_end_date(rs.getString("p_end_date"));
					productVo.setP_perchase_able(rs.getInt("p_perchase_able"));
					
					return productVo;
					
				}
			}, "%" + productVo.getP_name() + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productVos.size() > 0 ? productVos : null;
	}
	
	public ProductVO selectProduct(int p_no) {
		System.out.println("[ProductDAO] selectProduct()");
		
		String sql = "SELECT * FROM product WHERE p_no = ?";
		
		List<ProductVO> productVos = null;
		
		try {
			productVos = jdbcTemplate.query(sql, new RowMapper<ProductVO>() {
				@Override
				public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					ProductVO productVo = new ProductVO();
					
					productVo.setP_no(rs.getInt("p_no"));
					productVo.setP_img(rs.getString("p_img"));
					productVo.setP_name(rs.getString("p_name"));
					productVo.setP_perfo_date(rs.getString("p_perfo_date"));
					productVo.setP_viewing_time(rs.getString("p_viewing_time"));
					productVo.setP_viewing_grade(rs.getString("p_viewing_grade"));
					productVo.setP_grade(rs.getString("p_grade"));
					productVo.setP_price(rs.getInt("p_price"));
					productVo.setP_reg_date(rs.getString("p_reg_date"));
					productVo.setP_end_date(rs.getString("p_end_date"));
					productVo.setP_perchase_able(rs.getInt("p_perchase_able"));
					
					return productVo;
					
				}
			}, p_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productVos.size() > 0 ? productVos.get(0) : null;
	}
	
	public int updateProduct(ProductVO productVo) {
		System.out.println("[ProductDAO] updateProduct()");
		
		List<String> args = new ArrayList<String>();
		
		String sql = "UPDATE tbl_product SET ";
		if(productVo.getP_img() != null) {
			sql += "p_img = ?, ";
			args.add(productVo.getP_img());
		}
//		
//		sql += "p_name = ?, ";
//		args.add(productVo.getP_name());
//		
//		sql += "p_author = ?, ";
//		args.add(bookVo.getB_author());
//		
//		sql += "p_publisher= ?, ";
//		args.add(bookVo.getB_publisher());
//		
//		sql += "p_publish_year= ?, ";
//		args.add(bookVo.getB_publish_year());
//		
//		sql += "p_isbn = ?, ";
//		args.add(bookVo.getB_isbn());
//		
//		sql += "p_call_number= ?, ";
//		args.add(productVo.getB_call_number());
//		
//		sql += "p_rantal_able= ?, ";
//		args.add(Integer.toString(productVo.getB_rantal_able()));
//		
//		sql += "p_mod_date = NOW() ";
//		
//		sql += "WHERE p_no = ?";
//		args.add(Integer.toString(productVo.getP_no()));
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, args.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteProduct(int p_no) {
		System.out.println("[ProductDAO] deleteProduct()");
		
		String sql = "DELETE FROM product WHERE p_no = ?";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, p_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

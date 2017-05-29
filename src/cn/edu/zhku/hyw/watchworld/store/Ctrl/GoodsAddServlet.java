package cn.edu.zhku.hyw.watchworld.store.Ctrl;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cn.edu.zhku.hyw.watchworld.store.JavaBean.GoodsInfo;
import cn.edu.zhku.hyw.watchworld.store.Service.GoodsInfoService;
@WebServlet("/store/GoodsAddServlet")
@MultipartConfig
public class GoodsAddServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		
		String goodsName=request.getParameter("GoodsName");
		String price=request.getParameter("Price");
		String brand=request.getParameter("Brand");
		String color   =request.getParameter("Color");
		String model   =request.getParameter("Model");
		String number  =request.getParameter("Number");
		String storeID =request.getParameter("StoreID");
		
		
		
		Part goodsPart=request.getPart("GoodsPicturePath");
		String header=goodsPart.getHeader("Content-Disposition");
		System.out.println("header:"+header);
		String filename=((header.split(";")[2]).split("=")[1]).replaceAll("\"", "");
		String extname=filename.substring(filename.lastIndexOf('.'));
		String newfilename=System.currentTimeMillis()+extname;
		String uploadpath=getServletContext().getRealPath("/store/IMG/GoodsPicture");
		File filePath = new File(uploadpath);
        if (!filePath.exists()) {
			filePath.mkdir();// 创建目录
		}
		System.out.println("filename:"+filename);
		System.out.println("extname:"+extname);
		System.out.println("newfilename:"+newfilename);
		System.out.println("uploadpath:"+uploadpath);
		try {
			goodsPart.write(uploadpath+File.separator+newfilename);
			GoodsInfo goodsInfo=new GoodsInfo();
			goodsInfo.setGoodsName(goodsName);
			goodsInfo.setPrice(Integer.parseInt(price));
			goodsInfo.setBrand(brand);
			goodsInfo.setColor(color  );
			goodsInfo.setModel(model  );
			goodsInfo.setNumber(Integer.parseInt(number ));
			goodsInfo.setStoreID(Integer.parseInt(storeID));
			goodsInfo.setGoodsPicturePath(newfilename);
			GoodsInfoService service = new GoodsInfoService();
			if(service.addGoods(goodsInfo)){
				request.setAttribute("msg","添加成功" );
			}else {
				request.setAttribute("msg","添加失败" );
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("msg","添加失败" );
		}
		request.getRequestDispatcher("/store/goods_add.jsp").forward(request, response);
	}
	

}

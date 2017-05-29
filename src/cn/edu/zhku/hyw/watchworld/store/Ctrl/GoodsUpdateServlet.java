package cn.edu.zhku.hyw.watchworld.store.Ctrl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cn.edu.zhku.hyw.watchworld.store.JavaBean.GoodsInfo;
import cn.edu.zhku.hyw.watchworld.store.Service.GoodsInfoService;
@WebServlet("/store/GoodsUpdateServlet")
@MultipartConfig
public class GoodsUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String goodsID=request.getParameter("GoodsID");
		String goodsName=request.getParameter("GoodsName");
		String price=request.getParameter("Price");
		String brand=request.getParameter("Brand");
		String color   =request.getParameter("Color");
		String model   =request.getParameter("Model");
		String number  =request.getParameter("Number");
		String goodsPicturePath=request.getParameter("f_GoodsPicturePath");
		Part goodsPart=request.getPart("GoodsPicturePath");
		System.out.println("更新前图片:"+goodsPicturePath);
		GoodsInfo goodsInfo=new GoodsInfo();
		goodsInfo.setGoodsID(Integer.parseInt(goodsID));
		goodsInfo.setGoodsName(goodsName);
		goodsInfo.setPrice(Integer.parseInt(price));
		goodsInfo.setBrand(brand);
		goodsInfo.setColor(color  );
		goodsInfo.setModel(model  );
		goodsInfo.setNumber(Integer.parseInt(number ));
		
			String header=goodsPart.getHeader("Content-Disposition");
			System.out.println("header:"+header);
			String filename=((header.split(";")[2]).split("=")[1]).replaceAll("\"", "");
			if ("".equals(filename)) {
				
					goodsInfo.setGoodsPicturePath(goodsPicturePath);
				
			} else {

				String extname=filename.substring(filename.lastIndexOf('.'));
				String newfilename=System.currentTimeMillis()+extname;
			String uploadpath=getServletContext().getRealPath("/store/IMG/GoodsPicture");
			try {
				File filePath = new File(uploadpath);
		        if (!filePath.exists()) {
					filePath.mkdir();// 创建目录
				}
				goodsPart.write(uploadpath+File.separator+newfilename);
				File file = new File(uploadpath+File.separator+goodsPicturePath);
				file.delete();
				goodsInfo.setGoodsPicturePath(newfilename);
			}catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg","修改失败" );
			}
		}
		GoodsInfoService service = new GoodsInfoService();
		if(service.updateGoods(goodsInfo)){
			request.setAttribute("msg", "修改成功!");
		}else{
			request.setAttribute("msg", "修改失败!");
		}
		request.getRequestDispatcher("/store/GoodsShowServlet").forward(request, response);
	}

}

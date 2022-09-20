import java.io.*;

import org.apache.batik.apps.rasterizer.DestinationType;
import org.apache.batik.apps.rasterizer.SVGConverter;
import org.apache.batik.apps.rasterizer.SVGConverterException;

public class makepdf2_6_next extends SVGConverter{
		
		/**
		 * 转换方法
		 * @param sources SVG文件路径
		 * @param destination 目标文件路径
		 * @param type 转换类型，有 image/png | image/jpeg | application/pdf |　image/svg+xml　可选
		 * @param width 导出图片宽度
		 * @return 目标文件名
		 * @throws SVGConverterException
		 */
		public  String conver(String sources,String outfile,String type,int w,int high) throws SVGConverterException {
			
			SVGConverter converter = new makepdf2_6_next();
			
			converter.setHeight(high);
			
			// 设置宽度，传入的值
			converter.setWidth(w);
			
			// 设置svg源文件路径，是一个数组，支持多个文件同时转换
			String[] src = {sources};
			converter.setSources(src);
//			converter.setLanguage("zh-cn");
//			System.out.println("DefaultFontFamily: "+converter.getDefaultFontFamily());
			// 设置图片质量
			// 记录文件后缀
			String ext = "";
			
			// 更具传入的类型设置导出类型和文件后缀
			if(type.equals("image/png")) {
				converter.setDestinationType(DestinationType.PNG);
				ext = "png";
			} else if(type.equals("image/jpeg")) {
				converter.setDestinationType(DestinationType.JPEG);
				ext = "jpg";
			} else if(type.equals("application/pdf")) {
				converter.setDestinationType(DestinationType.PDF);
				ext = "pdf";
			} else if (type.equals("image/svg+xml")) {
				converter.setDestinationType(DestinationType.TIFF);
				ext = "tif";
			} else {
				return null;
			}
			// 设置目标文件路径
			converter.setDst(new File(outfile));
			
			// 执行导出
			//System.out.println("执行导出");
			converter.execute();
			return sources+".pdf";
		}
	}




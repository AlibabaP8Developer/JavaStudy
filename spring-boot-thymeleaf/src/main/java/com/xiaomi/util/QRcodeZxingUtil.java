package com.xiaomi.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * 生成二维码
 * @author alibaba
 */
public class QRcodeZxingUtil {

	/**
	 * 定义日期格式
	 */
	public static DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 二维码尺寸
	 */
	public static final int QRCODE_SIZE = 300;

	/**
	 * 二维码携带内容信息
	 */
	public static final String CONTENT = "http://blog.csdn.net/magi1201";

	/**
	 * 生成二维码 直接将二维码图片写到指定文件目录
	 *
	 * @param content 二维码内容
	 * @param width   二维码宽度
	 * @param height  二维码高度，通常建议二维码宽度和高度相同
	 * @param picFormat  二维码图片格式，jpg/png
	 * @param nfile 指定生成的路径
	 */
	public static void generateQRcodePic(String content, int width, int height, String picFormat, File nfile) {

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);

		try {
			// 构造二维字节矩阵
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

			// 构造文件目录，若目录不存在，则创建目录
			//String fileDir = "D:\\DEV_ENV" + File.separator + "image" + File.separator + sf.format(new Date());
			String fileDir = nfile.getAbsolutePath() + File.separator + "image/qrcode" + File.separator + sf.format(new Date());
			if (!new File(fileDir).exists()) {
				new File(fileDir).mkdirs();
			}
			Path file = new File(fileDir + File.separator + "qrcode." + picFormat).toPath();

			// 将二位字节矩阵按照指定图片格式，写入指定文件目录，生成二维码图片
			MatrixToImageWriter.writeToPath(bitMatrix, picFormat, file);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码 生成二维码图片字节流
	 *
	 * @param content 二维码内容
	 * @param width   二维码宽度和高度
	 * @param picFormat  二维码图片格式
	 */
	public static byte[] generateQRcodeByte(String content, int width, String picFormat) {
		byte[] codeBytes = null;
		try {
			// 构造二维字节矩阵，将二位字节矩阵渲染为二维缓存图片
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, width);
			BufferedImage image = toBufferedImage(bitMatrix);

			// 定义输出流，将二维缓存图片写到指定输出流
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(image, picFormat, out);

			// 将输出流转换为字节数组
			codeBytes = out.toByteArray();

		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		return codeBytes;
	}

	/**
	 * 将二维字节矩阵渲染为二维缓存图片
	 *
	 * @param matrix 二维字节矩阵
	 * @return 二维缓存图片
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int onColor = 0xFF000000;
		int offColor = 0xFFFFFFFF;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
			}
		}
		return image;
	}

	/**
	 * 解析二维码内容
	 *
	 * @param filepath 二维码路径
	 */
	public static void readQRcode(String filepath) {

		MultiFormatReader multiFormatReader = new MultiFormatReader();
		File file = new File(filepath);

		// 图片缓冲
		BufferedImage image = null;

		// 二进制比特图
		BinaryBitmap binaryBitmap = null;

		// 二维码结果
		Result result = null;

		try {
			image = ImageIO.read(file);
			binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			result = multiFormatReader.decode(binaryBitmap);
		} catch (IOException | NotFoundException e1) {
			e1.printStackTrace();
		}

		System.out.println("读取二维码： " + result.toString());
		System.out.println("二维码格式： " + result.getBarcodeFormat());
		System.out.println("二维码内容： " + result.getText());
	}

	/**
	 * main 测试方法
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		// 生成二维码，直接写到本地
		generateQRcodePic(CONTENT, QRCODE_SIZE, QRCODE_SIZE, "jpg", new File(""));

		// 测试二维码信息解析
		String filepath = "D:\\DEV_ENV" + File.separator + "image" + File.separator + sf.format(new Date())
				+ File.separator + "qrcode.jpg";
		readQRcode(filepath);

		// 生成二维码，返回字节数组
		String path = "D:\\DEV_ENV" + File.separator + "image" + File.separator + sf.format(new Date());
		File pathDir = new File(path);
		if (!pathDir.exists()) {
			pathDir.mkdirs();
		}

		File pathFile = new File(path + File.separator + "qrcodeByte.jpg");
		byte[] fileIo = generateQRcodeByte(CONTENT, QRCODE_SIZE, "jpg");
		try {
			OutputStream os = new FileOutputStream(pathFile);
			os.write(fileIo);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
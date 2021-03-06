package animationPack;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.IContainer;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IRational;


public class AnimationBuilder {

	//fps = how many frames will be displayed per sec, default 24
	private int frameRate = 30;
	//number of millisecs each frame will be displayed for the viewer , default 1000 millisecs
	private long durationPerFrame = 10; 
	//path to output mpeg video 
	private String outputFilenamePath = "";
	//list of JPEG pictures to be converted 
	private List<String> jpegFilePathList = new ArrayList<String>();
	//list of actual images in memory to be iterated through & encoded by Xuggler 
	private List<BufferedImage> jpegFileList = new ArrayList<BufferedImage>();
	private BufferedImage bfrImg;

	/* VERY IMPORTANT regarding setDurationPerFrame(), I noticed that fpr 24 fps , 
    the expected duration fell short 15% or 150 millisec per 1 second, 
    therefore I adjusted this by adding 15% to the user specified duration, 
    For example 3000 millisec duration will be adjusted to 3450 millisecs. 
    This adjustment was even more severe for lower fps*/

	public void setDurationPerFrame(long  durationPerFrame) {
		this.durationPerFrame = new Double(Math.ceil(durationPerFrame * 1.15)).longValue();
	}

	public long getDurationPerFrame() {
		return durationPerFrame;
	}
	/* 
     Actual method that converts JPEG images to MPEG (.mp4) extension video using Xuggler library
	 */
	public void convertJpegFramesToMpegVideo() {
		System.out.println("convertJpegFramesToMpegVideo, start...");
		// BufferedImage to store JPEG data from file
		BufferedImage img = null;
		IContainer container = IContainer.make();
		IMediaWriter writer = null;
		long nextEncodeTime = getDurationPerFrame();
		writer = ToolFactory.makeWriter(getOutputFilenamePath(), container);
		//the framerate is set at 24 fps , could be adjusted
		writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_WMV1,IRational.make(frameRate),784,562);

		File imgFile = null;
		//loop to load data from paths to BufferedImage objects
		for (int i = 0; i < jpegFilePathList.size(); i++) {
			imgFile = new File(getJpegFilePathList().get(i));
			if (imgFile.exists()) {

				try {

					img = ImageIO.read(imgFile);

					jpegFileList.add(img);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("convertJpegFramesToMpegVideo, file path: " + imgFile.getPath() + " does not exist!");
			}
		}//end for to load path to images 


		//for loop to encode each BufferedImage 
		for (int i = 0; i <jpegFileList.size(); i++) {
			System.out.println("convertJpegFramesToMpegVideo encoded frame counter: " + i);
			img = jpegFileList.get(i);
			bfrImg = convertToType(img,BufferedImage.TYPE_3BYTE_BGR);
			writer.encodeVideo(0, bfrImg, nextEncodeTime, TimeUnit.MILLISECONDS);
			nextEncodeTime = nextEncodeTime + getDurationPerFrame();
		}//end loop 
		// after encoding all BufferedImages close 
		writer.close();
		System.out.println("convertJpegFramesToMpegVideo, end!");
	}//end

	public static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {
		BufferedImage image;
		// if the source image is already the target type, return the source image

		if (sourceImage.getType() == targetType) {
			image = sourceImage;
		}
		// otherwise create a new image of the target type and draw the new image
		else {
			image = new BufferedImage(sourceImage.getWidth(),
					sourceImage.getHeight(), targetType);
			image.getGraphics().drawImage(sourceImage, 0, 0, null);
		}
		return image;
	}

	public int getFrameRate() {
		return frameRate;
	}

	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
	}
	public String getOutputFilenamePath() {
		return outputFilenamePath;
	}

	public void setOutputFilenamePath(String outputFilenamePath) {
		this.outputFilenamePath = outputFilenamePath;
	}

	public List<String> getJpegFilePathList() {
		return jpegFilePathList;
	}

	public void setJpegFilePathList(List<String> jpegFilePathList) {
		this.jpegFilePathList = jpegFilePathList;
	}

	public List<BufferedImage> getJpegFileList() {
		return jpegFileList;
	}

	public void setJpegFileList(List<BufferedImage> jpegFileList) {
		this.jpegFileList = jpegFileList;
	}

}//end class 
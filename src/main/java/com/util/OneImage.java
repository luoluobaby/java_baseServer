package com.util;

/**
 * 一张照片的数据
 * @author moni
 *
 */

public class OneImage {
	private String imagename = "";
	
	private byte[] imageContent = null;

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public byte[] getImageContent() {
		return imageContent;
	}

	public void setImageContent(byte[] imageContent) {
		this.imageContent = imageContent;
	}

	public OneImage(String imagename, byte[] imageContent) {
		super();
		this.imagename = imagename;
		this.imageContent = imageContent;
	}
}

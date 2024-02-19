package model;

import javax.swing.ImageIcon;

public class Photo {

	private ImageIcon photo;
	private String imagePath;

	public Photo(ImageIcon photo, String imagePath) {
		this.photo = photo;
		this.imagePath = imagePath;
	}

	public ImageIcon getPhoto() {
		return photo;
	}

	public String getImagePath() {
		return imagePath;
	}
}

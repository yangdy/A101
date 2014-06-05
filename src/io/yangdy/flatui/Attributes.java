package io.yangdy.flatui;

import android.content.res.Resources;
import android.graphics.Color;
import io.yangdy.R;

public class Attributes {

	public static int INVALID = -1;
	
	public static int DEFAULT_THEME = R.array.blood;
	
	public static final String DEFAULT_FONT_FAMILY = "roboto";
	public static final String DEFAULT_FONT_WIEGHT = "light";
	public static final String DEFAULT_FONT_EXTENSION = "ttf";
	public static final int DEFAULT_TEXT_APPEARANCE = 0;
	
	public static int DEFAULT_RADIUS= 4;
	public static int DEFAULT_BORDER_WIDTH = 2;
	public static int DEFAULT_SIZE = 10;
	
	private int[] colors;
	private int theme = INVALID;
	
	private String fontFamily = DEFAULT_FONT_FAMILY;
	private String fontWeight = DEFAULT_FONT_WIEGHT;
	private String fontExtension = DEFAULT_FONT_EXTENSION;
	private int textAppearance = DEFAULT_TEXT_APPEARANCE;
	
	private int radius = DEFAULT_RADIUS;
	private int size = DEFAULT_SIZE;
	private int borderWidth = DEFAULT_BORDER_WIDTH;
	
	public interface AttributeChangeListener {
		public void onThemeChange();
	}
	
	private AttributeChangeListener attributeChangeListener;
	
	public Attributes(AttributeChangeListener attributeChangeListener) {
		this.attributeChangeListener = attributeChangeListener;
	}
	
	public int getTheme() {
		return theme;
	}
	
	public void setTheme(int theme, Resources resources) {
		setTheme(theme, resources);
		attributeChangeListener.onThemeChange();
	}
	
	public void setThemeSilent(int theme, Resources resources) {
		try {
			this.theme = theme;
			colors = resources.getIntArray(theme);
		} catch (Resources.NotFoundException e) {
			colors = new int[] {
				Color.parseColor("#732219"),
				Color.parseColor("#a63124"),
				Color.parseColor("#d94130"),
				Color.parseColor("#f2b6ae")
			};
		}
	}

	public void setColors(int[] colors) {
		this.colors = colors;
		attributeChangeListener.onThemeChange();
	}
	
	public int getColor(int index) {
		return colors[index];
	}
	
	public String getFontFamily() {
		return fontFamily;
	}
	
	public void setFontFamily(String fontFamily) {
		if (fontFamily != null && !fontFamily.equals("") && !fontFamily.equals("null")) {
			this.fontFamily = fontFamily;
		}
	}
	
	public String getFontWeight() {
		return fontWeight;
	}
	
	public void setFontWeight(String fontWeight) {
		if (fontWeight != null && !fontWeight.equals("") && !fontWeight.equals("null")) {
			this.fontWeight = fontWeight;
		}
	}
	
	public String getFontExtension() {
		return fontExtension;
	}
	
	public void setFontExtension(String fontExtension) {
		if (fontExtension != null && !fontExtension.equals("") && !fontExtension.equals("null")) {
			this.fontExtension = fontExtension;
		}
	} 
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public float[] getOuterRadius() {
		return new float[] { radius, radius, radius, radius, radius, radius, radius, radius, radius };
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getBorderWidth() {
		return borderWidth;
	}
	
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
	
	public int getTextAppearance() {
		return textAppearance;
	}
	
	public void setTextAppearance(int textAppearance) {
		this.textAppearance = textAppearance;
	} 

}

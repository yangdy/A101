package io.yangdy.flatui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import io.yangdy.R;

public class FlatUI {
	
	public static final String androidStyleNamespace = "http://schemas.android.com/apk/res/android";
	
	public static final int BLOOD = R.array.blood;
	
	public static void initDefaultValues(Context context) {
		Attributes.DEFAULT_BORDER_WIDTH = (int) dipToPx(context, Attributes.DEFAULT_BORDER_WIDTH);
		Attributes.DEFAULT_RADIUS = (int) dipToPx(context, Attributes.DEFAULT_RADIUS);
		Attributes.DEFAULT_SIZE = (int) dipToPx(context, Attributes.DEFAULT_SIZE);
	}
	
	public static Typeface getFont(Context context, Attributes attributes) {
		String fontPath = "fonts/" + attributes.getFontFamily()
			+ "_" + attributes.getFontWeight()
			+ "." + attributes.getFontExtension();
		
		try {
			return Typeface.createFromAsset(context.getAssets(), fontPath);
		} catch (Exception e) {
			Log.e("FlatUI", "Font file at " + fontPath + " cannot be found or the files is " +
				"not a valid font file.");
			return null;
		}
	}
	
	public static Drawable getActionBarDrawable(Activity activity, int theme, boolean dark) {
		return getActionBarDrawable(activity, theme, dark, 0);
	}

	public static Drawable getActionBarDrawable(Activity activity, int theme, boolean dark, float borderBottom) {
		int[] colors = activity.getResources().getIntArray(theme);
		int c1 = colors[2];
		int c2 = colors[1];
		
		if (dark) {
			c1 = colors[1];
			c2 = colors[0];
		}
		
		borderBottom = dipToPx(activity, borderBottom);
		
		PaintDrawable front = new PaintDrawable(c1);
		PaintDrawable bottom = new PaintDrawable(c2);
		Drawable[] d = { bottom, front };
		LayerDrawable drawable = new LayerDrawable(d);
		drawable.setLayerInset(1,  0,  0,  0, (int)borderBottom);
		return drawable;
	}
	
	public static void setDefaultTheme(int theme) {
		Attributes.DEFAULT_THEME = theme;
	}

	private static float dipToPx(Context context, float dp) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
	}

}

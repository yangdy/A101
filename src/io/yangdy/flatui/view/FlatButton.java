package io.yangdy.flatui.view;

import io.yangdy.R;
import io.yangdy.flatui.Attributes;
import io.yangdy.flatui.FlatUI;
import io.yangdy.flatui.Attributes.AttributeChangeListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.Button;

public class FlatButton extends Button implements AttributeChangeListener {
	
	private Attributes attributes;
	
	private int bottom = 0;
	
	public FlatButton(Context context) {
		super(context);
		init(null);
	}
	
	public FlatButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}
	
	public FlatButton(Context context, AttributeSet attrs, int defaultStyle) {
		super(context, attrs, defaultStyle);
		init(attrs);
	}
	
	@SuppressWarnings("deprecation")
	private void init(AttributeSet attrs) {
		if (attributes == null) {
			attributes = new Attributes(this);
		}
		
		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FlatButton);
			int customTheme = a.getResourceId(R.styleable.FlatButton_theme, Attributes.DEFAULT_THEME);
			attributes.setThemeSilent(customTheme, getResources());
			attributes.setFontFamily(a.getString(R.styleable.FlatButton_fontFamily));
			attributes.setFontWeight(a.getString(R.styleable.FlatButton_fontWeight));
			attributes.setFontExtension(a.getString(R.styleable.FlatButton_fontExtension));
			attributes.setTextAppearance(a.getInt(R.styleable.FlatButton_textAppearance, 
				Attributes.DEFAULT_TEXT_APPEARANCE));
			attributes.setRadius(a.getInt(R.styleable.FlatButton_cornerRadius, 
				Attributes.DEFAULT_RADIUS));
			bottom = a.getDimensionPixelSize(R.styleable.FlatButton_blockButtonEffectHeight, bottom);
			a.recycle();
		}
		
		ShapeDrawable normalFront =
			new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
		normalFront.getPaint().setColor(attributes.getColor(2));
		
		ShapeDrawable normalBack =
			new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
		normalBack.getPaint().setColor(attributes.getColor(1));
		normalBack.setPadding(0, 0, 0, bottom);
		
		Drawable[] d = { normalBack, normalFront };
		LayerDrawable normal = new LayerDrawable(d);
		
		ShapeDrawable pressedFront =
			new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
		pressedFront.getPaint().setColor(attributes.getColor(1));
		
		ShapeDrawable pressedBack = 
			new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
		pressedBack.getPaint().setColor(attributes.getColor(0));
		pressedBack.setPadding(0, 0, 0, bottom/2);
		
		Drawable[] d2 = { pressedBack, pressedFront };
		LayerDrawable pressed = new LayerDrawable(d2);
		
		ShapeDrawable disabledFront =
			new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
		disabledFront.getPaint().setColor(attributes.getColor(3));
		
		ShapeDrawable disabledBack =
			new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
		disabledBack.getPaint().setColor(attributes.getColor(2));
		
		Drawable[] d3 = { disabledBack, disabledFront };
		LayerDrawable disabled = new LayerDrawable(d3);
		
		StateListDrawable states = new StateListDrawable();
		states.addState(new int[]{ android.R.attr.state_pressed, android.R.attr.state_enabled }, pressed);
		states.addState(new int[]{ android.R.attr.state_focused, android.R.attr.state_enabled }, pressed);
		states.addState(new int[]{ android.R.attr.state_enabled }, normal);
		states.addState(new int[]{ -android.R.attr.state_enabled }, disabled);
		
		//setBackgroundDrawable(states);
		setBackgroundDrawable(states);
		
		if (attributes.getTextAppearance() == 1) setTextColor(attributes.getColor(0));
		else if (attributes.getTextAppearance() == 2) setTextColor(attributes.getColor(3));
		else setTextColor(Color.WHITE);
		
		if (!this.isInEditMode()) {
			Typeface typeface = FlatUI.getFont(getContext(), attributes);
			if (typeface != null) setTypeface(typeface);
		}
	}
	
	public Attributes getAttributes() {
		return attributes;
	}

	@Override
	public void onThemeChange() {
		init(null);
	}

}

package jaanpehchan.rural.srei;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class CustomFontEditText extends AppCompatEditText {

    public CustomFontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        style(context, attrs);
    }

    public CustomFontEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        style(context, attrs);
    }

    private void style(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomFontTextView);
        int cf = a.getInteger(R.styleable.CustomFontTextView_fontName, 0);
        int fontName = 0;
        switch (cf) {
            case 1:
                fontName = R.string.Avenir_Book_01;
                break;
        }
        String customFont = getResources().getString(fontName);
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/" + customFont);
        setTypeface(tf);
        a.recycle();
    }
}

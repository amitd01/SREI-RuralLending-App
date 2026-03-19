package jaanpehchan.rural.srei;

import android.graphics.drawable.Drawable;

/**
 * Created by 1515012 on 11-05-2018.
 */

public class Proof {
    private String s;
    private Drawable drawable;
    public Proof(String s, Drawable drawable) {
        this.s=s;
        this.drawable=drawable;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public String getS() {
        return s;
    }
}

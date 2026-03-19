package jaanpehchan.rural.srei;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Shared UI utility methods used across multiple activities.
 */
public final class ViewUtils {

    private ViewUtils() {
        // Utility class, no instantiation
    }

    public static int dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }
}

package jaanpehchan.rural.srei;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;


public class Animations {

    public static void fading(Context context,View toHide,View toEnable){

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setDuration(1000);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setStartOffset(1000);
        fadeIn.setDuration(1000);


        AnimationSet animationFadeOut = new AnimationSet(false); //change to false
        AnimationSet animationFadeIn = new AnimationSet(false); //change to false
        animationFadeIn.addAnimation(fadeIn);
        animationFadeOut.addAnimation(fadeOut);


        if(toHide!=null) {
            toHide.setAnimation(animationFadeOut);
            toHide.setVisibility(View.GONE);
        }
        if(toEnable!=null){
            toEnable.setAnimation(animationFadeIn);
            toEnable.setVisibility(View.VISIBLE);
        }
    }

    public static void sliding(Context context,View toHide,View toEnable){

        Animation slideOut = new AlphaAnimation(1, 0);
        slideOut.setInterpolator(new AccelerateInterpolator()); //and this
        slideOut.setDuration(1000);

        Animation slideIn = new AlphaAnimation(0, 1);
        slideIn.setInterpolator(new DecelerateInterpolator()); //add this
        slideIn.setStartOffset(1000);
        slideIn.setDuration(1000);


        AnimationSet animationSlideOut = new AnimationSet(false); //change to false
        AnimationSet animationSlideIn = new AnimationSet(false); //change to false
        animationSlideIn.addAnimation(slideIn);
        animationSlideOut.addAnimation(slideOut);
        if(toHide!=null)
            toHide.setAnimation(animationSlideOut);
        if(toEnable!=null)
            toEnable.setAnimation(animationSlideIn);
    }

    // slide the view from below itself to the current position
    public static void slideUp(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public static void slideDown(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }
}

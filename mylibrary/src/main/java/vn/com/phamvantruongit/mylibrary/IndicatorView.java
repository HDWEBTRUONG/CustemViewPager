package vn.com.phamvantruongit.mylibrary;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class IndicatorView extends View implements IndicatorInterface ,ViewPager.OnPageChangeListener {

    private static final long DEFAULT_ANIMATE_DURATION = 200;

    private static final int DEFAULT_RADIUS_SELECTED = 10;

    private static final int DEFAULT_RADIUS_UNSELECTED = 7;

    private static final int DEFAULT_DISTANCE = 30;

    private ViewPager viewPager;

    private Dot[] dots;

    private long animateDuration = DEFAULT_ANIMATE_DURATION;

    private int radiusSelected = DEFAULT_RADIUS_SELECTED;

    private int radiusUnselected = DEFAULT_RADIUS_UNSELECTED;

    private int distance = DEFAULT_DISTANCE;

    private int colorSelected;

    private int colorUnselected;

    private int currentPosition;

    private int beforePosition;

    private ValueAnimator animatorZoomIn;

    private ValueAnimator animatorZoomOut;

    public IndicatorView(Context context) {
        super(context);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);

        this.radiusSelected = typedArray.getDimensionPixelSize(R.styleable.IndicatorView_hado_radius_selected, DEFAULT_RADIUS_SELECTED);

        this.radiusUnselected = typedArray.getDimensionPixelSize(R.styleable.IndicatorView_hado_radius_unselected, DEFAULT_RADIUS_UNSELECTED);

        this.distance = typedArray.getInt(R.styleable.IndicatorView_hado_distance, DEFAULT_DISTANCE);

        this.colorSelected = typedArray.getColor(R.styleable.IndicatorView_hado_color_selected, Color.parseColor("#ffffff"));

        this.colorUnselected = typedArray.getColor(R.styleable.IndicatorView_hado_color_unselected, Color.parseColor("#ffffff"));

        typedArray.recycle();
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int desiredHeight = 2 * radiusSelected;

        int width;
        int height;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = widthSize;
        } else {
            width = 0;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Dot dot : dots) {
            dot.draw(canvas);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        float yCenter=getHeight()/2;

        int d=distance+2*radiusUnselected;

        float firstXCenter=(getWidth()/2) -((dots.length-1)*d/2);
        for (int i = 0; i < dots.length; i++) {
            dots[i].setCenter(i == 0 ? firstXCenter : firstXCenter + d * i, yCenter);
            dots[i].setCurrentRadius(i == currentPosition ? radiusSelected : radiusUnselected);
            dots[i].setColor(i == currentPosition ? colorSelected : colorUnselected);
            dots[i].setAlpha(i == currentPosition ? 255 : radiusUnselected * 255 / radiusSelected);
        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        beforePosition = currentPosition;
        currentPosition = position;

        if (beforePosition == currentPosition) {
            beforePosition = currentPosition + 1;
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(animateDuration);

        animatorZoomIn = ValueAnimator.ofInt(radiusUnselected, radiusSelected);
        animatorZoomIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int positionPerform = currentPosition;

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int newRadius = (int) valueAnimator.getAnimatedValue();
                changeNewRadius(positionPerform, newRadius);
            }
        });

        animatorZoomOut = ValueAnimator.ofInt(radiusSelected, radiusUnselected);
        animatorZoomOut.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int positionPerform = beforePosition;

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int newRadius = (int) valueAnimator.getAnimatedValue();
                changeNewRadius(positionPerform, newRadius);
            }
        });

        animatorSet.play(animatorZoomIn).with(animatorZoomOut);
        animatorSet.start();
    }

    private void changeNewRadius(int positionPerform, int newRadius) {
        if (dots[positionPerform].getCurrentRadius() != newRadius) {
            dots[positionPerform].setCurrentRadius(newRadius);
            dots[positionPerform].setAlpha(newRadius * 255 / radiusSelected);
            invalidate();
        }
    }
    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void setViewPager(ViewPager viewPager) throws PagerLessException {
       this.viewPager=viewPager;
       viewPager.addOnPageChangeListener(this);
       initDot(viewPager.getAdapter().getCount());
       onPageSelected(0);
    }

    private void initDot(int count) throws PagerLessException{
        if(count<2) throw new PagerLessException();
        dots=new Dot[count];
        for(int i=0;i<dots.length;i++){
            dots[i]=new Dot();
        }

    }

    public void setAnimateDuration(long animateDuration) {
        this.animateDuration = animateDuration;
    }

    @Override
    public void setRadiusSelected(int radius) {
      this.radiusSelected=radius;
    }

    @Override
    public void setRadiusUnSelected(int radius) {
         this.radiusUnselected=radius;
    }

    @Override
    public void setDistanceDot(int radius) {
        this.distance=radius;
    }
}

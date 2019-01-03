package zhonggu.aiper.com.baselibrary.loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class ImageLoader {

    /**
     * Load image from source and set it into the imageView.
     *
     * @param context context.
     * @param source  could be Uri/String/File/ResourceId.
     * @param view    the imageView.
     */
    public static void load(Context context, Object source, ImageView view) {
        Glide.with(context)
                .load(source)
                .centerCrop()
                .into(view);
    }

    public static void load(Object source, ImageView view) {
        Glide.with(view.getContext())
                .load(source)
                .centerCrop()
                .into(view);
    }

    public static void loadWithCircle(Context context, Object source, ImageView view) {
        Glide.with(context)
                .load(source)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(view);
    }

    public static void loadWithCircle(Object source, ImageView view) {
        Glide.with(view.getContext())
                .load(source)
                .bitmapTransform(new CropCircleTransformation(view.getContext()))
                .into(view);
    }

}

package com.wl.pluto.module_component.photo;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wl.pluto.module_component.R;
import com.wl.pluto.module_component.base.BaseActivity;
import com.wl.pluto.module_component.view.RxTitle;
import com.wl.pluto.module_component.view.dialog.RxDialogChooseImage;
import com.wl.pluto.module_component.view.dialog.RxDialogScaleView;
import com.wl.pluto.module_component.view.dialog.RxDialogSureCancel;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.wl.pluto.lib_kit.rxtool.RxBarTool;
import com.wl.pluto.lib_kit.rxtool.RxPhotoTool;
import com.wl.pluto.lib_kit.rxtool.RxSPTool;

import static com.wl.pluto.module_component.view.dialog.RxDialogChooseImage.LayoutType.TITLE;


/**
 * @author vondear
 */
public class ActivityRxPhoto extends BaseActivity {


    RxTitle mRxTitle;

    TextView mTvBg;

    ImageView mIvAvatar;

    LinearLayout mLlAnchorLeft;

    RelativeLayout mRlAvatar;

    TextView mTvName;

    TextView mTvConstellation;

    TextView mTvBirthday;

    TextView mTvAddress;

    TextView mTvLables;

    TextView mTextView2;

    TextView mEditText2;

    Button mBtnExit;

    LinearLayout mActivityUser;
    private Uri resultUri;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBarTool.noTitle(this);
        setContentView(R.layout.activity_von_photo);
        initLayout();
        initView();
    }

    private void initLayout() {

        mRxTitle = findViewById(R.id.rx_title);

        mTvBg = findViewById(R.id.tv_bg);

        mIvAvatar = findViewById(R.id.iv_avatar);

        mLlAnchorLeft = findViewById(R.id.ll_anchor_left);

        mRlAvatar = findViewById(R.id.rl_avatar);

        mTvName = findViewById(R.id.tv_name);

        mTvConstellation = findViewById(R.id.tv_constellation);

        mTvBirthday = findViewById(R.id.tv_birthday);

        mTvAddress = findViewById(R.id.tv_address);

        mTvLables = findViewById(R.id.tv_lables);

        mTextView2 = findViewById(R.id.textView2);

        mEditText2 = findViewById(R.id.editText2);

        mBtnExit = findViewById(R.id.btn_exit);

        mActivityUser = findViewById(R.id.activity_user);
    }

    protected void initView() {
        Resources r = mContext.getResources();
        resultUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(R.mipmap.circle_elves_ball) + "/"
                + r.getResourceTypeName(R.mipmap.circle_elves_ball) + "/"
                + r.getResourceEntryName(R.mipmap.circle_elves_ball));

        mRxTitle.setLeftFinish(mContext);

        mIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialogChooseImage();
            }
        });
        mIvAvatar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                RxImageTool.showBigImageView(mContext, resultUri);
                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mContext);
                rxDialogScaleView.setImage(resultUri);
                rxDialogScaleView.show();
                return false;
            }
        });
    }

    private void initDialogChooseImage() {
        RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(mContext, TITLE);
        dialogChooseImage.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RxPhotoTool.GET_IMAGE_FROM_PHONE://选择相册之后的处理
                if (resultCode == RESULT_OK) {
//                    RxPhotoTool.cropImage(ActivityUser.this, );// 裁剪图片
                    initUCrop(data.getData());
                }

                break;
            case RxPhotoTool.GET_IMAGE_BY_CAMERA://选择照相机之后的处理
                if (resultCode == RESULT_OK) {
                    /* data.getExtras().get("data");*/
//                    RxPhotoTool.cropImage(ActivityUser.this, RxPhotoTool.imageUriFromCamera);// 裁剪图片
                    initUCrop(RxPhotoTool.imageUriFromCamera);
                }

                break;
            case RxPhotoTool.CROP_IMAGE://普通裁剪后的处理
                RequestOptions options = new RequestOptions()
                        .placeholder(R.mipmap.circle_elves_ball)
                        //异常占位图(当加载异常的时候出现的图片)
                        .error(R.mipmap.circle_elves_ball)
                        //禁止Glide硬盘缓存缓存
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

                Glide.with(mContext).
                        load(RxPhotoTool.cropImageUri).
                        apply(options).
                        thumbnail(0.5f).
                        into(mIvAvatar);
//                RequestUpdateAvatar(new File(RxPhotoTool.getRealFilePath(mContext, RxPhotoTool.cropImageUri)));
                break;

            case UCrop.REQUEST_CROP://UCrop裁剪之后的处理
                if (resultCode == RESULT_OK) {
                    resultUri = UCrop.getOutput(data);
                    roadImageView(resultUri, mIvAvatar);
                    RxSPTool.putContent(mContext, "AVATAR", resultUri.toString());
                } else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                }
                break;
            case UCrop.RESULT_ERROR://UCrop裁剪错误之后的处理
                final Throwable cropError = UCrop.getError(data);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //从Uri中加载图片 并将其转化成File文件返回
    private File roadImageView(Uri uri, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.circle_elves_ball)
                //异常占位图(当加载异常的时候出现的图片)
                .error(R.mipmap.circle_elves_ball)
                .transform(new CircleCrop())
                //禁止Glide硬盘缓存缓存
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(mContext).
                load(uri).
                apply(options).
                thumbnail(0.5f).
                into(imageView);

        return (new File(RxPhotoTool.getImageAbsolutePath(this, uri)));
    }

    private void initUCrop(Uri uri) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));

        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), imageName + ".jpeg"));

        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
        //options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark));

        //开始设置
        //设置最大缩放比例
        options.setMaxScaleMultiplier(5);
        //设置图片在切换比例时的动画
        options.setImageToCropBoundsAnimDuration(666);
        //设置裁剪窗口是否为椭圆
        //options.setCircleDimmedLayer(true);
        //设置是否展示矩形裁剪框
        // options.setShowCropFrame(false);
        //设置裁剪框横竖线的宽度
        //options.setCropGridStrokeWidth(20);
        //设置裁剪框横竖线的颜色
        //options.setCropGridColor(Color.GREEN);
        //设置竖线的数量
        //options.setCropGridColumnCount(2);
        //设置横线的数量
        //options.setCropGridRowCount(1);

        UCrop.of(uri, destinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(this);
    }

    public void onClick() {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(this);
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rxDialogSureCancel.show();
    }
}

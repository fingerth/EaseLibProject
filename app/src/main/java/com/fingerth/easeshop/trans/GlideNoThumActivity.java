package com.fingerth.easeshop.trans;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fingerth.commonadapter.baseadapter.CommonAdapter;
import com.fingerth.commonadapter.baseadapter.ViewHolder;
import com.fingerth.easeshop.R;
import com.hitomi.tilibrary.glideloader.GlideImageLoader;
import com.hitomi.tilibrary.style.progress.ProgressPieIndicator;
import com.hitomi.tilibrary.transfer.TransferConfig;
import com.hitomi.tilibrary.transfer.Transferee;

import java.util.ArrayList;

public class GlideNoThumActivity extends BaseActivity {

    {
        sourceImageList = new ArrayList<>();
        sourceImageList.add("https://file01.easesales.com/upload/C7F16DA142B7E9BF/201612/5177147022583692319.jpg");
        sourceImageList.add("https://file01.easesales.com/upload/C7F16DA142B7E9BF/201612/5455893484777716286.jpg");
        sourceImageList.add("https://file02.easesales.com/upload/C7F16DA142B7E9BF/201612/4736177164675131054.jpg");
        sourceImageList.add("https://file03.easesales.com/upload/C7F16DA142B7E9BF/201612/5261303544378606749.jpg");
        sourceImageList.add("https://file02.easesales.com/upload/C7F16DA142B7E9BF/201612/5742540043558198121.jpg");
        sourceImageList.add("https://file04.easesales.com/upload/C7F16DA142B7E9BF/201612/5729713429386032702.jpg");
        sourceImageList.add("https://file03.easesales.com/upload/C7F16DA142B7E9BF/201612/4907386240706090895.jpg");
        sourceImageList.add("https://file04.easesales.com/upload/C7F16DA142B7E9BF/201612/5565307729587750281.jpg");
        sourceImageList.add("https://file02.easesales.com/upload/C7F16DA142B7E9BF/201612/4674312472199148936.jpg");

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_grid_view;
    }

    @Override
    protected void initView() {
        gvImages = (GridView) findViewById(R.id.gv_images);
    }

    @Override
    protected void testTransferee() {
        config = TransferConfig.build()
                .setSourceImageList(sourceImageList)
                .setMissPlaceHolder(R.drawable.loading_spinner)
                .setProgressIndicator(new ProgressPieIndicator())
                .setImageLoader(GlideImageLoader.with(getApplicationContext()))
                .setOnLongClcikListener(new Transferee.OnTransfereeLongClickListener() {
                    @Override
                    public void onLongClick(ImageView imageView, int pos) {
                        saveImageByGlide(imageView);
                    }
                })
                .create();

        gvImages.setAdapter(new CommonAdapter<String>(this, sourceImageList, R.layout.item_grid_image) {
            @Override
            public void convert(ViewHolder viewHolder, final int position, String s) {
                ImageView imageView = viewHolder.getView(R.id.image_view);

                Glide.with(GlideNoThumActivity.this)
                        .load(s)
                        .centerCrop()
                        .placeholder(R.drawable.loading_spinner)
                        .into(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        config.setNowThumbnailIndex(position);
                        config.setOriginImageList(wrapOriginImageViewList(sourceImageList.size()));

                        transferee.apply(config).show(new Transferee.OnTransfereeStateChangeListener() {
                            @Override
                            public void onShow() {
                                Glide.with(GlideNoThumActivity.this).pauseRequests();
                            }

                            @Override
                            public void onDismiss() {
                                Glide.with(GlideNoThumActivity.this).resumeRequests();
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != WRITE_EXTERNAL_STORAGE) {
            Toast.makeText(this, "请允许获取相册图片文件写入权限", Toast.LENGTH_SHORT).show();
        }
    }

//    private class NineGridAdapter extends CommonAdapter<String> {
//
//        public NineGridAdapter() {
//            super(GlideNoThumActivity.this, R.layout.item_grid_image, sourceImageList);
//        }
//
//        @Override
//        protected void convert(ViewHolder viewHolder, String item, final int position) {
//            ImageView imageView = viewHolder.getView(R.id.image_view);
//
//            Glide.with(GlideNoThumActivity.this)
//                    .load(item)
//                    .centerCrop()
//                    .placeholder(R.mipmap.ic_empty_photo)
//                    .into(imageView);
//
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    config.setNowThumbnailIndex(position);
//                    config.setOriginImageList(wrapOriginImageViewList(sourceImageList.size()));
//
//                    transferee.apply(config).show(new Transferee.OnTransfereeStateChangeListener() {
//                        @Override
//                        public void onShow() {
//                            Glide.with(GlideNoThumActivity.this).pauseRequests();
//                        }
//
//                        @Override
//                        public void onDismiss() {
//                            Glide.with(GlideNoThumActivity.this).resumeRequests();
//                        }
//                    });
//                }
//            });
//        }
//    }

}

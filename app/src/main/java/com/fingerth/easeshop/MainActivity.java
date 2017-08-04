package com.fingerth.easeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.fingerth.easeshop.trans.GlideNoThumActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    @Nullable
    TextBadgeItem numberBadgeItem;

    @Nullable
    ShapeBadgeItem shapeBadgeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        numberBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.blue)
                .setText("" + 0)
                .setHideOnSelect(false);

        shapeBadgeItem = new ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_STAR_5_VERTICES)
                .setShapeColorResource(R.color.teal)
                .setGravity(Gravity.TOP | Gravity.END)
                .setHideOnSelect(false);


        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "Home").setActiveColorResource(R.color.orange).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_github_circle_white_24dp, "Github").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, "Music").setActiveColorResource(R.color.blue).setBadgeItem(shapeBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, "Videogame").setActiveColorResource(R.color.brown))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(int position) {

        if (numberBadgeItem != null) {
            numberBadgeItem.setText(Integer.toString(position));
        }
        tv.setText("Hello world " + position);

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @OnClick(R.id.btn_glide_no_thum)
    public void onViewClicked() {
        startActivity(new Intent(this, GlideNoThumActivity.class));
    }
}

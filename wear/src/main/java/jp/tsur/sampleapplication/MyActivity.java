package jp.tsur.sampleapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // rect 用 と round 用で別のレイアウトを用意する場合
//        setContentView(R.layout.activity_my);
//        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override
//            public void onLayoutInflated(WatchViewStub stub) {
//                mTextView = (TextView) stub.findViewById(R.id.text);
//            }
//        });
        //
        setContentView(R.layout.activity_my);
        WearableListView listView = (WearableListView) findViewById(R.id.list);
        listView.setAdapter(new Adapter(this));
        listView.setClickListener(new WearableListView.ClickListener() {
            @Override
            public void onClick(WearableListView.ViewHolder viewHolder) {
                Intent intent = null;
                switch (viewHolder.getPosition()) {
                    case 0:
                        intent = new Intent(MyActivity.this, WatchViewStubActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MyActivity.this, DelayedConfirmationViewActivity.class);
                        break;
                }
                startActivity(intent);
            }

            @Override
            public void onTopEmptyRegionClick() {

            }
        });
    }

    private static final class Adapter extends WearableListView.Adapter {
        private final LayoutInflater mInflater;
        private Context mContext;

        private Adapter(Context context) {
            mInflater = LayoutInflater.from(context);
            mContext = context;
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new WearableListView.ViewHolder(
                    mInflater.inflate(R.layout.notif_preset_list_item, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
            TextView view = (TextView) holder.itemView.findViewById(R.id.name);
            view.setText(mContext.getResources().getStringArray(R.array.menu)[position]);
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return mContext.getResources().getStringArray(R.array.menu).length;
        }
    }
}

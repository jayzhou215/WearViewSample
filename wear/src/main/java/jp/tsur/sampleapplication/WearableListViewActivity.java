package jp.tsur.sampleapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;


public class WearableListViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wearable_list_view);
        WearableListView listView = (WearableListView) findViewById(R.id.list);
        listView.setAdapter(new Adapter(this));
        listView.setClickListener(new WearableListView.ClickListener() {
            @Override
            public void onClick(WearableListView.ViewHolder viewHolder) {
                Intent intent = null;
                switch (viewHolder.getPosition()) {
                    case 0:
                        intent = new Intent(WearableListViewActivity.this, WatchViewStubActivity.class);
                        break;
                    case 1:
                        intent = new Intent(WearableListViewActivity.this, DelayedConfirmationViewActivity.class);
                        break;
                    case 2:
                        intent = new Intent(WearableListViewActivity.this, ConfirmationActivityActivity.class);
                        break;
                    case 3:
                        intent = new Intent(WearableListViewActivity.this, DismissOverlayViewActivity.class);
                        break;
                    case 4:
                        intent = new Intent(WearableListViewActivity.this, CircledImageViewActivity.class);
                        break;
                    case 5:
                        intent = new Intent(WearableListViewActivity.this, BoxInsetLayoutActivity.class);
                        break;
                    case 6:
                        intent = new Intent(WearableListViewActivity.this, CrossfadeDrawableActivity.class);
                        break;
                    case 7:
                        intent = new Intent(WearableListViewActivity.this, CardScrollViewActivity.class);
                        break;
                    case 8:
                        intent = new Intent(WearableListViewActivity.this, InsetActivityActivity.class);
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

package cwoj.tk.cwoj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private RecyclerView setting;
    private ArrayList<String> Data;
    private SettingsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Data = getData();
        adapter = new SettingsAdapter();

        setting = (RecyclerView) findViewById(R.id.setting);
        setting.setLayoutManager(new LinearLayoutManager(this));
        setting.setAdapter(adapter);

    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("查看APP源代码");
        data.add("尽请期待");
        data.add("尽请期待");
        data.add("尽请期待");
        data.add("尽请期待");
        return data;
    }

    class SettingsAdapter extends Adapter{

        private OnItemClickListener onItemClickListener;

        public void setOnItemClickLitener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SettingsViewHolder(LayoutInflater.from(SettingsActivity.this)
                    .inflate(R.layout.setting_unit, parent, false));
        }
        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            ((SettingsViewHolder)holder).textView.setText(Data.get(position));
            // 如果设置了回调，则设置点击事件
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });

                holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemTouch(holder.itemView, pos, event);
                        return false;
                    }
                });
            }
        }
        @Override
        public int getItemCount() {
            return Data.size();
        }

        class SettingsViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            TextView textView;
            public SettingsViewHolder(View itemView) {
                super(itemView);
                cardView = (CardView) itemView.findViewById(R.id.card);
                textView = (TextView) itemView.findViewById(R.id.cardChild);
            }
        }
    }
}


interface OnItemClickListener {
    void onItemClick(View view, int position);
    void onItemLongClick(View view , int position);
    void onItemTouch(View view, int position, MotionEvent event);
}
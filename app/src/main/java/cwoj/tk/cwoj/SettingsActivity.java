package cwoj.tk.cwoj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private RecyclerView setting;
    private ArrayList<String> Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Data = getData();
        setting = (RecyclerView) findViewById(R.id.setting);
        setting.setLayoutManager(new LinearLayoutManager(this));
        setting.setAdapter(new SettingsAdapter());
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


    class SettingsAdapter extends RecyclerView.Adapter{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SettingsViewHolder(LayoutInflater.from(SettingsActivity.this)
                    .inflate(R.layout.setting_unit, parent));
        }
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((SettingsViewHolder)holder).textView.setText(Data.get(position));
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
                cardView = (CardView) itemView;
                textView = (TextView) cardView.getChildAt(0);
            }
        }
    }
}

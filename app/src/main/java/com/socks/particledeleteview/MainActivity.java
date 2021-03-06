package com.socks.particledeleteview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.socks.library.ParticleLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ParticleAdapter mAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ParticleAdapter();
        recyclerView.setAdapter(mAdapter);
    }


    private class ParticleAdapter extends RecyclerView.Adapter<ParticleAdapter.ParticleViewHolder> {

        private ArrayList<String> strings;

        ParticleAdapter() {
            strings = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                strings.add("POSITION = " + i);
            }
        }

        @Override
        public ParticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_layout, parent, false);
            return new ParticleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ParticleViewHolder holder, final int position) {
            holder.tv.setText(strings.get(position));
            holder.root_layout.setDeleteListener(new ParticleLayout.DeleteListener() {
                @Override
                public void onDelete() {
                    Toast.makeText(MainActivity.this, "DETELE", Toast.LENGTH_SHORT).show();
                    strings.remove(position);
                    mAdapter.notifyItemRemoved(position);
                    mAdapter.notifyItemRangeChanged(position, strings.size() - position);
                }
            });
            holder.root_layout.setBitmapArrays(R.drawable.ic_star, R.drawable.ic_partical, R.drawable.ic_boom);
        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        class ParticleViewHolder extends RecyclerView.ViewHolder {

            TextView tv;
            ParticleLayout root_layout;

            public ParticleViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv);
                root_layout = (ParticleLayout) itemView.findViewById(R.id.root_layout);
            }
        }

    }

}

package com.example.redsocial;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.redsocial.Model.Anonymous;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private AnonymousAdapter mAnonymousAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<Anonymous>anonymous,List<String>keys){
        mContext = context;
        mAnonymousAdapter = new AnonymousAdapter(anonymous, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAnonymousAdapter);

    }

    class  AnonymousItemView extends RecyclerView.ViewHolder{
        private TextView mAnonymousID;
        private TextView mDescription;
        private TextView mLevel;
        private TextView mPin;

        private String key;

        public AnonymousItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.anonymous,parent,false));
            mAnonymousID = (TextView) itemView.findViewById(R.id.anonymousID_txtView);
            mDescription = (TextView) itemView.findViewById(R.id.description_txtView2);
            mLevel = (TextView) itemView.findViewById(R.id.level_textview);
            mPin = (TextView) itemView.findViewById(R.id.pin_textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,AnonymousDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("anonymousID",mAnonymousID.getText().toString());
                    intent.putExtra("pin",mPin.getText().toString());
                    intent.putExtra("level",mLevel.getText().toString());
                    intent.putExtra("description",mDescription.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }
        public void bind (Anonymous anonymous,String key){
            mAnonymousID.setText(anonymous.getAnonymousID());
            mDescription.setText(anonymous.getDescription());
            mPin.setText(anonymous.getPin());
            mLevel.setText(anonymous.getLevel());
            this.key = key;
        }
    }
    class AnonymousAdapter extends RecyclerView.Adapter<AnonymousItemView>{
        private List<Anonymous>mAnonymousList;
        private List<String> mKeys;

        public AnonymousAdapter(List<Anonymous> mAnonymousList, List<String> mKeys) {
            this.mAnonymousList = mAnonymousList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public AnonymousItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new AnonymousItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull AnonymousItemView anonymousItemView, int i) {
            anonymousItemView.bind(mAnonymousList.get(i),mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mAnonymousList.size();
        }
    }


}

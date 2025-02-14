package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {

    private ArrayList<String> mData = null ;


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;
        Button button;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.textView6) ; //in recycle xml
            button = itemView.findViewById(R.id.button11);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    SimpleTextAdapter(ArrayList<String> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public SimpleTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false) ;
        SimpleTextAdapter.ViewHolder vh = new SimpleTextAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(SimpleTextAdapter.ViewHolder holder, int position) {
        String text = mData.get(position) ;
        holder.textView1.setText(text) ;
        Button button=holder.button;

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d("udg SimpleTextAdapter","버튼 클릭");
                //mData에 position에 해당되는 데이터를 지우면 됨.
                //text는 시간:분 형식이고, DB는 시간 열, 분 열으로 저장됨
                MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(v.getContext());
                SQLiteDatabase db = helper.getWritableDatabase(); //wrtie이네?!
                String[] hourmin= text.split(":");

                int hour = Integer.parseInt(hourmin[0]);
                int min = Integer.parseInt(hourmin[1]);
                String query = "DELETE FROM times WHERE hour='" + hour +"'AND minute='"+min+"'";
                db.execSQL(query);
                db.close();

                Log.d("udb simpleTextAdapter","sql 데이터 삭제 성공:"+query);

                mData.remove(position);
                notifyDataSetChanged();



            }
        });


    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}


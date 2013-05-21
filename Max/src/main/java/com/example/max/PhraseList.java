package com.example.max;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fahim.chowdhury on 21/05/13.
 */
public class PhraseList extends ArrayAdapter {
    private Context mContext;
    private int id;
    private String[] items ;

    public PhraseList(Context context, int textViewResourceId, String[] list)
    {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list ;

    }
    public static class ViewHolder{
     public TextView item1;
      public TextView item2;
     }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        ViewHolder holder;
        String item = items[position];
        String eng  = item;
        String ben="";
        if (v == null) {
           LayoutInflater vi =
                   (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           v = vi.inflate(R.layout.grid_item, null);
            holder = new ViewHolder();
            holder.item1 =  (TextView) v.findViewById(R.id.big);
            holder.item2 =  (TextView) v.findViewById(R.id.small);
            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "fonts/Siyamrupali.ttf");
            holder.item2.setTypeface(type);
            v.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)v.getTag();



          //

        }
        if (item.contains("-")) {
            String[] arr =item.split("-");
            eng =arr[0];
            if(arr.length>0)ben = arr[1];
        }
        holder.item1.setText(eng);

        holder.item2.setText(ben);



        // Object item = items[position];
       // View view = super.getView(position, v, parent);
       // TextView textView = new TextView(mContext);
        //View currentView = super.getView(position, v, parent);
      // Typeface type = Typeface.createFromAsset(mContext.getAssets(), "fonts/kalpurush ANSI.ttf");
        //((TextView)view).setTypeface(type);
        //textView.setTypeface(type);
        return v;
    }


}
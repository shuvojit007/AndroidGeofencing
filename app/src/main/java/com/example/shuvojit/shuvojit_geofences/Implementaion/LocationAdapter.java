package com.example.shuvojit.shuvojit_geofences.Implementaion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shuvojit.shuvojit_geofences.Implementaion.Model.MapModel;
import com.example.shuvojit.shuvojit_geofences.Implementaion.Model.MapModel02;
import com.example.shuvojit.shuvojit_geofences.R;
import com.google.android.gms.maps.MapFragment;

import org.w3c.dom.Text;

import java.util.List;

import io.realm.RealmResults;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    List<MapModel02>md;
    private Context cn;

    public LocationAdapter(List<MapModel02> md, Context cn) {
        this.md = md;
        this.cn = cn;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cn);
        View itemView = inflater.inflate(R.layout.rec_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        MapModel02 model = md.get(position);
        holder.place.setText(model.getPlaceName());
        holder.llt.setText(String.valueOf(model.getLat()));
        holder.lng.setText(String.valueOf(model.getLng()));
        holder.todo.setText(model.getTodo());

    }



    @Override
    public int getItemCount() {
        return md.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView place,llt,lng,todo;

        public ViewHolder(View itemView) {
            super(itemView);
            place= itemView.findViewById(R.id.placename);
            llt= itemView.findViewById(R.id.lat);
            lng= itemView.findViewById(R.id.lon);
            todo= itemView.findViewById(R.id.placetodo);


        }
    }

}
